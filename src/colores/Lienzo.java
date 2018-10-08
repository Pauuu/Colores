package colores;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas {

    private Ventana ventana;
    private MyBuffImagen imagePrinted;

    public Lienzo(Ventana v) {
        this.ventana = v;

    }

    public void convertToGrey() {
        this.imagePrinted.toGrey();
    }

    public void showFadeOut(String imgTrans, int numFrames, int sleepTime) {

        try {
            BufferedImage buffImgFade = ImageIO.read(new File(imgTrans));


            this.imagePrinted.doFadeOut(buffImgFade, numFrames);

        } catch (Exception e) {

            System.out.println("Img no cargada");
        }

        for (int i = 0; i < numFrames; i++) {

        }
    }

    public void modifyBrightness(int valor) {
        this.imagePrinted.modifyBrightness(valor);
    }

    public void printImage(String imgSrc) {

        try {
            BufferedImage buffImagen = ImageIO.read(new File(imgSrc));
            this.imagePrinted = new MyBuffImagen(buffImagen);

        } catch (Exception e) {

            System.out.println("Img no cargada");
        }
    }

    public void printImage() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.imagePrinted.getBufferedImg(), 0, 0, this);
    }

    public Lienzo getLienzo() {
        return this;
    }

}
