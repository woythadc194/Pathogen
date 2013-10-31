import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TurnFlag{
    
    private static Color turn;
    private static ArrayList<TypeSelectorButton> list;
    
    public TurnFlag( Color startColor, ArrayList<TypeSelectorButton> list ){
        this.turn = startColor;
        this.list = list;  
    }
    
    public Color getTurn(){
        return turn;
    }
    
    public void incTurn(){
        String clr = "";
        if( turn == Color.RED ){
            turn = Color.BLUE;
            clr = "Blue";
        }else{
            turn = Color.RED;
            clr = "Red";
        }
        for( TypeSelectorButton b : list )
            b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( "" + b.getIconType() + clr + "Cell" ) );
    }
}
