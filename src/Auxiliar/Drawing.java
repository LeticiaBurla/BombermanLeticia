/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Controler.Tela;

/**
 *
 * @author junio
 */
public class Drawing implements Serializable {
    static Tela tTela;
    public static void setCenario(Tela umJCenario) {
        tTela = umJCenario;
    }

    public static Tela getTelaDoJogo() {
        return tTela;
    }

    public static Graphics getGraphicsDaTela() {
        return tTela.getGraphicsBuffer();
    }
    
    public static void desenhar(ImageIcon iImage, int iColuna, int iLinha) {
        iImage.paintIcon(tTela, getGraphicsDaTela(), iColuna * Consts.CELL_SIDE, iLinha * Consts.CELL_SIDE);
    }
    
    public static boolean ehPosicaoValida(Position p){
        return tTela.ehPosicaoValida(p);
    }
}
