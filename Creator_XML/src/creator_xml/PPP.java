/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creator_xml;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import javafx.scene.control.Spinner;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class PPP extends JFrame {
    
    public static void main(String[] args) {
        PPP p = new PPP();
        p.setVisible(true);
        
    }
    
    public PPP()
    {
//        makeFrame1();
        makeFrame();
        makeEvent();
    }
    
    public void makeEvent()
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent evt) {
                System.out.println("activated");
            }
            
            @Override
            public void windowStateChanged(WindowEvent e) {
                System.out.println("statechange");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("closed");
            }
            
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("opened");
            }
            
        });
    }
    
    private void makeFrame()
    {
        /*PANEL DEL JFRAME*/
        setTitle("hola");
        setPreferredSize(new Dimension(900, 900));
        
        JPanel contentPane = (JPanel)this.getContentPane();
        
        /*AÑADIMOS UN SCROLLPANE AL PANEL DEL JFRAME*/
        JPanel panel = new JPanel(null);    //PANEL DEL SCROLLPANE DONDE SE VA A TRABAJAR
        panel.setPreferredSize(new Dimension(1500, 1500));
        
        /* INICIO DE LOS COMPONENETES A AGREGAR HACIENDO USO DEL PANEL DEL SCROLLPANE */
        
        JPanel cont1 = new JPanel(null);
        cont1.setBounds(500, 400, 500, 500);
        cont1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        panel.add(cont1);
        
        
        JLabel eti1 = new JLabel("Probando");
        eti1.setBounds(10, 20, 100, 30);
        panel.add(eti1);
        
        
        JTextField txt = new JTextField("defecto");
        txt.setBounds(10, 10, 100, 30);
        txt.setForeground(Color.red);
        cont1.add(txt);
        
        JTextArea jta = new JTextArea();
        jta.setBounds(10, 50, 100, 50);
        
        JScrollPane jspn1 = new JScrollPane(jta);
        panel.add(jspn1);
        jspn1.setBounds(10, 50, 100, 50);
        
        
//        JSpinner nn1 = new JSpinner(new SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(10.0f), Float.valueOf(1.0f)));
//        nn1.setBounds(100, 10, 50, 30);
//        panel.add(nn1);
        
        JSpinner nn2 = new JSpinner();
        
        nn2.setFont(new Font(Constante.FUENTE, Font.PLAIN, Integer.parseInt(Constante.TAM)));
        nn2.setBounds(100, 10, 50, 30);
        nn2.setForeground(Color.decode(Constante.COLOR_NEGRO));
        
        SpinnerNumberModel snm = new SpinnerNumberModel();
        snm.setStepSize((double)1.0);
        snm.setValue((double)0);
        snm.setMaximum((double)6000.0);
        snm.setMinimum((double)0);
        nn2.setModel(snm);
        panel.add(nn2);
        /*FIN DEL INGRESO DE LOS COMPONENTES*/
        
//        panel.setPreferredSize(new Dimension(900, 900));
        /*AGREGANDO SCROLLPANE AL PANEL DEL JFRAME*/
        
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(0, 0, 900, 900);
        contentPane.add(scroll);
        
        pack();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        
    }
    
    public void makeFrame1()
    {
        
//        setLayout(null);
        setSize(900, 900);
        
        JScrollPane jsp = new JScrollPane();
        JPanel panel = new JPanel(null);
        
        /*CONTENEDOR*/
        JPanel jp1 = new JPanel(null);
        jp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jp1.setBounds(80, 100, 700, 700);
        jp1.setPreferredSize(new Dimension(700, 700));
        jp1.setBackground(Color.decode("#ffffff"));
        
        
        JLabel jl1 = new JLabel();
        jl1.setBounds(450, 20, WIDTH, HEIGHT);
        jl1.setText("Bienvenido a la prueba de Aritmetica, responda las siguientes preguntas");
        panel.add(jl1);
        
        JLabel jl2 = new JLabel();
        jl2.setBounds(10, 50, jl1.getWidth(), jl1.getHeight());
        jl2.setText("Bienvenido a la prueba de Aritmetica, responda las siguientes preguntas");
        jp1.add(jl2);
        
        JTextField jtf = new JTextField("hola");
        jtf.setPreferredSize(new Dimension(100, 50));
        jtf.setBounds(100,150,100,30);
        
        jp1.add(jtf);
        
        
        
        panel.setPreferredSize(new Dimension(900, 900));
        jsp.setViewportView(panel);
        panel.add(jp1);
        
        
        
        add(jsp);
//        
//        this.getContentPane().add(jsp, BorderLayout.CENTER);
//        this.setPreferredSize(new Dimension(900, 900));
//        
//        JPanel nu = new JPanel(new BorderLayout());
//        
//        nu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
//        nu.setBounds(100, 100, nu.getWidth(), nu.getHeight());
//        
//        nu.setSize(400, 1000);
//        nu.setBackground(Color.decode("#800000"));
//        panel.add(nu);
//        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
}
