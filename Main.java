import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComponent;
import java.awt.GridLayout;

public class Main {

    public static void main(String[] args) {

        Text.dictionary = new HashMap();

        // create a new frame to store text field and button
        Text.f = new JFrame("J-Kanji");

        // create a label to display text
        Text.l = new JLabel("Enter text here: ");

        Text.l2 = new JLabel("Enter description here: ");

        // create a new button
        Text.b = new JButton("ENTER");

        Text.b2 = new JButton("RANDOM");

        // create a object of the text class
        Text te = new Text();

        // addActionListener to button
        Text.b.addActionListener(te);
        Text.b2.addActionListener(te);

        // create a object of JTextField with 16 columns and a given initial text
        Text.t = new JTextField("かんじ", 16);

        Text.t2 = new JTextField("Definitions: ", 16);

        // create a panel to add buttons and textfield
        JPanel p = new JPanel();

        // add buttons and textfield to panel

        // p.setLayout(new GridLayout(0,1));
        // p.add(new JLabel("Enter text here: ", SwingConstants.Center))

        p.add(Text.t);
        p.add(Text.t2);
        p.add(Text.b);
        p.add(Text.b2);
        p.add(Text.l);
        p.add(Text.l2);

        // add panel to frame
        Text.f.add(p);

        // set the size of frame
        Text.f.setSize(400, 600);

        Text.f.show();
    }

    // if the button is pressed
}