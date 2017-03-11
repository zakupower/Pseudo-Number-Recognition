import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Tomov on 7.3.2017 г..
 */
public class ImagePattern {
    Pixel [] [] imagePattern;
    final public int MAXSIMILARITY;
    public ImagePattern(Pixel [] [] imagePattern) {
        this.imagePattern = imagePattern;
        MAXSIMILARITY = this.getSimilarity(this);
    }
    public ImagePattern(File img) throws IOException {
        BufferedImage image = ImageIO.read(img);
        ImageConverter imageConverter = new ImageConverter(image);
        imagePattern = imageConverter.getImagePattern().getPixelArray();
        MAXSIMILARITY = this.getSimilarity(this);
    }

    public void printPattern(){
        for(Pixel[] arr : imagePattern) {
            for(Pixel pixel : arr) {
                System.out.print(pixel.getColor() + "  ");
            }
            System.out.println();
        }
    }
    public int getSimilarity(ImagePattern ip){
        int similarity = 0;
        Pixel [] [] ipPixels = ip.getPixelArray();
        if(imagePattern.length != ipPixels.length || imagePattern[1].length != ipPixels[1].length) {
            return -1;
        } else {
            for(int i = 0; i < imagePattern.length; i++) {
                for(int j = 0; j < imagePattern[i].length;j++) {
                    if(imagePattern[i][j].getColor()==ipPixels[i][j].getColor() && ipPixels[i][j].getColor()!="W"){
                        similarity++;
                    }

                }
            }
            return similarity;
        }
    }
    public int getDifferenceFromMaxSimilarity(int similarityValue){
        return MAXSIMILARITY - similarityValue;
    }
    public Pixel [] [] getPixelArray() {
        return imagePattern;
    }
    public int getHeight() {
       return imagePattern.length;
    }
    public int getWidth() {
        return imagePattern[1].length;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImagePattern that = (ImagePattern) o;

        return Arrays.deepEquals(imagePattern, that.imagePattern);

    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(imagePattern);
    }
}
