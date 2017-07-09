package eu.mikroskeem.radicalaces.applet;

import com.radicalplay.aces.F51;

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;

public class SimpleAppletStub implements AppletStub {
    private final AppletContext simpleAppletContext;

    public SimpleAppletStub(AppletContext simpleAppletContext) {
        this.simpleAppletContext = simpleAppletContext;
    }

    @Override public boolean isActive() {
        /* TODO: find out what this does */
        return false;
    }

    @Override public URL getDocumentBase() {
        return F51.class.getResource("/");
    }

    @Override public URL getCodeBase() {
        return F51.class.getResource("/");
    }

    @Override public String getParameter(String name) {
        return null;
    }

    @Override public AppletContext getAppletContext() {
        return simpleAppletContext;
    }

    @Override public void appletResize(int width, int height) {
        throw new UnsupportedOperationException("Radical Aces doesn't support resizing sadly :(");
    }
}
