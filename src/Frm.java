
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Frm {
    public static void main(String args[]){
        JFrame frame=new JFrame("Hello");
        frame.setSize(640,480);
        JPanel panel=new JPanel();
        frame.add(panel);
        frame.setLayout(new GridLayout());
        frame.pack();
        frame.setVisible(true);
        Vector textFieldVector=new Vector();
        JTextField tf;
        int i=0;
        while(i<3){
            tf=new JTextField();
            textFieldVector.add(tf);
            panel.add(tf);
            i++;            
        }
        panel.validate();
        panel.repaint();
    }
    
}
