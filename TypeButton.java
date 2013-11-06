import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeButton extends JButton{
    
    public boolean turnBool;
    private static int buttonSize;
    private int type;
    private char iconType;
    private static TurnFlag flag;
    private String buttonType;
    
    public TypeButton( String buttonType, int buttonSize, int type, TurnFlag flag, boolean turnBool ){
        super();
        this.flag = flag;
        this.buttonSize = buttonSize;
        this.buttonType = buttonType;
        this.type = type;
        setIconType();
        this.turnBool = turnBool;
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
    
    public String getButtonType(){
        return this.buttonType;
    }
    
    public int getType(){
        return this.type;
    }
    
    public char getIconType(){
        return this.iconType;
    }
    
    public void clicked(){
        if( this.turnBool ){
            for( TypeButton b : flag.getTypeList() )
                if( b.turnBool )
                    b.setIcon( new ButtonIcon( buttonSize ).getIcon( b.getButtonType() ) );
            this.setIcon( new ButtonIcon( buttonSize ).getIcon( buttonType + "Selected" ) );
            flag.setTypeSelected( this.type );
        }
    }
}
