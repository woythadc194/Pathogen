import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameButton extends JButton{// implements ActionListener{
    
    private int zVal;
    private int xVal;
    private int yVal;
    private boolean clickbl;
    
    public GameButton(ImageIcon icon){
        super(icon);
    }

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
    }*/
}
