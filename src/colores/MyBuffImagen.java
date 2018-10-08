package colores;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyBuffImagen {

    private Lienzo lienzo;
    private byte[] baRaster;
    private BufferedImage imagen;
    private Integer[] iaRasterOriginal;
    private Integer[] iaRaster;
    private Raster rastDataImg;

    public MyBuffImagen(BufferedImage imgSource) {

        this.imagen = imgSource;

        //obtiene el rast de la imagen
        this.rastDataImg = this.imagen.getRaster();

        //obtiene la informacion del array de bytes del raster
        this.baRaster = this.copyDataRasterToByteArray(this.rastDataImg);

        //convierte el baRaster en un array de integers y guarda el original
        this.iaRasterOriginal = this.copyByteArrayToIntArray(this.baRaster);
        this.iaRasterOriginal = this.copyByteArrayToIntArray(this.baRaster);
    }

    public byte[] copyDataRasterToByteArray(Raster ras) {

        byte[] baDataRasterSource;

        //comprueba que la imagen sea compatible para q no pete el programa
        if (!this.comprobarProfundidad(ras)) {
            //añadir excepcion
        }

        baDataRasterSource = ((DataBufferByte) ras.getDataBuffer()).getData();
        return baDataRasterSource;
    }

    public byte[] copyIntArrayToByteArray(Integer[] biSource) {

        byte[] isCopy = new byte[biSource.length];

        for (int i = 0; i < biSource.length; i++) {
            isCopy[i] = biSource[i].byteValue();
        }

        return isCopy;
    }

    public void getDiff(BufferedImage imgSource, BufferedImage imgTrans) throws InterruptedException {
        Raster rastSource = imgSource.getRaster();
        Raster rastTrans = imgTrans.getRaster();

        byte[] baSource = this.copyDataRasterToByteArray(rastSource);
        byte[] baTrans = this.copyDataRasterToByteArray(rastTrans);

        Integer[] iaSource = this.copyByteArrayToIntArray(baSource);
        Integer[] iaTrans = this.copyByteArrayToIntArray(baTrans);
        Integer[] iaDiff = new Integer[iaSource.length];

        for (int posicion = 0; posicion < iaSource.length; posicion++) {
            iaDiff[posicion] = (iaTrans[posicion] - iaSource[posicion]) / 10;
        }
        
        for (int i = 0; i < 10; i++) {
            for (int pos = 0; pos < iaDiff.length; pos++) {
                iaSource[pos] += iaDiff[pos];
            }
            
            Thread.sleep(100);
            
        }
    }
    
    

    public Integer[] copyByteArrayToIntArray(byte[] baSource) {

        Integer[] isCopy = new Integer[baSource.length];

        for (int i = 0; i < baSource.length; i++) {
            isCopy[i] = Byte.toUnsignedInt(baSource[i]);
        }

        return isCopy;
    }

    public void modifyBrightness(int valor) {

        for (int i = 0; i < this.iaRasterOriginal.length; i++) {
            this.iaRasterOriginal[i] += valor;

            if (this.iaRasterOriginal[i] > 255) {
                this.iaRasterOriginal[i] = 255;

            } else if (this.iaRasterOriginal[i] < 0) {
                this.baRaster[i] = 0;
            }
        }

        this.updateBaRaster(iaRasterOriginal);
    }

    public void restoreOriginal() {

        this.updateBaRaster(iaRasterOriginal);
    }

    public void showSquare(int posX, int posY, int anchura, int altura) {
        int width = this.imagen.getWidth();
        int x = posX;
        int y = posY;
        int filaInicial = (width * 3) * y;
        int colInicial;

        //row < 
        for (int row = filaInicial; row < (width * 3) * (y + altura); row += (width * 3)) {

            colInicial = row + (x * 3);

            for (int col = colInicial; col < row + ((x + anchura)) * 3; col += 3) {

                this.iaRasterOriginal[col] = 20;
                this.iaRasterOriginal[col + 1] = 50;
                this.iaRasterOriginal[col + 2] = 200;
            }
        }

        this.updateBaRaster(this.iaRasterOriginal);
    }

    public void toGrey() {
        int media;
        for (int i = 0; i < this.iaRasterOriginal.length; i += 3) {
            media = (this.iaRasterOriginal[i]
                    + this.iaRasterOriginal[i + 1]
                    + this.iaRasterOriginal[i + 2]) / 3;

            this.iaRasterOriginal[i] = media;
            this.iaRasterOriginal[i + 1] = media;
            this.iaRasterOriginal[i + 2] = media;
        }

        this.updateBaRaster(this.iaRasterOriginal);
    }

    public void toYellow() {
        for (int i = 0; i < baRaster.length; i++) {
            byte b = baRaster[i];
        }
    }

    /////////////////////////////////////////
    public boolean comprobarProfundidad(Raster ras) {
        if (ras.getDataBuffer().getDataType() != DataBuffer.TYPE_BYTE) {
            System.out.println("Imagen no compatible");
            return false;   //saltar excepcion mas q valor nulo=================>
        }

        System.out.println("Imagen compatible");
        return true;
    }

    private void updateBaRaster(Integer[] iaSource) {
        byte[] bArray = this.copyIntArrayToByteArray(iaSource);

        for (int i = 0; i < bArray.length; i++) {
            this.baRaster[i] = bArray[i];
        }
    }

}
