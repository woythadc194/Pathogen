import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TurnFlag{
    
    private static Color turn;
    
    private static ArrayList<TypeSelectorButton> typeList;
    private static GameButton[][] buttonArray;
    private static GameButton[][] buttonArrayCopy;
    private static int numButtons;
    private static JFrame frame;
    
    public TurnFlag( Color startColor, ArrayList<TypeSelectorButton> typeList, JFrame frame ){
        this.turn = startColor;
        this.typeList = typeList;
        this.frame = frame;
    }
    
    public void setButtonArray( GameButton[][] buttonArray, int numButtons ){
        this.numButtons = numButtons;
        this.buttonArray = buttonArray;
        savePreviousTurn();
    }
    
    public Color getTurn(){
        return turn;
    }
    
    public void undoTurn(){
        setToPreviousTurn();
        incTurn();
    }
    
    private void setToPreviousTurn(){
        for( int i=0; i<buttonArray.length; i++ )
            for( int j=0; j<buttonArray[i].length; j++ )
                buttonArray[i][j] = new GameButton( buttonArrayCopy[i][j] );
    }
    
    public void savePreviousTurn(){
        this.buttonArrayCopy = new GameButton[numButtons][numButtons*2];
        for( int i=0; i<buttonArray.length; i++ )
            for( int j=0; j<buttonArray[i].length; j++ )
                buttonArrayCopy[i][j] = buttonArray[i][j];
    }
    
    public void incTurn(){
  
        System.out.print("Prev: ");
        buttonArrayCopy[0][0].printStats();
        System.out.print("Curr: ");
        buttonArray[0][0].printStats();
        System.out.println();

        String clr = "";
        if( turn == Color.RED ){
            turn = Color.BLUE;
            clr = "Blue";
        }else{
            turn = Color.RED;
            clr = "Red";
        }
        for( TypeSelectorButton b : typeList )
            b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( "" + b.getIconType() + clr + "Cell" ) );
        checkWin();
    }
    
    private void checkWin(){
        int numBlue = 0, numRed = 0;
        for( int i=0; i<buttonArray.length; i++ )
            for( int j=0; j<buttonArray[i].length; j++ )
                if( buttonArray[i][j].getBackground() == Color.RED )
                    numRed++;
                else if( buttonArray[i][j].getBackground() == Color.BLUE )
                    numBlue++;
        if( numRed + numBlue != numButtons )
            return;
        JOptionPane.showMessageDialog(frame, ( "RED: " + numRed + "; BLUE: " + numBlue ), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
