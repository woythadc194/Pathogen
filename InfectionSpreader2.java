import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfectionSpreader2{

    private static ArrayList<ArrayList<GameButton>> buttonList;
    private static ArrayList<GameButton> changeTo1;
    private static ArrayList<GameButton> changeTo2;
    private static ArrayList<GameButton> changeTo3;
    private static ArrayList<GameButton> changeTo4;
    
    private static ArrayList<GameButton> infected;
    private static GameButton startButton;

    public InfectionSpreader2( ArrayList<ArrayList<GameButton>> buttonList, GameButton startButton ){
        this.buttonList = buttonList;
        this.startButton = startButton;
        infected =  new ArrayList<GameButton> ();
        changeTo1 = new ArrayList<GameButton> ();
        changeTo2 = new ArrayList<GameButton> ();
        changeTo3 = new ArrayList<GameButton> ();
        changeTo4 = new ArrayList<GameButton> ();
    }

    public void getInfection(){
        GameButton parent = startButton;
        infected.add( parent );
        int parentType = parent.getType();
        if( parentType == 3 ){
            changeTo4.add( parent );
        } else if( parentType == 2 ){
            changeTo3.add( parent );
        } else if( parentType == 1 ){
            changeTo2.add( parent );
        }
        
        //4's
        for( GameButton b : changeTo4 ) //originaly clicked 3 moving to 4
            findSame( b );
        for( GameButton b : changeTo4 ) //the 3s that are touching the first 3s
            findLess( b );
        //3's
        for( GameButton b : changeTo3 ) //the 2s directly touching 3s
            findSame( b );
        for( GameButton b : changeTo3 ) //the 2s that are touching the first 2s
            findLess( b );
        //2's
        for( GameButton b : changeTo2 ) //the 1s directly touching 2s
            findSame( b );
        for( GameButton b : changeTo2 ) //the 1s that are touching the first 1s
            findLess( b );

        changeAll();
    }
    
        
    private void findLess( GameButton parent ){
        int parentType = parent.getType();
        int parentX = parent.getXLocal();
        int parentY = parent.getYLocal();
        ArrayList<GameButton> targets = new ArrayList<GameButton> ();
        if( findLessTest( parentX, parentY-1, parent ) )
            targets.add( buttonList.get( parentX ).get( parentY-1 ) );
        if( findLessTest( parentX, parentY+1, parent ) )
            targets.add( buttonList.get( parentX ).get( parentY+1 ) );
        if( findLessTest( parentX-1, parentY, parent ) )
            targets.add( buttonList.get( parentX-1 ).get( parentY ) );
        if( findLessTest( parentX+1, parentY, parent ) )
            targets.add( buttonList.get( parentX+1 ).get( parentY ) );

        for( GameButton b : targets ){
            if( parentType == 3 ){
                changeTo3.add( b );
            } else if( parentType == 2 ){
                changeTo2.add( b );
            } else if( parentType == 1 ){
                changeTo1.add( b );
            }
            infected.add( b );
        }
    }
    
    private boolean findLessTest( int parentX, int parentY, GameButton parent ){
        try{
            GameButton child = buttonList.get( parentX ).get( parentY );
            int childType = child.getType();
            int parentType = parent.getType();
            if( infected.contains( child ) )
                return false;
            if( parentType > childType )
                return true;
            return false;
        } catch(Exception e){
            return false;
        }
    }
        
    private void findSame( GameButton parent ){
        int parentType = parent.getType();
        int parentX = parent.getXLocal();
        int parentY = parent.getYLocal();
        ArrayList<GameButton> targets = new ArrayList<GameButton> ();
        if( findSameTest( parentX, parentY-1, parent ) )
            targets.add( buttonList.get( parentX ).get( parentY-1 ) );
        if( findSameTest( parentX, parentY+1, parent ) )
            targets.add( buttonList.get( parentX ).get( parentY+1 ) );
        if( findSameTest( parentX-1, parentY, parent ) )
            targets.add( buttonList.get( parentX-1 ).get( parentY ) );
        if( findSameTest( parentX+1, parentY, parent ) )
            targets.add( buttonList.get( parentX+1 ).get( parentY ) );

        for( GameButton b : targets ){
            if( parentType == 3 ){
                changeTo4.add( b );
            } else if( parentType == 2 ){
                changeTo3.add( b );
            } else if( parentType == 1 ){
                changeTo2.add( b );
            } 

            infected.add( b );
            findSame( b );
        }
    }
    
    private boolean findSameTest( int parentX, int parentY, GameButton parent ){
        try{
            GameButton child = buttonList.get( parentX ).get( parentY );
            int childType = child.getType();
            int parentType = parent.getType();
            if( infected.contains( child ) )
                return false;
            if( parentType == childType )
                return true;
            return false;
        } catch(Exception e){
            return false;
        }
    }
    
    private void changeAll(){
        System.out.println(changeTo4.size());
        System.out.println(changeTo3.size());
        System.out.println(changeTo2.size());
        System.out.println(changeTo1.size());
        for( GameButton b : changeTo4 )
            b.setType( 4 );
        for( GameButton b : changeTo3 )
            b.setType( 3 );
        for( GameButton b : changeTo2 )
            b.setType( 2 );
        for( GameButton b : changeTo1 )
            b.setType( 1 );
    }
    
}
