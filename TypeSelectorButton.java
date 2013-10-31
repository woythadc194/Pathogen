import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeSelectorButton extends JButton{
    
    private static int buttonSize;
    private int type;
    private char iconType;
    
    public TypeSelectorButton( String buttonType, int buttonSize, int type ){
        super();
        this.buttonSize = buttonSize;
        this.type = type;
        setIconType();
        this.setIcon( new ButtonIcon( buttonSize ).getIcon( buttonType ) );
        this.setBackground( Color.black );
        this.setPreferredSize( new Dimension( buttonSize, buttonSize ) );
        this.setBorder( BorderFactory.createEmptyBorder() );
    }
    
    private void setIconType(){
        iconType = ( char )( type+96 );
    }
    
    public int getButtonSize(){
        return this.buttonSize;
    }
    
    public char getIconType(){
        return this.iconType;
    }
    
}
