/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Drawing;
import java.util.Random;

/**
 *
 * @author ICMC
 */
public class RandomRobot extends Element {

    int iTimer = 0;

    public RandomRobot(String sNomeImagePNG) {
        super(sNomeImagePNG);
        bMortal = true;
    }

    public void autoDesenho() {
        int iRandDirecao;
        iTimer++;
        if (iTimer == Consts.TIMER) {
            iTimer = 0;
            /*sortear um número de 1 a 4*/
            Random r = new Random();
            iRandDirecao = r.nextInt(4);
            /*de acordo com o numero sorteado, mover o personagem*/
            if (iRandDirecao == 0) {
                this.moveDown();
            } else if (iRandDirecao == 1) {
                this.moveLeft();
            } else if (iRandDirecao == 2) {
                this.moveUp();
            } else {
                this.moveRight();
            }
        }
        if (!Drawing.ehPosicaoValida(pPosition))
            this.getPosition().goBack();
        super.autoDesenho();
    }
}
