import java.util.ArrayList;

/**
 * Created by Tomov on 7.3.2017 г..
 */
public class NumberGuesser {

    ArrayList<ArrayList<ImagePattern>> comparisonBase;
    double [] differencesFromMaxSimilarity;
    double minimalDifferencePercent;

    //comparisonBase should contain 10 arraylist containing image references 50x50 starting from 0 to 9
    public NumberGuesser(ArrayList<ArrayList<ImagePattern>> comparisonBase) {
        if(comparisonBase == null) throw new NullPointerException();
        if(comparisonBase.size()!=10) throw new IllegalArgumentException();
        this.comparisonBase = comparisonBase;
        differencesFromMaxSimilarity = new double[10];
        minimalDifferencePercent = 100;
    }

    public int guessNumber(ImagePattern numberPattern) {
        int k = 0;
        for(ArrayList<ImagePattern> comparisonPatterns : comparisonBase ) {
            double minSimilarityDifferencePercent = 100;
            int sumOfDifferences = 0;
            int sumOfMaxDifferences = 0;
            for(ImagePattern comparisonPattern : comparisonPatterns) {

                int dif = comparisonPattern.getDifferenceFromMaxSimilarity(
                        comparisonPattern.getSimilarity(numberPattern)
                        );
                if(dif==0) return k;
                sumOfDifferences+=dif;
                sumOfMaxDifferences+= comparisonPattern.MAXSIMILARITY;

            }
            if(calculatePercent(sumOfDifferences,sumOfMaxDifferences)<minSimilarityDifferencePercent){
                minSimilarityDifferencePercent = calculatePercent(sumOfDifferences,sumOfMaxDifferences);
            }
            differencesFromMaxSimilarity[k] = minSimilarityDifferencePercent;
            k++;
        }
        int numberGuess = -1;

        for(int i = 0; i < differencesFromMaxSimilarity.length;i++) {
            if(differencesFromMaxSimilarity[i]< minimalDifferencePercent) {
                minimalDifferencePercent = differencesFromMaxSimilarity[i];
                numberGuess = i;
            }
        }

        return numberGuess;
    }
    private double calculatePercent(int val, int max){
        return (val*100)/max;
    }
    public double popLastMinimalDifference(){
        double temp = minimalDifferencePercent;
        minimalDifferencePercent = 100;
        return temp;
    }
}
