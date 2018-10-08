package colores;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author pau
 */
public class Lienzo extends Canvas {

    private byte[] baRaster;
    private BufferedImage imgOriginal;
    private MyBuffImagen imgNueva;
    private Raster rastDataImg;

    public Lienzo() {
        this.loadImage();
        this.test(baRaster);
        //this.getInfoImg(); //cambiar(?)
        this.convertToGrey();
        //this.aumentarBrillo(baRaster, 0);

    }

    public void test(byte[] bBytes) {

        for (int i = 0; i < 12; i++) {
            System.out.println(bBytes[i] + "-> original");
        }
        System.out.println("-----------------------------------------");

        Integer intA[] = MyBuffImagen.copyByteArrayToIntArray(bBytes);

        for (int i = 0; i < 12; i++) {
            System.out.println(intA[i] + "-> int");
        }

        System.out.println("-----------------------------------------");

        byte byteA[] = MyBuffImagen.copyIntArrayToByteArray(intA);

        for (int i = 0; i < 12; i++) {
            System.out.println(byteA[i] + "-> byte");
        }

    }

    public void aumentarBrillo(int nivel) {
        Integer intArray[] = MyBuffImagen.copyByteArrayToIntArray(baRaster);

        for (int i = 0; i < intArray.length; i = i + 3) {

            intArray[i] = intArray[i] + nivel;
            intArray[i + 1] = intArray[i + 1] + nivel;
            intArray[i + 2] = intArray[i + 2] + nivel;
        }
        
        baRaster = MyBuffImagen.copyIntArrayToByteArray(intArray);

    }

    public void convertToGrey() {

        //Integer ba[] = new Integer[aBytes.length];
        Integer intArray[] = MyBuffImagen.copyByteArrayToIntArray(baRaster);
        Integer media;

        //cada tres bytes...
        for (int i = 0; i < intArray.length; i += 3) {

            //media de los tres primeros
            media = (intArray[i] + intArray[i + 1] + intArray[i + 2]) / 3;

            intArray[i] = media;
            intArray[i + 1] = media;
            intArray[i + 2] = media;

            //convertir resultado de ints a bytes
            Byte bMedia = media.byteValue();

            //asignar media a los tres primeros bytes 
            baRaster[i] = bMedia;
            baRaster[i + 1] = bMedia;
            baRaster[i + 2] = bMedia;
        }

        //aBytes = MyBuffImagen.copyIntArrayToByteArray(intArray);
    }

    public byte[] copyDataRasterToByteArray(Raster ras) {

        byte[] baDataRasterSource;

        //comprueba que la imagen sea compatible para q no pete el programa
        if (ras.getDataBuffer().getDataType() != DataBuffer.TYPE_BYTE) {

            System.out.println("Imagen no compatible");
            return null;   //saltar excepcion mas q valor nulo
        }

        System.out.println("Imagen compatible");

        baDataRasterSource = ((DataBufferByte) ras.getDataBuffer()).getData();
        return baDataRasterSource;
    }

    public void getInfoImg() {

        //obtiene el rast de la imagen
        this.rastDataImg = this.imgOriginal.getRaster();

        //obtiene la informacion del array de bytes del raster
        this.baRaster = this.copyDataRasterToByteArray(this.rastDataImg);
    }

    public void loadImage() {

        try {
            this.imgOriginal = ImageIO.read(new File("img/fondo2.jpeg"));
            this.getInfoImg();  //guarda el obj Raster y el byteArray del raster

        } catch (Exception e) {
            System.out.println("Img no cargada");
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(this.imgOriginal, 0, 0, this.imgOriginal.getWidth(), this.imgOriginal.getHeight(), this);
    }

    public Lienzo getLienzo() {
        return this;
    }

}
