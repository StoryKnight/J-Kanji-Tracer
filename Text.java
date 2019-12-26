// Java program to create a blank text field with a  
// given initial text and given number of columns

// use javac -encoding UTF-8 text.java

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.Font;
import java.nio.charset.Charset;

class Text extends JFrame implements ActionListener {
    // JTextField
    // default constructor

    public static JTextField t;

    public static JTextField t2;

    // JFrame
    public static JFrame f;

    // JButton
    public static JButton b;

    public static JButton b2;

    // label to display text
    public static JLabel l;

    public static JLabel l2;

    public static HashMap dictionary;

    public static String str;

    Text() {
        // set this to initialize the dictionary

        Charset charset = Charset.forName("UTF-8");

        l.setFont(l.getFont().deriveFont((float) 16.0));

        try {
            File file = new File("C:\\Users\\Allahmath\\" +
                    "Desktop\\J-Kanji\\vocab.txt");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), charset));

            while ((str = in.readLine()) != null) {
                // System.out.println("READLINE: " + in.readLine());
                // System.out.println("STR Length: " + str.length());
                String key = "";
                String value = "";
                boolean found = false;

                for (int i = 0; i < str.length(); i++) {
                    // System.out.println(str.charAt(i));
                    if (str.charAt(i) != ':'
                            && found == false) key += str.charAt(i);
                    else if (str.charAt(i) != ':'
                            && found == true) value += str.charAt(i);
                    else if (str.charAt(i) == ':') found = true;
                }
                dictionary.put(key,value);
                found = false;
            }
            in.close();
        }
        catch(UnsupportedEncodingException e) { System.out.println(e.getMessage() + "boi1"); }
        catch (IOException e) { System.out.println(e.getMessage() + "boi2"); }
        catch (Exception e) { System.out.println(e.getMessage() + "boi3"); }

        // FileReader reader = new FileReader("vocab.txt");

        l.setText("Kanji : Definition"); // set up dictionary first
        // System.out.println(dictionary.display());
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("ENTER")) {
            l.setFont(l.getFont().deriveFont((float) 16.0));
            // System.out.println("DOING ENTER");
            // set the text of the label to the text of the field
            dictionary.put(t.getText(), t2.getText());
            WriteF writer = new WriteF();

            // make a second button


            try {
                writer.writeFile(); // calls dictionary to write
            }

            catch (IOException ee) {
                System.out.println("File unavailable.");
            }

            l.setText("Added Successfully!");

            // set the text of field to blank


            t2.setText("  ");
            t.setText("  ");
        }

        else if (s.equals("RANDOM")) {
            l.setText("");
            // System.out.println("DOING RANDOM");
            l.setFont(l.getFont().deriveFont((float) 128.0));

            String kanji = (String) dictionary.randomKey();

            l.setText(kanji);

            l2.setFont(l2.getFont().deriveFont((float) 16.0));

            String definition = (String) dictionary.get(kanji);
            l2.setText(definition);
            System.out.println(definition);

        }

        // main class
    }
}