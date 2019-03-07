package eu.mikroskeem.radicalaces.applet;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;

public class SimpleAppletContext implements AppletContext {
    private final HashMap<URL, AudioClip> audioclips = new HashMap<>();
    private final HashMap<URL, Image> images = new HashMap<>();
    private final HashMap<String, InputStream> streams = new HashMap<>();

    @Override
    public AudioClip getAudioClip(URL url) {
        return audioclips.computeIfAbsent(url, createAudioClip);
    }

    @Override
    public Image getImage(URL url) {
        return images.computeIfAbsent(url, key -> Toolkit.getDefaultToolkit().createImage(url));
    }

    @Override
    public void showStatus(String status) {

    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException {
        streams.put(key, stream);
    }

    @Override
    public InputStream getStream(String key) {
        return streams.get(key);
    }

    @Override
    public Iterator<String> getStreamKeys() {
        return streams.keySet().iterator();
    }

    @Override public Applet getApplet(String name) { return null; }
    @Override public Enumeration<Applet> getApplets() { return null; }
    @Override public void showDocument(URL url) {}
    @Override public void showDocument(URL url, String target) {}

    private static Function<URL, AudioClip> createAudioClip;

    private static <T> T construct(Constructor<T> constructor, Object... args) {
        try {
            return constructor.newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T invoke(Method method, Object instance, Object... args) {
        try {
            return (T) method.invoke(instance, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            Class <?> jsac = Class.forName("com.sun.media.sound.JavaSoundAudioClip");
            Method createAc = jsac.getMethod("create", URL.class);

            createAudioClip = url -> invoke(createAc, null, url);
        } catch (Exception e) {
            // Try older API
            try {
                Class <?> aac = Class.forName("sun.applet.AppletAudioClip");
                Constructor<?> aacCtor = aac.getConstructor(URL.class);

                createAudioClip = url -> construct((Constructor<AudioClip>) aacCtor, url);
            } catch (Exception e2) {
                System.err.println("Failed to find a suitable way to create " + AudioClip.class + " instances!");
            }
        }
    }
}
