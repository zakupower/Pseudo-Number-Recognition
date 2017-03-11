import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Tomov on 7.3.2017 г..
 */
public class ImageConverter {

    BufferedImage image;

    public ImageConverter(BufferedImage image) {

        if (image == null)
            throw new NullPointerException();
        this.image = image;
    }

    public ImagePattern getImagePattern(){
        Color cl;
        int [] [] imageRGBData = convertTo2DWithoutUsingGetRGB();
        Pixel [] [] pixelArrays = new Pixel[imageRGBData.length][imageRGBData[1].length];

        for(int i = 0; i < imageRGBData.length; i++){

            for(int j = 0; j < imageRGBData[i].length;j++) {
                cl = new Color(imageRGBData[i][j]);
                pixelArrays[i][j] = new Pixel(cl.getAlpha(),cl.getRed(),cl.getGreen(),cl.getBlue());
            }
        }

    return new ImagePattern(pixelArrays);
    }

    private int[][] convertTo2DWithoutUsingGetRGB() {

        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) pixels[pixel] & 0xff); // blue
                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }


}
