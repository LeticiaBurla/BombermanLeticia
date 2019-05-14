/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.Consts;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author aula
 */
public class Bomb extends Element implements Serializable{
    int iCountTime ;//= 0; //original = 0;
    int iRadius;//= 0; //original = 0;
    boolean exploded;

    public Bomb(String sNomeImagePNG) {
        super(sNomeImagePNG);
        iCountTime = 0;
        iRadius = 1;
        exploded = false;
    }

    public void setiRadius(int radius){
        iRadius = radius;
    }
    /*
    public void autoDesenho(){
        iCountTempo += Consts.PERIOD;
        iRaio++;
        if(iCountTempo == 400){
            System.out.println("entrei");
            this.alteraAparencia("explosao.png");
            //destruir o que esta na reta
        }else if(iCountTempo >= 960){
            this.bMatar = true;
        }
        super.autoDesenho();
    }
  */

    public void autoDesenho(){
        iCountTime += Consts.PERIOD;
        //iRaio++;
        if(iCountTime == 400){     //400=5*80 e PERIOD = 80
            exploded = true;
            this.alteraAparencia("explosao.png");
        }else{
            if(iCountTime >= 960) {       //960=12*80 e PERIOD = 80
                this.bKill = true;
            }
        }
        super.autoDesenho();
    }

    private void alteraAparencia(String sNovaAparencia){
        try {        
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNovaAparencia);
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**retorna true se a bomba explodiu na ultima impressao de tela
     * e 'false' caso contrario*/
    public boolean exploded(){
        if(exploded){
            return true;
        }
        return false;
    }

    /**Retorna 'true' se o elemento esta na zona de explosao da bomba
     * e 'false' caso contrario
     * OBS: zona de explosao = raio unidades para cima, raio para baixo,
     * raio uma para esquerda e raio para a direita da bomba
     * FASE 1 -> Raio=1*/
    public boolean isInFireZone(Element e){
        int line = e.getPosition().getLine();
        int column = e.getPosition().getColumn();
        int bombLine = getPosition().getLine();
        int bombColumn = getPosition().getColumn();
        if( bombLine-iRadius<=line && line<= bombLine+iRadius && column==bombColumn){
            return true;
        }
        if( bombColumn-iRadius<=column && column<=bombColumn+iRadius && line==bombLine){
            return true;
        }
        return false;
    }

    /**
     * Metodo que atualiza os elemntos apos a explosao, ou seja,
     * atualiza os elementos que morreram na explosao
     * (que estavam na zona de fogo da bomba quando explodiu)
     * */
    public void updateExplosion(ArrayList<Element> e){
        Element eTemp;
        if(isInFireZone(e.get(0))){
            Bomberman b = (Bomberman)e.get(0);
            b.kill();
        }
        for(int i=1; i<e.size(); i++){
            eTemp = e.get(i);
            if( !(eTemp instanceof HardWall) ){
                if(isInFireZone(eTemp)){
                    eTemp.kill();
                }
            }
        }
    }

}
