import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    private static Color pTurn;
    private static ArrayList<ArrayList<GameButton>> buttonList = new ArrayList<ArrayList<GameButton>>();
    private static ArrayList<GameButton> changed;
    private static int nButtons;
    private int cellType;
    private int buttonSize;
    private int xLocal;
    private int yLocal;
    private ImageIcon eIcon;
    private ImageIcon aRedIcon;
    private ImageIcon bRedIcon;
    private ImageIcon cRedIcon;
    private ImageIcon aBlueIcon;
    private ImageIcon bBlueIcon;
    private ImageIcon cBlueIcon;
    
    
    public GameButton( int cellType, int buttonSize, int xLocal, int yLocal, ArrayList<ArrayList<GameButton>> buttonList, int nButtons ){
        super();
        this.xLocal=xLocal;
        this.yLocal=yLocal;
        this.buttonSize = buttonSize;
        this.cellType = cellType;
        this.pTurn = Color.RED;
        this.buttonList = buttonList;
        this.nButtons = nButtons;
        makeCellIcons();
        this.setIcon( eIcon );
    }
    
    private Color getPTurn(){
        return this.pTurn;
    }
    
    private int getCellType(){
        return this.cellType;
    }
    
    private void makeCellIcons(){
        eIcon = new ImageIcon( "eCell.jpg" );
        Image img = eIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        eIcon = new ImageIcon( img );
        
        aRedIcon = new ImageIcon( "aRedCell.jpg" );
        img = aRedIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        aRedIcon = new ImageIcon( img );
        
        bRedIcon = new ImageIcon( "bRedCell.jpg" );
        img = bRedIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        bRedIcon = new ImageIcon( img );
        
        cRedIcon = new ImageIcon( "cRedCell.jpg" );
        img = cRedIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        cRedIcon = new ImageIcon( img );
        
        aBlueIcon = new ImageIcon( "aBlueCell.jpg" );
        img = aBlueIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        aBlueIcon = new ImageIcon( img );
        
        bBlueIcon = new ImageIcon( "bBlueCell.jpg" );
        img = bBlueIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        bBlueIcon = new ImageIcon( img );
        
        cBlueIcon = new ImageIcon( "cBlueCell.jpg" );
        img = cBlueIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        cBlueIcon = new ImageIcon( img );
    }
    
    private void printStats(){
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
                if(buttonList.get(i).get(j).getBackground()==Color.RED)
                    c = '=';
                else if(buttonList.get(i).get(j).getBackground()==Color.BLUE)
                    c = 'X';
                System.out.print(c);
            }
            System.out.println();
        }
        for(int i=0; i<nButtons*2; i++)
            System.out.print("=");
        System.out.println();
    }
    private int getXLocal(){
        return this.xLocal;
    }
    
    private int getYLocal(){
        return this.yLocal;
    }
    
    public void clicked(){
        Color bgColor = this.getBackground();
        if( bgColor != pTurn ){
            if( bgColor == Color.BLACK ){
                setType(1);
                this.setBackground( pTurn );
                incTurn();
                printStats();
            } else {
                        
            }
        } else{
            if( getCellType() < 3 ){
                setType(cellType+1);
                changed.add(this);
                printStats();
                spread( xLocal, yLocal, false ); //FIXME
                incTurn();

            }
        }
    }
    
    private boolean spreadTest( int parentX, int parentY ){
//        System.out.print( "(" + parentX + ", " + parentY + ") " );
        try{ 
            GameButton b = buttonList.get( parentX ).get( parentY ); 
            if( changed.contains(b) ){
//                System.out.println("Failed");
                return false;
            }
//            System.out.println("Passed");
            return true;
        } catch(Exception e){ 
            return false;
        }
    }
    
    private void spread( int parentX, int parentY, boolean lastItr ){
        ArrayList<GameButton> infected = new ArrayList<GameButton>();

        if( spreadTest( parentX, parentY-1 ) )
            infected.add( buttonList.get( parentX ).get( parentY-1 ) );
        if( spreadTest( parentX, parentY+1 ) ) 
            infected.add( buttonList.get( parentX ).get( parentY+1 ) );
        if( spreadTest( parentX-1, parentY ) ) 
            infected.add( buttonList.get( parentX-1 ).get( parentY ) );
        if( spreadTest( parentX+1, parentY ) ) 
            infected.add( buttonList.get( parentX+1 ).get( parentY ) );
   
        for(GameButton b : infected){
            int passType = this.getCellType()-1;
            int infType = b.getCellType();
            Color passBg = this.getBackground();
            Color infBg = b.getBackground();
            if( passType == 2 ){
                if( infType == 2 ){
                    if( passBg==infBg ){                                        //Pass 2; Inf 2; Same Color
                        b.setBackground( passBg );
                        b.setType( 3 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );
                    }else{                                                      //Pass 2; Inf 2; Diff Color
                        b.setBackground( passBg );
                        b.setType( 2 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                           b.spread( b.getXLocal(), b.getYLocal(), false );
                    }
                }else if( infType==1 ){
                    if( passBg==infBg ){                                        //Pass 2; Inf 1; Same Color
                        b.setBackground( passBg );
                        b.setType( 2 );  
                        changed.add( b );               
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );
                    }else{                                                      //Pass 2; Inf 1; Diff Color
                        b.setBackground( passBg );
                        b.setType( 2 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );
                    }
                }else if( infType==0 ){
                    b.setBackground( passBg );                                  //Pass 2; Inf 0; Either Color
                    b.setType( 2 );
                    changed.add( b );
                    printState();
                    if(!lastItr)
                        b.spread( b.getXLocal(), b.getYLocal(), false );
                }
            }else if( passType==1 ){
                if( infType == 2 ){                                                 
                    if( passBg==infBg ){                                        //Pass 1; Inf 2; Same Color;
                        ;
                    }else{                                                      //Pass 1; Inf 2; Diff Color;
                        ;
                    }
                }else if( infType==1 ){
                    if( passBg==infBg ){                                        //Pass 1; Inf 1; Same Color;
                        b.setBackground( passBg );
                        b.setType( 2 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );
                    }else{                                                      //Pass 1; Inf 1; Diff Color;
                        b.setBackground( passBg );
                        b.setType( 1 );
                        changed.add( b );
                        printState();                
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );

//                        System.out.println("Spreading to enemy at (" + b.getXLocal() + ", " + b.getYLocal() + ")" );
                    }
                }else if( infType==0 ){                                         //Pass 1; Inf 0; Either Color;
//                    System.out.println("Empty Cell at (" + b.getXLocal() + ", " + b.getYLocal() + ")" );
                    b.setBackground( passBg );
                    b.setType( 1 );
                    changed.add( b );
                    printState();
                    if(!lastItr)
                        b.spread( b.getXLocal(), b.getYLocal(), true );
                }
            }else if( passType == 0 ){
                if( infType == 2 ){
                    if( passBg==infBg ){                                        //Pass 0; Inf 2; Same Color;
                        ;
                    }else{                                                      //Pass 0; Inf 2; Diff Color;
                        ;
                    }
                } else if( infType == 1 ){
                    if( passBg==infBg ){                                        //Pass 0; Inf 1; Same Color;
                        ;
                    }else{                                                      //Pass 0; Inf 1; Diff Color;
                        b.setBackground( passBg );
                        b.setType( 1 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), false );
                    }
                } else if( infType == 0 ){
                    if( passBg==infBg ){                                        //Pass 0; Inf 0; Either Color;
                        b.setBackground( passBg );
                        b.setType( 1 );
                        changed.add( b );
                        printState();
                        if(!lastItr)
                            b.spread( b.getXLocal(), b.getYLocal(), true );
                    }
                }
            }
        }
    }
    
    private String tostring(){
        return "(" + xLocal + ", " + yLocal + ")" + cellType;
    }
    
    private void setType(int x){
        this.cellType = x;
        if( x == 1 )
            if( pTurn == Color.blue )
                this.setIcon( aBlueIcon );
            else
                this.setIcon( aRedIcon );
        if( x == 2 )
            if( pTurn == Color.blue ){
                this.setIcon( bBlueIcon );
            }else{
                this.setIcon( bRedIcon );
            }
        if( x == 3 )
            if( pTurn == Color.blue )
                this.setIcon( cBlueIcon );
            else
                this.setIcon( cRedIcon );
    }
    
    private Color getTurn(){
        return this.pTurn;
    }
    
    private void incTurn(){
        if( pTurn == Color.RED )
            pTurn = Color.BLUE;
        else
            pTurn = Color.RED;
        changed = new ArrayList<GameButton> ();
    }
}
