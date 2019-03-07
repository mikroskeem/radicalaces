plugins {
    java
}

group = "eu.mikroskeem"

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
                "Main-Class" to "eu.mikroskeem.radicalaces.Bootstrap"
        )
    }
}

defaultTasks("build")