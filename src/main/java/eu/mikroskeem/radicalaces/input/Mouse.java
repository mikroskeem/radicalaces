package eu.mikroskeem.radicalaces.input;

import com.radicalplay.aces.F51;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
    private final F51 game;

    public Mouse(F51 game) {
        this.game = game;
    }

    @Override public void mouseClicked(MouseEvent e){
        if(game.maxmo != -1) {
            game.mon = false;
            if(game.moner.equals("Click here to Start")) {
                game.moner = "Click here to Continue";
            }
        }

        if(game.u.canclick) {
            game.u.space = true;
        }
        e.consume();
    }

    @Override public void mouseExited(MouseEvent e){
        if(!game.nounif) {
            game.mon = true;
        }

        if(game.maxmo != -1) {
            game.view = 0;
            game.u.radar = false;
            game.u.plus = false;
            game.u.mins = false;
            game.enterd = false;
            game.tab = false;
            game.u.fire = false;
            game.u.left = false;
            game.u.right = false;
            game.u.down = false;
            game.u.up = false;
        }
        e.consume();
    }

    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
}
