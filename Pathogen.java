import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pathogen extends JFrame{
    
    private static int numButtons = 10;
    private static int buttonSize = 25;
    private static int currentSelectedVal;
    private static GameButton[][] buttonArray;


    public static void main(String[]args){
        try{ 
            numButtons = Integer.parseInt( args[0] ); 
            if(numButtons<5) numButtons = 5;
        } catch ( Exception e ){}
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
        int boardHeight = ( ( numButtons * buttonSize ) + ( numButtons+1 ) ); //sum of width of button + whitespace between buttons
        int whiteSpace = boardHeight - ( buttonSize * 4 );
        return ( whiteSpace / 5 );
    }

    private void createAndDisplayGUI(){
    
        TurnFlag flag = new TurnFlag( Color.black, this );
        buttonArray = new GameButton[numButtons*2][numButtons];
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        JPanel gameWindow = new JPanel();
        gameWindow.setLayout( new BorderLayout() );
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder( BorderFactory.createEmptyBorder() );
        menuBar.setBackground( Color.DARK_GRAY );
        
        JMenu fileMenu = new JMenu( "File" );
        fileMenu.setForeground( Color.WHITE );
                
        JMenuItem newGame = new JMenuItem( "New Game" );
        JMenuItem quit = new JMenuItem( "Quit" );
        quit.addActionListener( new ActionListener() { public void actionPerformed(ActionEvent e){ System.exit(0); } } );
        
        fileMenu.add( newGame );
        fileMenu.add( quit );
        menuBar.add( fileMenu );
        gameWindow.add( menuBar, BorderLayout.NORTH );
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        contentPane.setBackground( Color.BLACK );
        
        ArrayList<TypeButton> typeList = new ArrayList<TypeButton>();
        typeList.add( getTypeButton( "aRedCell",    "Selected", buttonSize, 1, flag, true, 0 ) );
        typeList.add( getTypeButton( "bRedCell",    "",         buttonSize, 2, flag, true, 3 ) );
        typeList.add( getTypeButton( "cRedCell",    "",         buttonSize, 3, flag, true, 7 ) );
        typeList.add( getTypeButton( "cureRedCell", "",         buttonSize, 0, flag, true, 7 ) );

        typeList.add( getTypeButton( "aBlueCell",    "Dark", buttonSize, 1, flag, false, 0 ) );        
        typeList.add( getTypeButton( "bBlueCell",    "Dark", buttonSize, 2, flag, false, 3 ) );        
        typeList.add( getTypeButton( "cBlueCell",    "Dark", buttonSize, 3, flag, false, 7 ) );        
        typeList.add( getTypeButton( "cureBlueCell", "Dark", buttonSize, 0, flag, false, 7 ) );        

        JPanel typeRedPane = new JPanel();
        typeRedPane.setBackground( Color.BLACK );
        typeRedPane.setLayout( new GridLayout( 4, 1, 0, getTypeButtonSpace() ) );
        JPanel typeBluePane = new JPanel();
        typeBluePane.setBackground( Color.BLACK );
        typeBluePane.setLayout( new GridLayout( 4, 1, 0, getTypeButtonSpace() ) );

        for( int i=0; i<typeList.size(); i++ )
            if( i<4 )
                typeRedPane.add( typeList.get( i ) );
            else
                typeBluePane.add( typeList.get ( i ) );
        
        JPanel buttonPane = getButtonPane( flag );

        flag.addTypeList( typeList );
        flag.setButtonArray( buttonArray, ( numButtons * numButtons * 2 ) );
        contentPane.add( typeRedPane );
        contentPane.add( buttonPane );
        contentPane.add( typeBluePane );
        gameWindow.add( contentPane, BorderLayout.SOUTH );
        setContentPane( gameWindow );
        
        pack();
        setLocationByPlatform( false );
        setVisible( true );
        setResizable( false );
    }
    
    private TypeButton getTypeButton( String name, String suffix, int size, int strength, TurnFlag flag, boolean turn, int cooldown ){
        final TypeButton b = new TypeButton(name, size, strength, flag, turn, cooldown);
        b.setIcon( new ButtonIcon( size ).getIcon( name + suffix ) );
        b.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ b.clicked(); } });
        return b;
    }
    
    private JPanel getButtonPane( TurnFlag flag ){
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground( Color.BLACK );
        buttonPane.setLayout( new GridLayout( numButtons, numButtons, 1, 1 ) );
        for( int y=0; y<numButtons; y++ ){
            ArrayList<JButton> list = new ArrayList<JButton>();
            for( int x=0; x<numButtons*2; x++ ){
                final GameButton button = new GameButton( 0, buttonSize, x, y, buttonArray, numButtons, flag, this );
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        //Execute when button is pressed
                        button.clicked();
                    }
                });   
                buttonPane.add( button );
                buttonArray[x][y] = button;
            }

        }
        return buttonPane;
    }
}
