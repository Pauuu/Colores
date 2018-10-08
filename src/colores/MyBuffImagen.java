package colores;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

public class MyBuffImagen {

    private Lienzo lienzo;
    
    private BufferedImage imagen;
    private byte[] baRaster; 
    private Integer[] iaRasterSrc;
    private Raster rastDataImg;

    public MyBuffImagen(BufferedImage imgSource) {

        this.imagen = imgSource;

        //obtiene el rast de la imagen
        this.rastDataImg = this.imagen.getRaster();

        //obtiene la informacion del array de bytes del raster
        this.baRaster = this.convertDataRasterToByteArray(this.rastDataImg);

        //convierte el baRaster en un array de integers y guarda el original
        this.iaRasterSrc = this.convertByteArrayToIntArray(this.baRaster);
    }

    public byte[] convertDataRasterToByteArray(Raster ras) {

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
    
    public BufferedImage getBufferedImg(){
        return this.imagen;
    }

    public Integer[] convertByteArrayToIntArray(byte[] baSource) {

        Integer[] isCopy = new Integer[baSource.length];

        for (int i = 0; i < baSource.length; i++) {
            isCopy[i] = Byte.toUnsignedInt(baSource[i]);
        }

        return isCopy;
    }
    
    public void doFadeOut(BufferedImage imgFade, int numFrames){
        
        Raster rasFade = imgFade.getRaster();
        byte[] baFade = this.convertDataRasterToByteArray(rasFade);
        Integer[] iaFader = this.convertByteArrayToIntArray(baFade);
        
        Integer[] iaDifferencia = new Integer[iaFader.length];
        
        for (int pos = 0; pos < iaFader.length; pos++){
            iaDifferencia[pos] = (iaFader[pos] - iaRasterSrc[pos]) / numFrames;
        }
        
        for (int i = 0; i < 0; i++) {
            this.iaRasterSrc[i] += iaDifferencia[i];
            
        }
        
        this.updateBaRaster(this.iaRasterSrc);
    }
    
    

    public void modifyBrightness(int valor) {

        for (int i = 0; i < this.iaRasterSrc.length; i++) {
            this.iaRasterSrc[i] += valor;

            if (this.iaRasterSrc[i] > 255) {
                this.iaRasterSrc[i] = 255;

            } else if (this.iaRasterSrc[i] < 0) {
                this.baRaster[i] = 0;
            }
        }

        this.updateBaRaster(iaRasterSrc);
    }

    public void restoreOriginal() {

        this.updateBaRaster(iaRasterSrc);
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

                this.iaRasterSrc[col] = 20;
                this.iaRasterSrc[col + 1] = 50;
                this.iaRasterSrc[col + 2] = 200;
            }
        }

        this.updateBaRaster(this.iaRasterSrc);
    }

    public void toGrey() {
        int media;
        for (int i = 0; i < this.iaRasterSrc.length; i += 3) {
            media = (this.iaRasterSrc[i]
                    + this.iaRasterSrc[i + 1]
                    + this.iaRasterSrc[i + 2]) / 3;

            this.iaRasterSrc[i] = media;
            this.iaRasterSrc[i + 1] = media;
            this.iaRasterSrc[i + 2] = media;
        }

        this.updateBaRaster(this.iaRasterSrc);
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
