package eu.mikroskeem.radicalaces;

import com.radicalplay.aces.F51;

import java.net.URL;
import java.nio.file.Paths;

public class Utils {
    public static URL getGameResource(String path){
        String resourcePath = join("/game", path);
        return F51.class.getResource(resourcePath);
    }

    public static String join(String base, String... child){
        /* Right... Windows peasants get backslash here... */
        return Paths.get(base, child)
                .toString()
                .replace("\\", "/");
    }
}
