import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfectionSpreader{

    private static GameButton[][] buttonArray;
    private static ArrayList<GameButton> targets;
    private static ArrayList<GameButton> infected;
    private static GameButton startButton;
    private static int strength;
    private static TurnFlag flag;
    private static Color turnColor;

    public InfectionSpreader( GameButton[][] buttonArray, GameButton startButton, int strength, TurnFlag flag ){
        this.buttonArray = buttonArray;
        targets = new ArrayList<GameButton>();
        infected = new ArrayList<GameButton>();
        this.startButton = startButton;
        this.strength = strength;
        this.flag = flag;
        this.turnColor = flag.getTurn();
        setChangeable();
    }
    
    private static void setChangeable(){
        for( int i=0; i<buttonArray.length; i++)
            for( int j=0; j<buttonArray[i].length; j++)
                buttonArray[i][j].changeable = true;
    }

    public void getInfection(){
        GameButton parent = startButton;
        infected.add( parent );
        setParent( parent ); 
        getInfection( parent );
    }
    
    private void setParent( GameButton parent){
        int parentStrength = parent.getType();
        if(parentStrength < strength)
            parent.setType(strength);
        else if(parentStrength == strength)
            parent.setType(strength+1);
    }
    
    private void getInfection( GameButton parent ){
        int parentX = parent.getXLocal();
        int parentY = parent.getYLocal();
        
        targets = new ArrayList<GameButton>();
        
        if( spreadTest( parentX, parentY-1 ) )
            targets.add( buttonArray[parentX][parentY-1] );
        if( spreadTest( parentX, parentY+1 ) )
            targets.add( buttonArray[parentX][parentY+1] );
        if( spreadTest( parentX-1, parentY ) )
            targets.add( buttonArray[parentX-1][parentY] );
        if( spreadTest( parentX+1, parentY ) )
            targets.add( buttonArray[parentX+1][parentY] );

        for( GameButton b : targets )
            spread( parent, b );
    }

    private boolean spreadTest( int parentX, int parentY ){
        try{
            GameButton b = buttonArray[parentX][parentY];
            if( infected.contains(b) )
                return false;
            return true;
        } catch(Exception e){
            return false;
        }
    }

    private void spread( GameButton parent, GameButton child ){
        int parentType = parent.getType();
        int childType = child.getType();
        Color parentBg = parent.getBackground();
        Color childBg = child.getBackground();
        if( parentType == 4 ){
            if( child.changeable && childType == 3 ){
                set4( child );
                getInfection( child );
            } else if( child.changeable && childType <= 2 ){
                set3( child );
                getInfection( child );
            }
        } else if( parentType == 3 ){
            if( child.changeable && childType == 2 ){
                set3( child );
                getInfection( child );
            } else if( child.changeable && childType <= 1 ){
                set2( child );
                getInfection( child );
            }
        } else if( parentType == 2 ){
            if( child.changeable && childType == 1 ){
                set2( child );
                getInfection( child );
            } else if( child.changeable && childType == 0 ){
                set1( child );
            }
        }
    }
    
    private void set4( GameButton b ){
        b.changeable = false;
        b.setType(4);
        infected.add( b );
    }
    
    private void set3( GameButton b ){
        b.changeable = false;
        b.setType(3);
        infected.add( b );
    }
    
    private void set2( GameButton b ){
        b.changeable = false;
        b.setType(2);
        infected.add( b );
    }
    
    private void set1( GameButton b ){
        b.changeable = false;
        b.setType(1);
        infected.add( b );
    }
    
}
