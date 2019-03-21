/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creator_xml;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;
import otros.Constante;

/**
 *
 * @author Jhona
 */
public class principal extends javax.swing.JFrame {

    public boolean ideclose;
    
    /**
     * Creates new form principal
     */
    public principal() {
        ideclose = false;
        initComponents();
        Constante.consola = "";
        Constante.errores.clear();
        addTab("Archivo_1", Constante.GXML);
        addTab("Archivo_2", Constante.FS);
        Constante.consolaTexto = consola;
        crearSalida();
        actualizarTabla();
    }
    
    public static void actualizarTabla()
    {
        String columns[] = { "Fecha y Hora", "Lenguaje", "Tipo", "Ambito", "Archivo", "Descripcion", "Linea", "Columna" };
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(columns);
        
        for (otros.Error err : Constante.errores) {
            String row[] = { err.fecha_hora, err.lenguaje, 
                err.tipo, err.ambito, err.archivo, 
                err.descripcion, String.valueOf(err.line), String.valueOf(err.colm) };
            dtm.addRow(row);
        }
        
        tabla_error.setModel(dtm);
        
        tabla_error.getColumn(columns[0]).setResizable(false);  //fecha hora
        tabla_error.getColumn(columns[0]).setPreferredWidth(100);
        
        tabla_error.getColumn(columns[1]).setResizable(false);  //lenguaje
        tabla_error.getColumn(columns[1]).setPreferredWidth(80);
        
        tabla_error.getColumn(columns[2]).setResizable(false);  //tipo
        tabla_error.getColumn(columns[2]).setPreferredWidth(90);
        
        tabla_error.getColumn(columns[3]).setResizable(false);  //ambito
        tabla_error.getColumn(columns[3]).setPreferredWidth(90);
        
        tabla_error.getColumn(columns[4]).setResizable(false);  //archivo
        tabla_error.getColumn(columns[4]).setPreferredWidth(180);
        
        tabla_error.getColumn(columns[5]).setResizable(false);  //descripcion
        tabla_error.getColumn(columns[5]).setPreferredWidth(700);
        
        tabla_error.getColumn(columns[6]).setResizable(false);  //linea
        tabla_error.getColumn(columns[6]).setPreferredWidth(70);
        
        tabla_error.getColumn(columns[7]).setResizable(false);  //columna
        tabla_error.getColumn(columns[7]).setPreferredWidth(70);
    }
    
    private void crearSalida()
    {
        salida_control.setTitleAt(0, "Consola");
        salida_control.setTitleAt(1, "Errores");
    }
    
    public void opcionCrearTab(){
        //debo de pedir el nombre del archivo y su extension
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del archivo", 
                " Nombre Tab", JOptionPane.INFORMATION_MESSAGE);
        
        if(nombre != null){
            if(!nombre.equals(""))
            {
                String extension = (String)JOptionPane.showInputDialog(null, 
                    "Seleccione el tipo de archivo", "Extension", JOptionPane.INFORMATION_MESSAGE, 
                    null, Constante.OPTIONS_NAME, Constante.OPTIONS_NAME[0]);

                extension = (extension.equals(Constante.OPTIONS_NAME[0]))? Constante.OPTIONS_EXT[0]:Constante.OPTIONS_EXT[1];

                addTab(nombre, extension);
            }
            else { JOptionPane.showMessageDialog(null, "Debe ingresar el nombre", "Informe de error", JOptionPane.ERROR_MESSAGE); }
        }
    }
    
    private void addTab(String nombre, String extension){
        AbstractTokenMakerFactory atmf1 = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf1.putMapping("GxmlSyntax", "gxml.analizador.GxmlSyntax");
        AbstractTokenMakerFactory atmf2 = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf2.putMapping("FsSyntax", "fs.analizador.FsSyntax");
        //Colocar area de texto
        RSyntaxTextArea rsta = new RSyntaxTextArea();
        
        if(extension.equals(Constante.OPTIONS_EXT[0])){
            //gxml
            rsta.setSyntaxEditingStyle("GxmlSyntax");
        }
        else
        {
             rsta.setSyntaxEditingStyle("FsSyntax");
        }
        //Colocar un scrollpane
        RTextScrollPane rtsp = new RTextScrollPane(rsta, true);
        rtsp.setFoldIndicatorEnabled(true);
        rtsp.setIconRowHeaderEnabled(true);
        
        if(extension.equals(Constante.OPTIONS_EXT[0]) || extension.equals(Constante.OPTIONS_EXT[1])){
            SyntaxScheme scheme = rsta.getSyntaxScheme();
            scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
            scheme.getStyle(Token.FUNCTION).foreground = Color.BLUE;
            
            scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;
            scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.ORANGE;
            
            scheme.getStyle(Token.IDENTIFIER).foreground = Color.BLACK;
            scheme.getStyle(Token.DATA_TYPE).foreground = Color.BLUE;
            scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
            scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
            scheme.getStyle(Token.SEPARATOR).foreground = Color.black;
        }
        
        JPanel pan = new JPanel(new BorderLayout());
        pan.add(rtsp, BorderLayout.CENTER);
        
        String nameTab = nombre + "." + extension;
        tabcontrol.addTab(nameTab, pan);
        tabcontrol.setSelectedIndex(tabcontrol.getTabCount()-1);
    }
    
    public void interpretar(){
        Constante.consola = "";
        Constante.errores.clear();
        actualizarTabla();
        
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-información-del-sistema-48.png"))); // NOI18N
        
        String nombre = tabcontrol.getTitleAt(tabcontrol.getSelectedIndex());
        
        JPanel pan =(JPanel)(tabcontrol.getSelectedComponent());
        RTextScrollPane rtsp = (RTextScrollPane) pan.getComponent(0);
        RSyntaxTextArea texto = (RSyntaxTextArea) rtsp.getTextArea();
        
        if(nombre.endsWith(".gxml")){
            Creator_XML.analizarGXML(nombre, texto.getText());
        }
        else{
            Creator_XML.analizarFS(nombre, texto.getText());
        }
        
        if(Constante.errores.size() > 0)
        {
            jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-informe-del-sistema-48.png"))); // NOI18N
        }
        
        consola.setText(Constante.consola);
    }
    
    public void guardar(){
        String path = Constante.path_relativa + tabcontrol.getTitleAt(tabcontrol.getSelectedIndex());
        File f = new File(path);
        
        if (f.exists()){
            /*obtengo los datos*/
                JPanel pan =(JPanel)(tabcontrol.getSelectedComponent());
                RTextScrollPane rtsp = (RTextScrollPane) pan.getComponent(0);
                RSyntaxTextArea texto = (RSyntaxTextArea) rtsp.getTextArea();
                
                Creator_XML.crearArchivo(path, texto.getText());
        }
        else{
            guardarComo();
        }
    }

    public void guardarComo(){
        /*carpeta donde se almacenara los archivos*/
        File f = new File("archivos");
        /*filtro de extensiones .gxml .fs*/
        FileFilter ff1 = new FileNameExtensionFilter("Generic XML", "gxml");
        FileFilter ff2 = new FileNameExtensionFilter("FuncionScript", "fs");
        /*creando filechooser*/
        JFileChooser jfc = new JFileChooser(f);
        jfc.removeChoosableFileFilter(jfc.getFileFilter());
        /*colocando generic xml pos 0*/
        jfc.setFileFilter(ff1);
        /*agregando mas filtros*/
        jfc.addChoosableFileFilter(ff2);
        /*denegar varios archivos*/
        jfc.setMultiSelectionEnabled(false);
        /*colocando nombre default al archivo*/
        jfc.setSelectedFile(new File(tabcontrol.getTitleAt(tabcontrol.getSelectedIndex())));
        
        /*obtener la ruta para guardarlo*/
        int value = jfc.showSaveDialog(null);
        
        if(value == JFileChooser.APPROVE_OPTION){
            /*obteniendo extension del archivo*/
            FileNameExtensionFilter tmp = (FileNameExtensionFilter)jfc.getFileFilter();
            String parent = jfc.getSelectedFile().getParent();
            
            if(parent.endsWith("archivos")){
                /*aqui debo de guardar el archivo en la carpeta principal*/
                JPanel pan =(JPanel)(tabcontrol.getSelectedComponent());
                RTextScrollPane rtsp = (RTextScrollPane) pan.getComponent(0);
                RSyntaxTextArea texto = (RSyntaxTextArea) rtsp.getTextArea();
                
                /*obteniendo path*/
                String path = (jfc.getSelectedFile().getName().endsWith(".gxml"))?
                        jfc.getSelectedFile().getName():
                        (jfc.getSelectedFile().getName().endsWith(".fs"))?
                        jfc.getSelectedFile().getName():
                        jfc.getSelectedFile().getName()+"."+tmp.getExtensions()[0];
                
                tabcontrol.setTitleAt(tabcontrol.getSelectedIndex(), path);
                
                Creator_XML.crearArchivo(Constante.path_relativa + path, texto.getText());
                JOptionPane.showMessageDialog(null, "Archivo guardado en:\n" + path, "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "El archivo tiene que ser guardado\nen la carpeta principal \"archivos\"", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void abrir(){
        /*carpeta donde se almacenara los archivos*/
        File f = new File("archivos");
        /*filtro de extensiones .gxml .fs*/
        FileFilter ff1 = new FileNameExtensionFilter("Generic XML", "gxml");
        FileFilter ff2 = new FileNameExtensionFilter("FuncionScript", "fs");
        /*creando filechooser*/
        JFileChooser jfc = new JFileChooser(f);
        jfc.removeChoosableFileFilter(jfc.getFileFilter());
        /*colocando generic xml pos 0*/
        jfc.setFileFilter(ff1);
        /*agregando mas filtros*/
        jfc.addChoosableFileFilter(ff2);
        /*denegar varios archivos*/
        jfc.setMultiSelectionEnabled(false);
        /*colocando nombre default al archivo*/
        //jfc.setSelectedFile(new File(tabcontrol.getTitleAt(tabcontrol.getSelectedIndex())));
        
        /*obtener la ruta para guardarlo*/
        int value = jfc.showOpenDialog(null);
        
        if(value == JFileChooser.APPROVE_OPTION){
            /*obteniendo extension del archivo*/
            FileNameExtensionFilter tmp = (FileNameExtensionFilter)jfc.getFileFilter();
            String parent = jfc.getSelectedFile().getParent();
            
            
            if(parent.endsWith("archivos")){
                /*aqui debo de guardar el archivo en la carpeta principal*/
                JPanel pan =(JPanel)(tabcontrol.getSelectedComponent());
                RTextScrollPane rtsp = (RTextScrollPane) pan.getComponent(0);
                RSyntaxTextArea texto = (RSyntaxTextArea) rtsp.getTextArea();
                
                /*obteniendo path*/
                String path = (jfc.getSelectedFile().getName().endsWith(".gxml"))?
                        jfc.getSelectedFile().getName():
                        (jfc.getSelectedFile().getName().endsWith(".fs"))?
                        jfc.getSelectedFile().getName():
                        jfc.getSelectedFile().getName()+"."+tmp.getExtensions()[0];
                
                tabcontrol.setTitleAt(tabcontrol.getSelectedIndex(), path);
                
                if(jfc.getSelectedFile().getName().endsWith(".gxml"))
                {
                    texto.setSyntaxEditingStyle("GxmlSyntax");
                }
                else
                {
                    texto.setSyntaxEditingStyle("FsSyntax");
                }
                
                texto.setText(Creator_XML.leerArchivo(Constante.path_relativa + path));
            }
            else{
                JOptionPane.showMessageDialog(null, "El archivo tiene que estar\nen la carpeta principal \"archivos\"", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_compilar = new javax.swing.JButton();
        tabcontrol = new javax.swing.JTabbedPane();
        salida_control = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_error = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btn_abrir = new javax.swing.JMenuItem();
        btn_guardar = new javax.swing.JMenuItem();
        btn_guardarcomo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btn_nuevo = new javax.swing.JMenuItem();
        btn_cerrar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btn_report = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ExtremeEditor");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btn_compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-reproducir-en-círculo-48.png"))); // NOI18N
        btn_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_compilarActionPerformed(evt);
            }
        });

        tabcontrol.setToolTipText("");

        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane1.setViewportView(consola);

        salida_control.addTab("tab1", jScrollPane1);

        tabla_error.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_error);

        salida_control.addTab("tab2", jScrollPane2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-carpeta-de-documentos-48.png"))); // NOI18N
        jMenu1.setText("Archivo");

        btn_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-abrir-documento-48.png"))); // NOI18N
        btn_abrir.setText("Abrir");
        btn_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abrirActionPerformed(evt);
            }
        });
        jMenu1.add(btn_abrir);

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-guardar-48.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jMenu1.add(btn_guardar);

        btn_guardarcomo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-guardar-como-48.png"))); // NOI18N
        btn_guardarcomo.setText("Guardar Como");
        btn_guardarcomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarcomoActionPerformed(evt);
            }
        });
        jMenu1.add(btn_guardarcomo);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-papel-48.png"))); // NOI18N
        jMenu2.setText("Pestaña");

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-agregar-archivo-48.png"))); // NOI18N
        btn_nuevo.setText("Nuevo Tab");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        jMenu2.add(btn_nuevo);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-eliminar-archivo-48.png"))); // NOI18N
        btn_cerrar.setText("Cerrar Tab");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });
        jMenu2.add(btn_cerrar);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-información-del-sistema-48.png"))); // NOI18N
        jMenu3.setText("Errores");

        btn_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-error-48.png"))); // NOI18N
        btn_report.setText("Reporte Errores");
        btn_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportActionPerformed(evt);
            }
        });
        jMenu3.add(btn_report);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabcontrol)
                    .addComponent(salida_control, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_compilar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_compilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabcontrol, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salida_control, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        opcionCrearTab();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_compilarActionPerformed
        // TODO add your handling code here:
        interpretar();
    }//GEN-LAST:event_btn_compilarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_guardarcomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarcomoActionPerformed
        // TODO add your handling code here:
        guardarComo();
    }//GEN-LAST:event_btn_guardarcomoActionPerformed

    private void btn_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abrirActionPerformed
        // TODO add your handling code here:
        abrir();
    }//GEN-LAST:event_btn_abrirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        ideclose = true;
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ideclose = true;
    }//GEN-LAST:event_formWindowClosing

    private void btn_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportActionPerformed
        // TODO add your handling code here:
        salida_control.setSelectedIndex(1);
    }//GEN-LAST:event_btn_reportActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        tabcontrol.removeTabAt(tabcontrol.getSelectedIndex());
        if(tabcontrol.getTabCount() == 0)
        {
            addTab("Nuevo", Constante.GXML);
        }
    }//GEN-LAST:event_btn_cerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btn_abrir;
    private javax.swing.JMenuItem btn_cerrar;
    private javax.swing.JButton btn_compilar;
    private javax.swing.JMenuItem btn_guardar;
    private javax.swing.JMenuItem btn_guardarcomo;
    private javax.swing.JMenuItem btn_nuevo;
    private javax.swing.JMenuItem btn_report;
    private javax.swing.JTextArea consola;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane salida_control;
    private javax.swing.JTabbedPane tabcontrol;
    private static javax.swing.JTable tabla_error;
    // End of variables declaration//GEN-END:variables
}
