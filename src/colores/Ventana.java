package colores;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Ventana extends JFrame implements ActionListener {

    private JPanel panel;
    private Lienzo lienzoOriginal, lienzo1;
    private JButton jbGrey, jbBrightness, jbRestoreImg;
    private JTextArea textArea;

    public Ventana() {
        this.mostrarVentana();
        
        //añadida imagen original
        //this.lienzoOriginal.printImage("img/fondo5.jpeg");

        //añadida imagen para modificar
        this.lienzo1.printImage("img/fondo5.jpeg");
        
        //test para hacer el fundido
        this.lienzo1.showFadeOut("img/fondo4.jpeg", 10, 100);
    }
    
    public void mostrarVentana() {
        this.setSize(1500, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel(new GridBagLayout());

        //añadir lienzos
        this.addLienzoOriginal();
        this.addLienzo1();

        //añadir los JElements
        this.addButtonToGrey("Convertir a gris");
        this.addButtonModifyBrightness("Cambiar brillo");
        this.addRestoreButton();
        //this.addTextAreaBrightness();

        Container cp = this.getContentPane();
        cp.add(panel);
    }

    public void addLienzoOriginal() {
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        

        this.lienzoOriginal = new Lienzo(this);
        this.lienzoOriginal.setSize(700, 700);

        this.panel.add(lienzoOriginal, c);
    }

    public void addLienzo1() {
        

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;

        this.lienzo1 = new Lienzo(this);
        this.lienzo1.setSize(700, 700);

        this.panel.add(lienzo1, c);
    }

    public void addButtonToGrey(String nombreBoton) {
        
        this.jbGrey = new JButton(nombreBoton);
        this.jbGrey.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 2;
        c.gridy = 1;
        c.ipady = 2;

        this.panel.add(this.jbGrey, c);
    }

    public void addButtonModifyBrightness(String nombreBoton) {
        
        this.jbBrightness = new JButton(nombreBoton);
        this.jbBrightness.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.ipady = 2;

        this.panel.add(this.jbBrightness, c);
    }

    public void addRestoreButton() {
        
        this.jbRestoreImg = new JButton("Restaurar imagen");
        this.jbBrightness.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.ipady = 2;
        
        this.panel.add(this.jbRestoreImg, c);
    }

    public void addTextAreaBrightness() {

        this.textArea = new JTextArea(0, 1);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 2;

        this.panel.add(this.textArea, c);
    }

    public Ventana getVentana() {
        return this;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.jbGrey == e.getSource()) {
            this.lienzo1.convertToGrey();

        } else if (this.jbBrightness == e.getSource()) {
           this.lienzo1.modifyBrightness(20);
        }

        this.lienzo1.repaint();

    }
    
    
}
