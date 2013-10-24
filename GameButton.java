import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    public static Color pTurn;
    public int cellType;
    private int buttonSize;
    private int xLocal;
    private int yLocal;
    private ImageIcon eIcon;
    private ImageIcon aIcon;
    private ImageIcon bIcon;
    private ImageIcon cIcon;
    
    
    public GameButton( int cellType, int buttonSize, int xLocal, int yLocal ){
        super();
        this.xLocal=xLocal;
        this.yLocal=yLocal;
        this.buttonSize = buttonSize;
        this.cellType = cellType;
        this.pTurn = Color.RED;
        makeCellIcons();
        this.setIcon( eIcon );
    }
    
    private void makeCellIcons(){
        eIcon = new ImageIcon( "eCell.jpg" );
        Image img = eIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        eIcon = new ImageIcon( img );
        
        aIcon = new ImageIcon( "aCell.jpg" );
        img = aIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        aIcon = new ImageIcon( img );
        
        bIcon = new ImageIcon( "bCell.jpg" );
        img = bIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        bIcon = new ImageIcon( img );
        
        cIcon = new ImageIcon( "cCell.jpg" );
        img = cIcon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        cIcon = new ImageIcon( img );
    }
    
    public void printStats(){
        String color = getBackground().toString();
        System.out.println( "(" + xLocal + ", " + yLocal + ") Type:" + cellType + " " + color );
    }
    
    public void incType(){
        this.cellType++;
        if( cellType == 1 )
            this.setIcon( aIcon );
        if( cellType == 2 )
            this.setIcon( bIcon );
        if( cellType == 3 )
            this.setIcon( cIcon );
            
        
    }
}/*
    public void setStats( int zVal, int xVal, int yVal ){
        this.zVal=zVal;
        this.xVal=xVal;
        this.yVal=yVal;
        this.clickbl = true;
    }

    public void increase(){
        this.zVal++;
        if( zVal==4 )
            clickbl = false;
    }
    
    public void increase( int i ){
        this.zVal += i;
        if( zVal>4 ){
            zVal=4;
            clickbl = false;
        }
    }
    
    public int getX(){
        return this.xVal;
    }
    
    public int getY(){
        return this.yVal;
    }
    
    public int getVal(){
        return this.zVal;
    }
    
    public boolean clickable(){
        return this.clickbl;
    }
 
    /*@Override
    public void actionPerformed(ActionEvent e){
        //Execute when button is pressed
        System.out.println(zVal);
        if( zVal!=4 ){
            if( currentSelectedVal >= zVal ){
                increase( currentSelectedVal );
            }
        }
    }
}*/
