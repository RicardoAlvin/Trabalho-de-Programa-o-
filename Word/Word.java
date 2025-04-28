/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Ricardo
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Ricardo
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class Word extends JFrame {

    private JTextPane textPane;
    private StyledDocument doc;
    private SimpleAttributeSet attributes;

    public Word() {
        super("Mini Word Editor");

        setLayout(new BorderLayout());

        textPane = new JTextPane();
        doc = textPane.getStyledDocument();
        attributes = new SimpleAttributeSet();
        textPane.setCharacterAttributes(attributes, true);

        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());

        
        JCheckBox boldBox = new JCheckBox("Bold");
        JCheckBox italicBox = new JCheckBox("Italic");
        JCheckBox underlineBox = new JCheckBox("Underline");

        
        JButton alignLeft = new JButton("Left");
        JButton alignCenter = new JButton("Center");
        JButton alignRight = new JButton("Right");

        
        String[] colors = {"Black", "Red", "Blue", "Green", "pink"};
        JComboBox<String> colorBox = new JComboBox<>(colors);

        
        boldBox.addItemListener(e -> {
            StyleConstants.setBold(attributes, boldBox.isSelected());
            updateTextStyle();
        });

        italicBox.addItemListener(e -> {
            StyleConstants.setItalic(attributes, italicBox.isSelected());
            updateTextStyle();
        });

        underlineBox.addItemListener(e -> {
            StyleConstants.setUnderline(attributes, underlineBox.isSelected());
            updateTextStyle();
        });

        
        alignLeft.addActionListener(e -> setAlignment(StyleConstants.ALIGN_LEFT));
        alignCenter.addActionListener(e -> setAlignment(StyleConstants.ALIGN_CENTER));
        alignRight.addActionListener(e -> setAlignment(StyleConstants.ALIGN_RIGHT));

        
        colorBox.addActionListener(e -> {
            String selected = (String) colorBox.getSelectedItem();
            Color color = switch (selected) {
                case "Red" -> Color.RED;
                case "Blue" -> Color.BLUE;
                case "Green" -> Color.GREEN;
                case "pink" -> Color.pink;
                default -> Color.BLACK;
            };
            StyleConstants.setForeground(attributes, color);
            updateTextStyle();
        });

        
        controlPanel.add(boldBox);
        controlPanel.add(italicBox);
        controlPanel.add(underlineBox);
        controlPanel.add(alignLeft);
        controlPanel.add(alignCenter);
        controlPanel.add(alignRight);
        controlPanel.add(new JLabel("Color:"));
        controlPanel.add(colorBox);

        add(controlPanel, BorderLayout.NORTH);
    }

    
    private void updateTextStyle() {
        textPane.setCharacterAttributes(attributes, true);
    }

   
    private void setAlignment(int alignment) {
        SimpleAttributeSet alignmentAttr = new SimpleAttributeSet();
        StyleConstants.setAlignment(alignmentAttr, alignment);
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        doc.setParagraphAttributes(start, end - start, alignmentAttr, false);
    }
}
