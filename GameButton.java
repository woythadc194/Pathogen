import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    private static Color pTurn;
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
    
    public void setType( int i ){
        this.cellType = i;
        if( i==1 && pTurn==Color.RED)
            this.setIcon( aRedIcon );
        else if( i==1 )
            this.setIcon( aBlueIcon );
    }
    
    public Color getPTurn(){
        return this.pTurn;
    }
    
    public int getCellType(){
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
    
    public void printStats(){
        System.out.print( "(" + xLocal + ", " + yLocal + ") Type:" + cellType + " " );
        if( this.getBackground() == Color.RED )
            System.out.println( "RED" );
        else
            System.out.println( "BLUE" );
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
                incType();
                incTurn();
                printStats();
            }
        }
    }
    
    public void incType(){
        this.cellType++;
        if( cellType == 1 )
            if( pTurn == Color.blue )
                this.setIcon( aBlueIcon );
            else
                this.setIcon( aRedIcon );
        if( cellType == 2 )
            if( pTurn == Color.blue ){
                this.setIcon( bBlueIcon );
            }else{
                this.setIcon( bRedIcon );
            }
        if( cellType == 3 )
            if( pTurn == Color.blue )
                this.setIcon( cBlueIcon );
            else
                this.setIcon( cRedIcon );
    }
    
    public void incTurn(){
        if( pTurn == Color.RED )
            pTurn = Color.BLUE;
        else
            pTurn = Color.RED;
    }
}
