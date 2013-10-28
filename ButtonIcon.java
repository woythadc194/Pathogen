import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonIcon{
    
    private int buttonSize;
    public ButtonIcon(int buttonSize){
        this.buttonSize = buttonSize;
    }
    
    public ImageIcon getIcon(String buttonType){
        ImageIcon icon = new ImageIcon( buttonType + ".jpg" );
        Image img = icon.getImage().getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
        icon = new ImageIcon( img );
        return icon;
    }
}
