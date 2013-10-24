import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    private static Color pTurn;
    private static ArrayList<ArrayList<GameButton>> buttonList = new ArrayList<ArrayList<GameButton>>();
    private static ArrayList<GameButton> seen;
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
    
    
    public GameButton( int cellType, int buttonSize, int xLocal, int yLocal, ArrayList<ArrayList<GameButton>> buttonList ){
        super();
        this.xLocal=xLocal;
        this.yLocal=yLocal;
        this.buttonSize = buttonSize;
        this.cellType = cellType;
        this.pTurn = Color.RED;
        this.buttonList = buttonList;
        makeCellIcons();
        this.setIcon( eIcon );
    }
    
    private GameButton(){
        ;
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
                seen.add(this);
                spread( xLocal, yLocal ); //FIXME
                incTurn();
                printStats();
            }
        }
    }
    
    private void spread( int parentX, int parentY){
        ArrayList<GameButton> infected = new ArrayList<GameButton>();
        GameButton bUp = new GameButton();
        GameButton bDn = new GameButton();
        GameButton bLf = new GameButton();
        GameButton bRt = new GameButton();
        try{ 
            bUp = buttonList.get( parentX ).get( parentY-1 ); 
            if(!seen.contains( bUp ) ){
                seen.add( bUp );
                infected.add( bUp );
            }
        } catch(Exception e){}
        try{ 
            bDn = buttonList.get( parentX ).get( parentY+1 ); 
            if(!seen.contains( bDn ) ){
                seen.add( bDn );
                infected.add( bDn );
            }
        } catch(Exception e){}
        try{ 
            bLf = buttonList.get( parentX-1 ).get( parentY ); 
            if(!seen.contains( bLf ) ){
                seen.add( bLf );
                infected.add( bLf );
            }
        } catch(Exception e){}
        try{ 
            bRt = buttonList.get( parentX+1 ).get( parentY ); 
            if(!seen.contains( bRt ) ){
                seen.add( bRt );
                infected.add( bRt );
            }
        } catch(Exception e){}
   
        for(GameButton b : infected){
            boolean cont = true;
            int passType = this.getCellType()-1;
            int infType = b.getCellType();
            Color passBg = this.getBackground();
            Color infBg = b.getBackground();
            if(passType == 2){
                if(infType == 2){
                    if(passBg==infBg){
                        b.setBackground(passBg);
                        b.setType(3);
                        b.spread( b.getXLocal(), b.getYLocal() );
                    }else{
                        b.setBackground(passBg);
                        b.setType(2);
                        b.spread( b.getXLocal(), b.getYLocal() );
                    }
                }else if(infType==1){
                    if(passBg==infBg){
                        b.setBackground(passBg);
                        b.setType(2);                 
                        b.spread( b.getXLocal(), b.getYLocal() );
                    }else{
                        b.setBackground(passBg);
                        b.setType(2);
                        b.spread( b.getXLocal(), b.getYLocal() );
                    }
                }else if(infType==0){
                    b.setBackground(passBg);
                    b.setType(2);
                        b.spread( b.getXLocal(), b.getYLocal() );
                }
            }else if(passType==1){
                if(infType == 2){
                    if(passBg==infBg){
                        ;
                    }else{
                        ;
                    }
                }else if(infType==1){
                    if(passBg==infBg){
                        b.setBackground(passBg);
                        b.setType(2);
                        b.spread( b.getXLocal(), b.getYLocal() );
                    }else{
                        b.setBackground(passBg);
                        b.setType(1);
                        b.spread( b.getXLocal(), b.getYLocal() );
                        System.out.println("Spreading to enemy at (" + b.getXLocal() + ", " + b.getYLocal() + ")");
                    }
                }else if(infType==0){
                    b.setBackground(passBg);
                    b.setType(1);
                    cont = false;
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
    
    private void incTurn(){
        if( pTurn == Color.RED )
            pTurn = Color.BLUE;
        else
            pTurn = Color.RED;
        seen = new ArrayList<GameButton> ();
    }
}
