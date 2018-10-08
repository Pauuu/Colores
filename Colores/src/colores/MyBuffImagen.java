/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;

/**
 *
 * @author pau
 */
public class MyBuffImagen extends BufferedImage {

    /*
        Poner metodos en static!!!!!!!!!!!!!!!!
     */
    public MyBuffImagen(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public static byte[] copyIntArrayToByteArray(Integer[] biSource) {

        byte[] isCopy = new byte[biSource.length];

        for (int i = 0; i < biSource.length; i++) {
            isCopy[i] = biSource[i].byteValue();
        }

        return isCopy;
    }

    public static Integer[] copyByteArrayToIntArray(byte[] baSource) {

        Integer[] isCopy = new Integer[baSource.length];

        for (int i = 0; i < baSource.length; i++) {
            isCopy[i] = Byte.toUnsignedInt(baSource[i]);
        }

        return isCopy;
    }

    public boolean comprobarProfundidad(Raster ras) {
        return false;
    }

}
