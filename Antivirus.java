import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Antivirus{

    private static GameButton[][] buttonArray;
    private static GameButton startButton;
    private static int strength;
    private static TurnFlag flag;


    public Antivirus( GameButton[][] buttonArray, GameButton startButton, int strength, TurnFlag flag ){
        this.buttonArray = buttonArray;
        this.startButton = startButton;
        this.strength = strength;
        this.flag = flag;
    }
    
    public void cure(){
        set0( startButton );
        cure( startButton );
        flag.incTurn();
    }
    
    public void cure( GameButton parent ){
        int parentX = parent.getXLocal();
        int parentY = parent.getYLocal();
        
        ArrayList<GameButton> targets = new ArrayList<GameButton>();
        
        if( spreadTest( parentX, parentY-1 ) )
            targets.add( buttonArray[parentX][parentY-1] );
        if( spreadTest( parentX, parentY+1 ) )
            targets.add( buttonArray[parentX][parentY+1] );
        if( spreadTest( parentX-1, parentY ) )
            targets.add( buttonArray[parentX-1][parentY] );
        if( spreadTest( parentX+1, parentY ) )
            targets.add( buttonArray[parentX+1][parentY] );

        for( GameButton b : targets )
            set0( b );
        for( GameButton b : targets )
            cure( b );
    }

    private boolean spreadTest( int parentX, int parentY ){
        try{
            GameButton b = buttonArray[parentX][parentY];
            if( b.getType() != strength )
                return false;
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    private void set0( GameButton b ){
        b.setBackground( Color.BLACK );
        b.setTypeValue( 0 );
        b.setIcon( new ButtonIcon( b.getButtonSize() ).getIcon( "eCell" ) );
    }
}   
