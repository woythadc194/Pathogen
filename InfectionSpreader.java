import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfectionSpreader{

    private static ArrayList<ArrayList<GameButton>> buttonList;
    private static ArrayList<GameButton> targets;
    private static ArrayList<GameButton> infected;
    private static GameButton startButton;

    public InfectionSpreader( ArrayList<ArrayList<GameButton>> buttonList, GameButton startButton ){
        this.buttonList = buttonList;
        targets = new ArrayList<GameButton>();
        infected = new ArrayList<GameButton>();
        this.startButton = startButton;
    }

    public void getInfection(){
        GameButton parent = startButton;
        infected.add( parent );
        parent.setType( parent.getType() + 1 );
        getInfection( parent );
    }
    
    private void getInfection( GameButton parent ){
        /* 
         * for watching for program executes
         *
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        /* 
         * for watching for program executes
         */
        
        int parentX = parent.getXLocal();
        int parentY = parent.getYLocal();
        
        targets = new ArrayList<GameButton>();
        
        if( spreadTest( parentX, parentY-1 ) )
            targets.add( buttonList.get( parentX ).get( parentY-1 ) );
        if( spreadTest( parentX, parentY+1 ) )
            targets.add( buttonList.get( parentX ).get( parentY+1 ) );
        if( spreadTest( parentX-1, parentY ) )
            targets.add( buttonList.get( parentX-1 ).get( parentY ) );
        if( spreadTest( parentX+1, parentY ) )
            targets.add( buttonList.get( parentX+1 ).get( parentY ) );

        for( GameButton b : targets )
            spread( parent, b );
    }

    private boolean spreadTest( int parentX, int parentY ){
        try{
            GameButton b = buttonList.get( parentX ).get( parentY );
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

        if( childType == 4 ){
            if( parentType == 4 ){
                    ;
            }else if( parentType == 3 ){
                    ;
            }else if( parentType == 2 ){
                    ;
            }else if( parentType == 1 ){
                    ;
            }
        }else if( childType == 3 ){
            if( parentType == 4 ){
                setSolid( child );
                getInfection( child );
            }else if( parentType == 3 ){
                set3( child );
                getInfection( child );
            }else if( parentType == 2 ){
                ;
            }else if( parentType == 1 ){
                ;
            }
        }else if( childType == 2 ){
            if( parentType == 4 ){
                set3( child );
                getInfection( child );
            }else if( parentType == 3 ){
                set3( child );
                getInfection( child );
            }else if( parentType == 2 ){
                ;
            }else if( parentType == 1 ){
                ;
            }
        }else if( childType == 1 ){
            if( parentType == 4 ){
                set2( child );
                getInfection( child );
            }else if( parentType == 3 ){
                set2( child );
                getInfection( child );
            }else if( parentType == 2 ){
                set2( child );
                getInfection( child );
            }else if( parentType == 1 ){
                ;
            }
        }else if( childType == 0 ){
            set1( child );
        }
    }
    
    private void setSolid( GameButton b ){
        infected.add( b );
    }
    
    private void set3( GameButton b ){
        b.setBackground( startButton.getBackground() );
        b.setType(3);
        infected.add( b );
    }
    private void set2( GameButton b ){
        b.setBackground( startButton.getBackground() );
        b.setType(2);
        infected.add( b );
    }
    private void set1( GameButton b ){
        b.setBackground( startButton.getBackground() );
        b.setType(1);
        infected.add( b );
    }
    
}
