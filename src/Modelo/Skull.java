/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Auxiliar.Drawing;

import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Skull extends Element implements Serializable{
    private int iContaIntervalos;
    
    public Skull(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.iContaIntervalos = 0;
    }

    public void autoDesenho() {
        super.autoDesenho();

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER_BOMBA){
            this.iContaIntervalos = 0;
            Fire f = new Fire("fire.png");
            f.setPosition(pPosition.getLine(),pPosition.getColumn()+1);
            Drawing.getTelaDoJogo().addElemento(f);
        }
    }    
}
