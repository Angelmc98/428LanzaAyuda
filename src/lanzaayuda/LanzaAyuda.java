/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lanzaayuda;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ÁNGEL MEDINA CANTOS 
 */

public class LanzaAyuda implements ActionListener, ItemListener{

    JTextArea output;
    JScrollPane scrollPane;
    String newline ="\n";

    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        //Cremaos el menuBar
        menuBar = new JMenuBar();
        
        // Construimos el primer menu
        menu = new JMenu("Ayuda");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                " El único menú de este programa que tiene elementos de menú");
        menuBar.add(menu); // Añadimos el menu bar al menu
        
        // Agrupamos los menuItems
        // Contenido de ayda
        menuItem = new JMenuItem("Contenido de Ayuda", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_F1, 0));
        menuItem.addActionListener(this);
        menu.add(menuItem); // Añadimos el menuItem al menu
        
        //About
        JMenuItem menuItem2 = new JMenuItem("About", KeyEvent.VK_B);
         menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem2.addActionListener(this);
        menu.add(menuItem2);
        
        //Creamos del objeto HelpBroker
        HelpSet hs = obtenFicAyuda();
        HelpBroker hb = hs.createHelpBroker();
        
        // Asociamos la ayuda al menuItem
        hb.enableHelpOnButton(menuItem,"topics", hs);
        
        return menuBar;
    }
    
    public HelpSet obtenFicAyuda()
    {
        try
        {
            ClassLoader c1 = LanzaAyuda.class.getClassLoader();
            File file = new File(LanzaAyuda.class.getResource("/Help/HelpSet.hs").getFile());
            URL url = file.toURI().toURL();
            
            //Crea un objeto HelpSet
            HelpSet hs = new HelpSet(null, url);
            return hs;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Fichero HelpSet no encontrado");
            return null;
        }
    }
   
            
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        obtenFicAyuda();
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
        obtenFicAyuda();
    }
     private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Ayuda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LanzaAyuda demo = new LanzaAyuda();
        frame.setJMenuBar(demo.createMenuBar());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
    
     
    public static void main(String[] args) 
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               createAndShowGUI();
            }
        });
    }
}
