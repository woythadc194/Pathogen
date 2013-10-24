import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pathogen extends JFrame{
    
    private static int numCells = 10;
    private static int buttonSize = 25;
    private static int boardWidth;
    private static int boardHeight;
    private static int currentSelectedVal;
    private static ArrayList<ArrayList<JButton>> buttonList;

    public static void main(String[]args){
        try{ numCells = Integer.parseInt( args[0] ); } catch ( Exception e ){}
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Pathogen().createAndDisplayGUI();
                gameFunct();
            }
        });
    }
    
    public Pathogen(){
        super( "Pathogen" );
    }

    private void createAndDisplayGUI(){
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        JPanel contentPane = new JPanel();
        contentPane.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        
        JPanel typePane = new JPanel();
        typePane.setLayout( new FlowLayout( FlowLayout.LEFT ) );
        contentPane.setBorder( BorderFactory.createLineBorder( Color.DARK_GRAY, 2 ) );
        JButton b = new JButton("eCell.jpg");
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new GridLayout( numCells, numCells, 1, 1 ) );
        
        for( int x=0; x<numCells*2; x++ ){
            ArrayList<JButton> list = new ArrayList<JButton>();
            for( int y=0; y<numCells; y++ ){
                /*
                ImageIcon icon = new ImageIcon( "eCell.jpg" );
                Image img = icon.getImage() ;  
                Image newimg = img.getScaledInstance( buttonSize, buttonSize,  java.awt.Image.SCALE_FAST ) ;  
                icon = new ImageIcon( newimg );
                */
                final GameButton button = new GameButton( 0, buttonSize, x, y );// eIcon );
                button.setBackground( Color.black );
                button.setPreferredSize( new Dimension( buttonSize, buttonSize ) );
                button.setBorder( BorderFactory.createEmptyBorder() );
                button.addActionListener(new ActionListener() {
 
                    public void actionPerformed(ActionEvent e){
                        //Execute when button is pressed
                        if(button.getBackground()==Color.black)
                            button.setBackground(Color.red);
                        else if(button.getBackground()==Color.red)
                            button.setBackground(Color.blue);
                        else if(button.getBackground()==Color.blue)
                            button.setBackground(Color.green);
                        button.printStats();
                    }
                });   
                buttonPanel.add( button );
                //list.add(button);
            }
            //buttonList.add(list);
        }
        contentPane.add( buttonPanel );
        setContentPane( contentPane );
        pack();
        setLocationByPlatform( true );
        setVisible( true );
    }
    
    private static void gameFunct(){

    }
}
