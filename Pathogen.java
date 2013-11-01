import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pathogen extends JFrame{
    
    private static int numButtons = 12;
    private static int buttonSize = 50;
    private static int currentSelectedVal;
    private static ArrayList<ArrayList<GameButton>> buttonList;

    public static void main(String[]args){
        try{ numButtons = Integer.parseInt( args[0] ); } catch ( Exception e ){}
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Pathogen().createAndDisplayGUI();
            }
        });
    }
    
    public Pathogen(){
        super( "Pathogen" );
    }
    
    private int getTypeButtonSpace(){
        int boardHeight = ( ( numButtons * buttonSize ) + ( numButtons+1 ) );
        int whiteSpace = boardHeight - ( buttonSize * 3 );
        return ( whiteSpace / 4 );
    }

    private void createAndDisplayGUI(){
        ArrayList<ArrayList<GameButton>> buttonList = new ArrayList<ArrayList<GameButton>> ();
        for( int x=0; x<numButtons; x++)
            buttonList.add(new ArrayList<GameButton>());
    
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        JPanel contentPane = new JPanel();
        contentPane.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        
        JPanel typePane = new JPanel();
        ArrayList<TypeSelectorButton> typeList = new ArrayList<TypeSelectorButton>();
        typePane.setLayout( new GridLayout( 3, 1, 0, getTypeButtonSpace() ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        TypeSelectorButton b = new TypeSelectorButton("aRedCell", buttonSize, 1);
        typePane.add( b );
        typeList.add( b );
        b = new TypeSelectorButton("bRedCell", buttonSize, 2);
        typePane.add( b );
        typeList.add( b );
        b = new TypeSelectorButton("cRedCell", buttonSize, 3);
        typePane.add( b );
        typeList.add( b );
        contentPane.add( typePane );
        
        final TurnFlag flag = new TurnFlag( Color.RED, typeList, this );
        
        JPanel undoPane = new JPanel();
        undoPane.setLayout( new GridLayout( 1, 1, 1, 1 ) );
        undoPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        JButton undoButton = new JButton("UNDO");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                flag.undoTurn();
            }
        }); 
        undoPane.add( undoButton );

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new GridLayout( numButtons, numButtons, 1, 1 ) );
        

        for( int y=0; y<numButtons; y++ ){
            ArrayList<JButton> list = new ArrayList<JButton>();
            for( int x=0; x<numButtons; x++ ){
                final GameButton button = new GameButton( 0, buttonSize, x, y, buttonList, numButtons, flag );
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        //Execute when button is pressed
                        button.clicked();
                    }
                });   
                buttonPanel.add( button );
                buttonList.get(x).add(button);
            }
        }
        flag.setButtonList( buttonList, ( numButtons * numButtons ) );
        contentPane.add( buttonPanel );
        contentPane.add( undoPane );
        setContentPane( contentPane );
        pack();
        setLocationByPlatform( false );
        setVisible( true );
        setResizable( false );
    }
}
