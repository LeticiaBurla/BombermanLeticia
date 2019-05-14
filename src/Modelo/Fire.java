/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Drawing;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author junio
 */
public class Fire extends Element implements Serializable{
            
    public Fire(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bMortal = true;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(!this.moveRight())
            Drawing.getTelaDoJogo().removeElemento(this);
    }
    
}
