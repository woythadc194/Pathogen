import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeButton extends JButton{
    
    private static int buttonSize;
    private int type;
    private char iconType;
    private static TurnFlag flag;
    
    public TypeButton( String buttonType, int buttonSize, int type, TurnFlag flag ){
        super();
        this.flag = flag;
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
    
    public void clicked(){
        flag.setTypeSelected( this.type );
    }
    
}
