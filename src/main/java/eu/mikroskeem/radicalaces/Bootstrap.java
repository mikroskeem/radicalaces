package eu.mikroskeem.radicalaces;

import com.radicalplay.aces.F51;
import eu.mikroskeem.radicalaces.applet.SimpleAppletContext;
import eu.mikroskeem.radicalaces.applet.SimpleAppletStub;

import javax.swing.*;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Bootstrap {
    public static final boolean DEBUG = Boolean.getBoolean("RadicalDebug");
    public static Storage STORAGE;

    private static JFrame mainFrame;

    public static void main(String... args){
        AppletContext appletContext = new SimpleAppletContext();
        AppletStub appletStub = new SimpleAppletStub(appletContext);

        /* Initialize data file */
        try {
            STORAGE = new Storage("radicalaces-mikroskeem.properties");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    STORAGE.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, "Game save shutdown hook"));
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }

        F51 f51 = new F51();
        f51.setStub(appletStub);

        mainFrame = new JFrame();
        mainFrame.setTitle("Radical Aces Classic");
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.add(f51);
        mainFrame.setSize(500, 385);
        mainFrame.addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e){
                f51.init();
                f51.start();
            }

            @Override public void windowClosing(WindowEvent e){
                try {
                    f51.stop();
                } catch(Exception exc){
                    exc.printStackTrace();
                }

                System.exit(0);
            }

            @Override public void windowClosed(WindowEvent e) { windowClosing(null); }
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public static void doExit() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }
}
