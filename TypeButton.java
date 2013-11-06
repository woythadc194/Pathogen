import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TypeButton extends JButton{
    
    public boolean turnBool;
    public int cooldown;
    private int cooldownType;
    private static int buttonSize;
    private int type;
    private char iconType;
    private static TurnFlag flag;
    private String buttonType;
    
    public TypeButton( String buttonType, int buttonSize, int type, TurnFlag flag, boolean turnBool, int cooldownType ){
        super();
        this.flag = flag;
        this.cooldownType = cooldownType;
        this.cooldown = 0;
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
    
    public void resetCooldown(){
        this.cooldown = this.cooldownType;
    }
    
    public int getCooldownType(){
        return this.cooldownType;
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
        if( this.turnBool && this.cooldown==0 ){
            for( TypeButton b : flag.getTypeList() )
                if( b.turnBool )
                    if( b.cooldown == 0 )
                        b.setIcon( new ButtonIcon( buttonSize ).getIcon( b.getButtonType() ) );
                    else 
                        b.setIcon( new ButtonIcon( buttonSize ).getIcon( b.getButtonType() + b.cooldown) );
            this.setIcon( new ButtonIcon( buttonSize ).getIcon( buttonType + "Selected" ) );
            flag.setTypeSelected( this.type );
        }
    }
}
