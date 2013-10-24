import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    private int val;
    private int x;
    private int y;
    private boolean clickbl;
    

    public GameButton( int val, int x, int y ){
        //super(new ImageIcon( "eCell.jpg" ) );
        this.val=val;
        this.x=x;
        this.y=y;
        this.clickbl = true;
//        setSize( 25, 25 );
    }

    public void increase(){
        this.val++;
        if( val==4 )
            clickbl = false;
    }
    
    public void increase( int i ){
        this.val += i;
        if( val>4 ){
            val=4;
            clickbl = false;
        }
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getVal(){
        return this.val;
    }
    
    public boolean clickable(){
        return this.clickbl;
    }
 
        /*
    @Override
    public void actionPerformed(ActionEvent e){
        //Execute when button is pressed
        System.out.println(val);
        if( val!=4 ){
            if( currentSelectedVal >= val ){
                increase( currentSelectedVal );
            }
        }
    }
    */
   
}
