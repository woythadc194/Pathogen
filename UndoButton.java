import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UndoButton extends JButton{

    private static JFrame frame;
    private static GameButton[][] buttonArray;

    public UndoButton(String s, JFrame frame, GameButton[][] buttonArray ){
        super(s);
        this.frame = frame;
        this.buttonArray = buttonArray;
    }
    
    public void clicked(){
        for( int i=0; i<buttonArray.length; i++)
            for( int j=0; j<buttonArray[i].length; i++)
                ;
    }
}
