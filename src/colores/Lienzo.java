package colores;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lienzo extends Canvas {

    private byte[] baRaster;
    private byte[] baRasterOriginal;

    private BufferedImage buffImagen;
    private MyBuffImagen myBuffImagen;
    private MyBuffImagen myBuffImagen1;

    private Integer[] iaRaster;

    public Lienzo() {
        // this.loadImage();
        /* this.myBuffImagen.showSquare(50, 0, 20, 20);
        this.myBuffImagen.showSquare(20, 20, 30, 100);
        this.myBuffImagen.showSquare(0, 0, 20, 20);
         */
    }

    public void convertToGrey() {
        this.myBuffImagen.toGrey();
    }

    public void doFadeOut(String srcTrans) {
        
        try {
            BufferedImage buffImgTrans = ImageIO.read(new File(srcTrans));

        } catch (Exception e) {
            System.out.println("Img no cargada");
        }

    }

    public void doFadeoutX(String srcImgOriginal, String srcImgTrans) {

        try {
            this.buffImagen = ImageIO.read(new File(srcImgOriginal));
            BufferedImage buff2 = ImageIO.read(new File(srcImgTrans));

            this.myBuffImagen1 = new MyBuffImagen(buffImagen);

        } catch (Exception e) {
            System.out.print("Imagenes no cargadas");
        }

    }

    public void modifyBrightness(int valor) {
        this.myBuffImagen.modifyBrightness(valor);
    }

    public void restoreImg() {

    }

    public void showImage() {

    }

    public void loadImage() {

        try {
            this.buffImagen = ImageIO.read(new File("img/fondo4.jpeg"));
            this.myBuffImagen = new MyBuffImagen(buffImagen);

        } catch (Exception e) {

            System.out.println("Img no cargada");
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffImagen, 0, 0, this);
    }

    public Lienzo getLienzo() {
        return this;
    }

}
