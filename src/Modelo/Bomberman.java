/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Drawing;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Junio
 */
public class Bomberman extends Element implements Serializable{
    int lives;

    public Bomberman(String sNomeImagePNG) {
        super(sNomeImagePNG);
        lives = 3;
    }

    public void backToLastPosition(){
        this.pPosition.goBack();
    }

    @Override
    public void kill(){
        lives = lives - 1;
    }

    public int getLives(){
        return lives;
    }
}
