import java.util.ArrayList;

public class Pixel {

    private int alpha; //opacity not using it for now
    private int red;
    private int blue;
    private int green;
    private String color;

    public Pixel(int alpha, int red, int green, int blue){
        if(red==green && green == blue && blue == 255) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
            this.color = "W";
        } else {
            this.red = 0;
            this.green = 0;
            this.blue = 0;
            this.alpha = 255;
            this.color = "B";
        }

    }


    @Override
    public String toString() {
        return red + "" + green + "" + blue + "";
    }

    String getColor(){
        return color;
    }
    public int getRed() {
        return red;
    }
    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

    public int getAlpha() {
        return alpha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (red != pixel.red) return false;
        if (blue != pixel.blue) return false;
        if (green != pixel.green) return false;
        return alpha == pixel.alpha;

    }

    @Override
    public int hashCode() {
        int result = red;
        result = 31 * result + blue;
        result = 31 * result + green;
        result = 31 * result + alpha;
        return result;
    }
}