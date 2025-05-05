
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ListFrame extends JFrame {
    private JList colorJList; //lista para exiber corres
    private static final String[] colorNames = { "Black", "Blue" , "Cyan", "Dark Gray", "Gray", "Green", "Magenta", "Orange", "Pink", "Red", "White", "Yellow" };
    private static final Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };
    
    public ListFrame(){
        super( "List Test");
        setLayout(new FlowLayout() );//
        colorJList = new JList( colorNames );//
        colorJList.setVisibleRowCount( 5 );
        //
        colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //
        add( new JScrollPane( colorJList));
        colorJList.addListSelectionListener( 
                new ListSelectionListener(){
                    public void valueChanged( ListSelectionEvent event){
                         getContentPane().setBackground( colors[colorJList.getSelectedIndex() ]);     
                    }
        
                }
        );
    }
}
    
        

