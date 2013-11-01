import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TurnFlag{
    
    private static Color turn;
    
    private static ArrayList<TypeSelectorButton> typeList;
    private static ArrayList<ArrayList<GameButton>> buttonList;
    private static ArrayList<ArrayList<GameButton>> buttonListPrevious;
    private static int numButtons;
    private static JFrame frame;
    
    public TurnFlag( Color startColor, ArrayList<TypeSelectorButton> typeList, JFrame frame ){
        this.turn = startColor;
        this.typeList = typeList;
        this.frame = frame;
    }
    
    public void setButtonList( ArrayList<ArrayList<GameButton>> buttonList, int numButtons ){
        this.numButtons = numButtons;
        this.buttonList = buttonList;
    }
    
    public Color getTurn(){
        return turn;
    }
    
    public void undoTurn(){
        buttonList = buttonListPrevious;
        incTurn();
    }
    
    private void setPreviousTurn(){
        this.buttonListPrevious = new ArrayList<ArrayList<GameButton>> ();
        for( ArrayList<GameButton> list : buttonList ){
            ArrayList<GameButton> listCopy = new ArrayList<GameButton>();
            for( GameButton b : list )
                listCopy.add( new GameButton( b ) );
            buttonListPrevious.add( list );
        }
    }
    
    public void incTurn(){
        setPreviousTurn();
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
        for( ArrayList<GameButton> list : buttonList )
            for( GameButton b : list )
                if( b.getBackground() == Color.RED )
                    numRed++;
                else if( b.getBackground() == Color.BLUE )
                    numBlue++;
        if( numRed + numBlue != numButtons )
            return;
        JOptionPane.showMessageDialog(frame, ( "RED: " + numRed + "; BLUE: " + numBlue ), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
