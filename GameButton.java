import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameButton extends JButton{// implements ActionListener{
    
    private static Color pTurn;
    private static ArrayList<ArrayList<GameButton>> buttonList = new ArrayList<ArrayList<GameButton>>();
    private static ArrayList<GameButton> changed;
    private static int nButtons;
    private int cellType;
    private int buttonSize;
    private int xLocal;
    private int yLocal;
    
    public GameButton( int cellType, int buttonSize, int xLocal, int yLocal, ArrayList<ArrayList<GameButton>> buttonList, int nButtons ){
        super();
        this.xLocal=xLocal;
        this.yLocal=yLocal;
        this.buttonSize = buttonSize;
        this.cellType = cellType;
        this.pTurn = Color.RED;
        this.buttonList = buttonList;
        this.nButtons = nButtons;
        this.setIcon( new ButtonIcon( buttonSize ).getIcon( "eCell" ) );
    }
    
    private Color getPTurn(){
        return this.pTurn;
    }
    
    public int getType(){
        return this.cellType;
    }
    
    private void printStats(){
        System.out.print( "(" + xLocal + ", " + yLocal + ") Type:" + cellType + " " );
        if( this.getBackground() == Color.RED )
            System.out.println( "RED" );
        else
            System.out.println( "BLUE" );
    }
    private void printState(){
        for(int i=0; i<nButtons*2; i++)
            System.out.print("=");
        System.out.println();
        for( int j=0; j<nButtons; j++ ){
            for( int i=0; i<nButtons*2; i++ ){
                char c = ' ';
                if(buttonList.get(i).get(j).getBackground()==Color.RED)
                    c = '=';
                else if(buttonList.get(i).get(j).getBackground()==Color.BLUE)
                    c = 'X';
                System.out.print(c);
            }
            System.out.println();
        }
        for(int i=0; i<nButtons*2; i++)
            System.out.print("=");
        System.out.println();
    }
    
    public int getXLocal(){
        return this.xLocal;
    }
    
    public int getYLocal(){
        return this.yLocal;
    }
    
    public void clicked(){
        Color bgColor = this.getBackground();
        if( bgColor != pTurn ){
            if( bgColor == Color.BLACK ){
                setType(1);
                this.setBackground( pTurn );
                incTurn();
                printStats();
            }
        } else {
            if( getType() < 4 ){
                new InfectionSpreader(buttonList, this).getInfection();
                printStats();
                incTurn();
            }
        }
    }
    
    private String tostring(){
        return "(" + xLocal + ", " + yLocal + ")" + cellType;
    }
    
    public void setType(int x){
        this.cellType = x;
        if( x == 1 )
            if( pTurn == Color.blue )
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "aBlueCell" ) );
            else
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "aRedCell" ) );
        if( x == 2 )
            if( pTurn == Color.blue )
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "bBlueCell" ) );
            else
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "bRedCell" ) );
        if( x == 3 )
            if( pTurn == Color.blue )
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "cBlueCell" ) );
            else
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "cRedCell" ) );
        if( x == 4 )
            if( pTurn == Color.blue )
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "solidBlueCell" ) );
            else
                this.setIcon( new ButtonIcon( buttonSize ).getIcon( "solidRedCell" ) );
    }
    
    private Color getTurn(){
        return this.pTurn;
    }
    
    private void incTurn(){
        if( pTurn == Color.RED )
            pTurn = Color.BLUE;
        else
            pTurn = Color.RED;
        changed = new ArrayList<GameButton> ();
    }
}
