/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author pau
 */
public class Ventana extends JFrame {

    private Lienzo l;

    public Ventana() {
        this.setSize(500, 500);
        Container cp = this.getContentPane();

        this.l = new Lienzo();
        //this.l.convertToGrey();
        cp.add(l);

    }

    public Ventana getVentana() {
        return this;
    }
}
