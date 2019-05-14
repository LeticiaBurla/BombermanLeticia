package Auxiliar;

import java.io.Serializable;

public class Position implements Serializable{
    private int	line;
    private int column;
    
    private int previousLine;
    private int previousColumn;

    public Position(int line, int column){
        this.setPosition(line,column);
    }

    public boolean setPosition(int line, int column){
        if(line < 0 || line >= Auxiliar.Consts.RES)
            return false;
        if(column < 0 || column >= Auxiliar.Consts.RES)
            return false;

        previousLine = this.line;
        this.line = line;

        previousColumn = this.column;
        this.column = column;
        
        return true;
    }
    public boolean setPosition(Position p){
        return this.setPosition(p.getLine(), p.getColumn());
    }
    
    public int getLine(){
        return line;
    }

    public int getColumn(){
        return column;
    }
    
    public boolean goBack(){    //volta
        return this.setPosition(previousLine,previousColumn);
    }

    //TRADUZIR --------------***************-------------------
    public boolean igual(Position posicao){
        return (line == posicao.getLine() && column == posicao.getColumn());
    }

    public boolean copy(Position posicao){
        return this.setPosition(posicao.getLine(),posicao.getColumn());
    }
    
    public boolean moveUp(){
        return this.setPosition(this.getLine()-1, this.getColumn());
    }
    public boolean moveDown(){
        return this.setPosition(this.getLine()+1, this.getColumn());
    }
    public boolean moveRight(){
        return this.setPosition(this.getLine(), this.getColumn()+1);
    }
    public boolean moveLeft(){
        return this.setPosition(this.getLine(), this.getColumn()-1);
    }
}
