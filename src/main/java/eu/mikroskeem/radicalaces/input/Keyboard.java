package eu.mikroskeem.radicalaces.input;

import com.radicalplay.aces.F51;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private final F51 game;

    public Keyboard(F51 game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                game.u.fire = true;
                break;
            case KeyEvent.VK_1:
            case KeyEvent.VK_2:
            case KeyEvent.VK_3:
            case KeyEvent.VK_4:
            case KeyEvent.VK_5:
                /* View modes from 1 to 5 */
                game.view = keyCode - 48;
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                game.u.up = true;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                game.u.down = true;
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                game.u.left = true;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                game.u.right = true;
                break;

            case KeyEvent.VK_MINUS:
                game.u.mins = true;
                break;

            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_EQUALS:
                game.u.plus = true;
                break;

            case KeyEvent.VK_TAB:
            case KeyEvent.VK_Q:
                game.tab = true;
                break;

            case KeyEvent.VK_M:
                if(game.nomusic) {
                    game.nomusic = false;
                } else {
                    game.nomusic = true;
                    if(game.plcnt >= 100 && game.crntt != -1) {
                        game.mtrak[game.crntt].stop();
                        game.crntt += -1;
                        game.plcnt = 95;
                    }
                }
                break;

            case KeyEvent.VK_T:
                if(game.plcnt >= 100) {
                    game.mtrak[game.crntt].stop();
                }
                game.plcnt = 95;
                break;

            case KeyEvent.VK_S:
                game.nosound = !game.nosound;
                break;

            case KeyEvent.VK_R:
                game.u.radar = true;
                break;

            case KeyEvent.VK_J:
                if(game.u.jump == 0) {
                    game.u.jump = 1;
                    if(!game.u.jade) {
                        game.u.jade = true;
                    }
                }
                break;

            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_ENTER:
                if(!game.enterd) {
                    game.u.space = true;
                    game.enterd = true;
                }
                break;

            case KeyEvent.VK_D:
                //log.info("Current level: {}", game.xtGraphics.getCurrentLevel());
                break;
        }
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                game.u.fire = false;
                break;

            case KeyEvent.VK_1:
            case KeyEvent.VK_2:
            case KeyEvent.VK_3:
            case KeyEvent.VK_4:
            case KeyEvent.VK_5:
                if(game.view > 0 && game.view < 6) {
                    game.view = 0;
                }
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                game.u.up = false;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                game.u.down = false;
                break;

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                game.u.left = false;
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                game.u.right = false;
                break;

            case KeyEvent.VK_MINUS:
                game.u.mins = false;
                break;

            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_EQUALS:
                game.u.plus = false;
                break;

            case KeyEvent.VK_TAB:
            case KeyEvent.VK_Q:
                game.tab = false;
                break;

            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_ENTER:
                game.enterd = false;
                break;

            case KeyEvent.VK_R:
                game.u.radar = false;
                break;
        }

        e.consume();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
