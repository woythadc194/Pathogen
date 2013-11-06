import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TurnFlag{
    
    private static Color turn;
    private static int typeSelected;

    private static ArrayList<TypeButton> typeList;
    private static GameButton[][] buttonArray;
    private static int numButtons;
    private static JFrame frame;
    
    public TurnFlag( Color startColor, JFrame frame ){
        this.turn = startColor;
        this.frame = frame;
        this.typeSelected = 1;
    }
    
    public void addTypeList( ArrayList<TypeButton> typeList ){
        this.typeList = typeList;    
    }

    public void setButtonArray( GameButton[][] buttonArray, int numButtons ){
        this.numButtons = numButtons;
        this.buttonArray = buttonArray;
        this.turn = Color.RED;
    }
    
    public ArrayList<TypeButton> getTypeList(){
        return this.typeList;
    }
    
    public int getTypeSelected(){
        return this.typeSelected;
    }
    
    public void setTypeSelected( int x ){
        //System.out.println("TYPE IS NOW: " + x);
        this.typeSelected = x;
    }
    
    public Color getTurn(){
        return this.turn;
    }
    
    public void incTurn(){
        if( turn == Color.RED )
            turn = Color.BLUE;
        else
            turn = Color.RED;
        for( TypeButton b : typeList ){
            //System.out.println(b.getButtonType());
            if( b.turnBool ){
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + "Dark" ) );
            } else {
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() ) );                
            }
            b.turnBool = !b.turnBool;
            if( b.turnBool && b.getType()==1 ) 
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + "Selected" ) );
        }
        setTypeSelected( 1 );
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
