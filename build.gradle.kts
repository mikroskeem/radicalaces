plugins {
    java
}

group = "eu.mikroskeem"

val ecjVersion = "3.16.0"

repositories {
    mavenLocal()
    mavenCentral()
}

val compileJava by tasks.getting(JavaCompile::class) {
    if(project.hasProperty("useEcj")) {
        println("*******************************")
        println("* Using Eclipse Java Compiler *")
        println("*******************************")

        project.configurations.maybeCreate("ecj")
        project.dependencies.add("ecj", "org.eclipse.jdt:ecj:$ecjVersion")

        options.isFork = true
        options.forkOptions.apply {
            executable = "java"
            jvmArgs = listOf(
                    "-classpath",
                    project.configurations["ecj"].asPath,
                    "org.eclipse.jdt.internal.compiler.batch.Main"
            )
        }
    }
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
                "Main-Class" to "eu.mikroskeem.radicalaces.Bootstrap"
        )
    }
}

defaultTasks("build")