/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Modelo.EnemyHorizontal;
import Modelo.Bomb;
import Modelo.Element;
import Modelo.Bomberman;
import Auxiliar.Position;

import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author junio
 */
public class ControleDeJogo {

    public void desenhaTudo(ArrayList<Element> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }

    public void updateExplosion(ArrayList<Element> e, Bomb b){
        Element eTemp;
        for(int i=1; i < e.size(); i++){
            eTemp = e.get(i);
            if(!(eTemp instanceof Bomb)){
                if(eTemp.isbTransponivel()){
                    //checar se eTemp esta na zona de fogo da bomba
                    if(b.isInFireZone(eTemp)){
                        if(eTemp instanceof Bomberman){
                            Bomberman bBomberman = (Bomberman) eTemp;
                            bBomberman.kill();
                            System.out.println("morri de bomba");
                        }else{
                            eTemp.kill();
                        }
                    }
                }
            }
        }
    }

    public void processaTudo(ArrayList<Element> e){
        Bomberman bBomberman = (Bomberman)e.get(0);
        Element eTemp;
        boolean hasBomb = false;
        if(bBomberman.getLives()==0){
            // GAME OVER
            System.exit(0);
        }
        //analisamos cada elemento da tela
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            //se ele ja foi morto temos que retira-lo
            //da lista de elementos
            if(eTemp.isbKill()){
               e.remove(eTemp);
                //continue;
            }else{
                //se o elemento eh uma bomba temos que
                //verificar sua explosao
                if(eTemp instanceof Bomb){
                    Bomb bBomba = (Bomb)eTemp;
                    //se a bomba tiver explodido
                    if(bBomba.exploded()){
                        bBomba.updateExplosion(e);
                    }
                }
                if(bBomberman.getPosition().igual(eTemp.getPosition())){
                    if(eTemp.isMortal()){
                        bBomberman.kill();
                        System.out.println("morri "+eTemp.getPosition().getLine()+","+eTemp.getPosition().getColumn());
                    }
                  }
            }
        }


    }

     public boolean ehPosicaoValida(ArrayList<Element> e, Position p){
        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);            
            if(!eTemp.isbTransponivel())
                if(eTemp.getPosition().igual(p))
                    return false;
        }        
        return true;
    }


}
