import sun.net.www.content.image.png;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            ArrayList<ArrayList<ImagePattern>> comparisonBase = new ArrayList<ArrayList<ImagePattern>>();
            for(int i = 0;i<10;i++) {
                comparisonBase.add(new ArrayList<ImagePattern>());
            }

            for(int i = 0; i <23; i++){
                comparisonBase.get(0).add(new ImagePattern(new File("Samples\\0zeroes\\0"+i+".png")));       //
                comparisonBase.get(1).add(new ImagePattern(new File("Samples\\1ones\\1"+i+".png")));         //
                comparisonBase.get(2).add(new ImagePattern(new File("Samples\\2twos\\2"+i+".png")));         //
                comparisonBase.get(3).add(new ImagePattern(new File("Samples\\3threes\\3"+i+".png")));       //
                comparisonBase.get(4).add(new ImagePattern(new File("Samples\\4fours\\4"+i+".png")));        //  These directories need to be changed
                comparisonBase.get(5).add(new ImagePattern(new File("Samples\\5fives\\5"+i+".png")));        //
                comparisonBase.get(6).add(new ImagePattern(new File("Samples\\6sixes\\6"+i+".png")));        //
                comparisonBase.get(7).add(new ImagePattern(new File("Samples\\7sevens\\7"+i+".png")));       //
                comparisonBase.get(8).add(new ImagePattern(new File("Samples\\8eights\\8"+i+".png")));       //
                comparisonBase.get(9).add(new ImagePattern(new File("Samples\\9nines\\9"+i+".png")));        //
            }
            NumberGuesser numGuesser = new NumberGuesser(comparisonBase);

            for(int i = 0; i < 10; i++) {
                testPatternWithNumber(i,numGuesser);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();;
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

        private static void testPatternWithNumber(int i, NumberGuesser numGuesser) throws IOException {
            int guessedNumber = numGuesser.guessNumber(new ImagePattern(new File("Sanmples\\"+i+".png")));    //this directory needs to be changed
            System.out.print(guessedNumber + " " + numGuesser.popLastMinimalDifference()+"%");
            if(guessedNumber==i) System.out.println(" TRUE");
            else System.out.println(" FALSE - " + i);
        }
        private static void printSimilarities(ImagePattern pattern1, ImagePattern pattern2, String first,String second){
            System.out.println(first+"=="+second);
            int sim = pattern1.getSimilarity(pattern2);
            System.out.println("Similarity:" + sim);
            System.out.println("DifferenceFromMaxSimilarity:" + pattern1.getDifferenceFromMaxSimilarity(sim));
        }

}






