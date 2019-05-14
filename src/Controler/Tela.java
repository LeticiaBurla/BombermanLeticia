/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tela.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package Controler;

import Modelo.*;
import Auxiliar.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;
/**
 *
 * @author junio
 */
public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Bomberman bBomberman;
    private ArrayList<Element> eElementos;
    private ArrayList<Element> fase1;
    private ArrayList<Element> fase2;
    private ArrayList<Element> fase3;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    /**
     * Creates new form tabuleiro
     */
    public Tela() {
        Drawing.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        eElementos = new ArrayList<Element>(100);

        /*Cria eElementos adiciona elementos*/
        bBomberman = new Bomberman("bomberman.png");
        bBomberman.setPosition(0, 7);
        this.addElemento(bBomberman);

        EnemyHorizontal bBichinhoH = new EnemyHorizontal("bichinho.png");
        bBichinhoH.setPosition(3, 3);
        this.addElemento(bBichinhoH);

        Skull bV = new Skull("caveira.png");
        bV.setPosition(9, 1);
        this.addElemento(bV);

        /*
        Bomba b = new Bomba("bomba.png");
        b.setPosicao(8, 8);
        this.addElemento(b);
        */

        RandomRobot rb = new RandomRobot("bichinho.png");
        rb.setPosition(1, 1);
        this.addElemento(rb);
        
        HardWall t1 = new HardWall("parede.png");
        t1.setPosition(4, 4);
        this.addElemento(t1);

        EnemyHorizontal bBichinhoH2 = new EnemyHorizontal("bichinho.png");
        bBichinhoH2.setPosition(6, 6);
        this.addElemento(bBichinhoH2);

        EnemyHorizontal bBichinhoH3 = new EnemyHorizontal("bichinho.png");
        bBichinhoH3.setPosition(2,9);
        this.addElemento(bBichinhoH3);
        
        HardWall t2 = new HardWall("parede.png");
        t2.setPosition(4, 5);
        this.addElemento(t2);

        HardWall t3 = new HardWall("parede.png");
        t3.setPosition(4, 6);
        this.addElemento(t3);        

        HardWall t4 = new HardWall("parede.png");
        t4.setPosition(0, 6);
        this.addElemento(t4);  

        HardWall t5 = new HardWall("parede.png");
        t5.setPosition(1, 6);
        this.addElemento(t5);  

        HardWall t6 = new HardWall("parede.png");
        t6.setPosition(1, 7);
        this.addElemento(t6);         
        
    }

/*--------------------------------------------------*/
/*------Não se preocupe com o código a seguir-------*/
/*--------------------------------------------------*/
    public void addElemento(Element umElemento) {
        eElementos.add(umElemento);
    }

    public void removeElemento(Element umElemento) {
        eElementos.remove(umElemento);
    }

    public boolean ehPosicaoValida(Position p){
        return cj.ehPosicaoValida(this.eElementos, p);
    }
    
    public Graphics getGraphicsBuffer(){
        return g2;
    }
       
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*Desenha cenário*/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "green.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!this.eElementos.isEmpty()) {
            this.cj.desenhaTudo(eElementos);
            this.cj.processaTudo(eElementos);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
            TimerTask task = new TimerTask() {
                public void run() {
                    repaint();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.eElementos.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            try {
                File tanque = new File("c:\\temp\\POO.zip");
                FileInputStream canoOut = new FileInputStream(tanque);
                GZIPInputStream compactador = new GZIPInputStream(canoOut);
                ObjectInputStream serializador = new ObjectInputStream(compactador);
                this.eElementos = (ArrayList<Element>)serializador.readObject();
                serializador.close();
            } catch (Exception ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            try {
                File tanque = new File("c:\\temp\\POO.zip");
                tanque.createNewFile();
                FileOutputStream canoOut = new FileOutputStream(tanque);
                GZIPOutputStream compactador = new GZIPOutputStream(canoOut);
                ObjectOutputStream serializador = new ObjectOutputStream(compactador);
                serializador.writeObject(this.eElementos);
                serializador.flush();
                serializador.close();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            bBomberman.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            bBomberman.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bBomberman.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bBomberman.moveRight();
        }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            //bBomberman.soltaUmaBomba();
            Bomb b = new Bomb("bomba.png");
            b.setPosition(bBomberman.getPosition());
            this.addElemento(b);
        }
        if (!this.ehPosicaoValida(bBomberman.getPosition())) {
            bBomberman.backToLastPosition();
        }

        this.setTitle("-> Cell: " + (bBomberman.getPosition().getColumn()) + ", "
                + (bBomberman.getPosition().getLine()));

        // repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado
         int x = eElementos.getX();
         int y = eElementos.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.bBomberman.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         */
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2015-1 - Adventures of lolo");
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
