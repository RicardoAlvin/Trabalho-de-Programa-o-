/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Aluno
 */
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class newJavaFile extends JFrame  {
    
        private  JLabel label1;
       
        
        public newJavaFile(){
            super ("Testing JLabel");
            setLayout (new FlowLayout());
            
            Icon bug = new ImageIcon (getClass().getResource("bug1.png"));
            label1 = new JLabel();
            label1.setText("INSTITUTO FEDERAL DE EDUCACAO, CIENCIA E TECNOLOGIA DE MATO GROSSO");
            label1.setIcon(bug);
            label1.setHorizontalTextPosition(SwingConstants.CENTER);
            label1.setVerticalTextPosition(SwingConstants.BOTTOM);
            label1.setToolTipText("Logo do IFMT");
            add (label1);
            
        }
}

