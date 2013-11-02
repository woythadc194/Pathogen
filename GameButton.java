import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameButton extends JButton{
    
    private static GameButton [][] buttonArray;
    private static ArrayList<GameButton> changed;
    private static int nButtons;
    private static TurnFlag flag;
    private int cellType;
    private int buttonSize;
    private int xLocal;
    private int yLocal;
    public boolean changeable;
    
    public GameButton( int cellType, int buttonSize, int xLocal, int yLocal, GameButton[][] buttonArray, int nButtons, TurnFlag flag ){
        super();
        this.changeable = true;
        this.xLocal=xLocal;
        this.yLocal=yLocal;
        this.buttonSize = buttonSize;
        this.cellType = cellType;
        this.flag = flag;
        this.buttonArray = buttonArray;
        this.nButtons = nButtons;
        this.setIcon( new ButtonIcon( buttonSize ).getIcon( "eCell" ) );
        this.setBackground( Color.black );
        this.setPreferredSize( new Dimension( buttonSize, buttonSize ) );
        this.setBorder( BorderFactory.createEmptyBorder() );
    }
    
    public GameButton( GameButton b ){
        super();
        this.changeable = b.changeable;
        this.xLocal=b.xLocal;
        this.yLocal=b.yLocal;
        this.buttonSize = b.buttonSize;
        this.cellType = b.cellType;
        this.flag = b.flag;
        this.buttonArray = b.buttonArray;
        this.nButtons = b.nButtons;
        this.setIcon( b.getIcon() );
        this.setBackground( b.getBackground() );
        this.setPreferredSize( new Dimension( b.buttonSize, b.buttonSize ) );
        this.setBorder( BorderFactory.createEmptyBorder() );
    }
    
    public int getType(){
        return this.cellType;
    }
    
    public void printStats(){
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
                if(buttonArray[i][j].getBackground()==Color.RED)
                    c = '=';
                else if(buttonArray[i][j].getBackground()==Color.BLUE)
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
        Color pTurn = flag.getTurn();
        if( bgColor != pTurn ){
            if( bgColor == Color.BLACK ){
                flag.savePreviousTurn();
                setType(1);
                this.setBackground( pTurn );
                changed = new ArrayList<GameButton> ();
                flag.incTurn();
            }
        } else {
            if( getType() < 4 ){
                flag.savePreviousTurn();
                new InfectionSpreader(buttonArray, this).getInfection();
                changed = new ArrayList<GameButton> ();
                flag.incTurn();
            }
        }
    }
    
    private String tostring(){
        return "(" + xLocal + ", " + yLocal + ")" + cellType;
    }
    
    public void setTypeValue(int x){
        this.cellType = x;
    }
    
    public void setType(int x){
        this.cellType = x;
        Color pTurn = flag.getTurn();
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
}
