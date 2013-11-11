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
            if(b.turnBool)
                b.cooldown--;
            if( b.cooldown < 0 )
                b.cooldown = 0;
            
            if( b.cooldown != 0 && b.getType() != 1 )
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + b.cooldown ) );
            else if( b.turnBool )
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + "Dark" ) );
            else
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() ) );
            
            if( b.getType()==this.getTypeSelected() && b.cooldown==0 && b.turnBool){
                b.resetCooldown();
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + b.cooldown ) );
            }
            if( b.getType()==1)
                if( b.turnBool )
                    b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + "Dark" ) );
                else
                    b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() ) );                    

            b.turnBool = !b.turnBool;
            if( b.turnBool && b.getType()==1 )
                b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( b.getButtonType() + "Selected" ) );
                
        }
        setTypeSelected( 1 );
        checkWin();
    }
    
    private void checkWin(){
        int numBlue = 0, numRed = 0, numBlueS = 0, numRedS = 0;
        for( int i=0; i<buttonArray.length; i++ )
            for( int j=0; j<buttonArray[i].length; j++ ){
                if( buttonArray[i][j].getBackground() == Color.RED ){
                    numRed++;
                    if( buttonArray[i][j].getType() == 4 )
                        numRedS++;
                } else if( buttonArray[i][j].getBackground() == Color.BLUE ){
                    numBlue++;
                    if( buttonArray[i][j].getType() == 4 )
                        numBlueS++;
                }
            }
        if( numRedS < numButtons/2 && numBlueS < numButtons/2 && numRed+numBlue!=numButtons )
            return;
        if( numRedS >= numButtons/2 )
            JOptionPane.showMessageDialog(frame, ( "Red Solid took over the board \n RED: " 
            + numRed + "; BLUE: " + numBlue ), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        if( numBlueS >= numButtons/2 )
            JOptionPane.showMessageDialog(frame, ( "Blue Solid took over the board \n RED: " 
            + numRed + "; BLUE: " + numBlue ), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        if( numRed + numBlue == numButtons )
            JOptionPane.showMessageDialog(frame, ( "RED: " + numRed + "; BLUE: " 
            + numBlue ), "GAME OVER", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
