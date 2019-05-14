/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Drawing;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Junio
 */
//BichinhoVaiVemHorizontal
public class EnemyHorizontal extends Element  implements Serializable{
    private boolean bRight;

    public EnemyHorizontal(String sNomeImagePNG) {
        super(sNomeImagePNG);
        bRight = true;
        bMortal = true;
    }
    public void autoDesenho(){
        if(bRight)
            this.setPosition(pPosition.getLine(), pPosition.getColumn()+1);
        else
            this.setPosition(pPosition.getLine(), pPosition.getColumn()-1);

        super.autoDesenho();
        bRight = !bRight;
    }
}
