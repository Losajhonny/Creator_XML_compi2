/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creator_xml;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ScrollDemo extends JFrame {

  JScrollPane scrollpane;

  public ScrollDemo() {
    super("JScrollPane Demonstration");
    setSize(300, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    init();
    setVisible(true);
  }

  public void init() {
    
    JPanel p = new JPanel(new FlowLayout());
    p.setSize(300, 200);
    
    
    JLabel et1 = new JLabel("hola prueba");
    et1.setBounds(200, 150, 300, 70);
    p.add(et1);
    
    
    scrollpane = new JScrollPane(p);
    getContentPane().add(scrollpane, BorderLayout.CENTER);
    
    
  }

  public static void main(String args[]) {
    new ScrollDemo();
  }
}