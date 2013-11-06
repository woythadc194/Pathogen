import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pathogen extends JFrame{
    
    private static int numButtons = 10;
    private static int buttonSize = 35;
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

        JPanel contentPane = new JPanel();
        contentPane.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        contentPane.setBackground( Color.BLACK );
        
        ArrayList<TypeButton> typeList = new ArrayList<TypeButton>();
        JPanel typeRedPane = new JPanel();
        typeRedPane.setBackground( Color.BLACK );
        typeRedPane.setLayout( new GridLayout( 4, 1, 0, getTypeButtonSpace() ) );
        final TypeButton aRed = new TypeButton( "aRedCell", buttonSize, 1, flag, true, 0 );
        aRed.setIcon( new ButtonIcon( buttonSize ).getIcon( "aRedCellSelected" ) );
        aRed.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ aRed.clicked(); } }); 
        typeRedPane.add( aRed );
        typeList.add( aRed );
        final TypeButton bRed = new TypeButton( "bRedCell", buttonSize, 2, flag, true, 3 );
        bRed.setIcon( new ButtonIcon( buttonSize ).getIcon( "bRedCell" ) );
        bRed.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ bRed.clicked(); } });
        typeRedPane.add( bRed );
        typeList.add( bRed );
        final TypeButton cRed = new TypeButton( "cRedCell", buttonSize, 3, flag, true, 7 );
        cRed.setIcon( new ButtonIcon( buttonSize ).getIcon( "cRedCell" ) );
        cRed.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ cRed.clicked(); } });
        typeRedPane.add( cRed );
        typeList.add( cRed );
        final TypeButton cureRed = new TypeButton( "cureRedCell", buttonSize, 0, flag, true, 7 );
        cureRed.setIcon( new ButtonIcon( buttonSize ).getIcon( "cureRedCell" ) );
        cureRed.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ cureRed.clicked(); } });
        typeRedPane.add( cureRed );
        typeList.add( cureRed );
        
        JPanel typeBluePane = new JPanel();
        typeBluePane.setBackground( Color.BLACK );
        typeBluePane.setLayout( new GridLayout( 4, 1, 0, getTypeButtonSpace() ) );
        final TypeButton aBlue = new TypeButton( "aBlueCell", buttonSize, 1, flag, false, 0 );
        aBlue.setIcon( new ButtonIcon( buttonSize ).getIcon( "aBlueCellDark" ) );
        aBlue.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ aBlue.clicked(); } }); 
        typeBluePane.add( aBlue );
        typeList.add( aBlue );
        final TypeButton bBlue = new TypeButton( "bBlueCell", buttonSize, 2, flag, false, 3 );
        bBlue.setIcon( new ButtonIcon( buttonSize ).getIcon( "bBlueCellDark" ) );
        bBlue.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ bBlue.clicked(); } });
        typeBluePane.add( bBlue );
        typeList.add( bBlue );
        final TypeButton cBlue = new TypeButton( "cBlueCell", buttonSize, 3, flag, false, 7 );
        cBlue.setIcon( new ButtonIcon( buttonSize ).getIcon( "cBlueCellDark" ) );
        cBlue.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ cBlue.clicked(); } });
        typeBluePane.add( cBlue );
        typeList.add( cBlue );
        final TypeButton cureBlue = new TypeButton( "cureBlueCell", buttonSize, 0, flag, false, 7 );
        cureBlue.setIcon( new ButtonIcon( buttonSize ).getIcon( "cureBlueCellDark" ) );
        cureBlue.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){ cureBlue.clicked(); } });
        typeBluePane.add( cureBlue );
        typeList.add( cureBlue );
        
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

        flag.addTypeList( typeList );
        flag.setButtonArray( buttonArray, ( numButtons * numButtons * 2 ) );
        contentPane.add( typeRedPane );
        contentPane.add( buttonPane );
        contentPane.add( typeBluePane );
        setContentPane( contentPane );
        pack();
        setLocationByPlatform( false );
        setVisible( true );
        setResizable( false );
    }
}
