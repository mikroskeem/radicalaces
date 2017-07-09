package eu.mikroskeem.radicalaces.applet;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

public class SimpleAppletContext implements AppletContext {
    private final HashMap<URL, AudioClip> audioclips = new HashMap<>();
    private final HashMap<URL, Image> images = new HashMap<>();
    private final HashMap<String, InputStream> streams = new HashMap<>();

    @Override
    public AudioClip getAudioClip(URL url) {
        return audioclips.computeIfAbsent(url, key-> new sun.applet.AppletAudioClip(url));
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
}
