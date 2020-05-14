package Backend;

import java.awt.*;

public class ImageGraph {

    private int[] redIntencityMatrix = new int[256];
    private int[] greenIntencityMatrix = new int[256];
    private int[] blueIntencityMatrix = new int[256];
    private static final int R_Canal = 0;
    private static final int G_Canal = 1;
    private static final int B_Canal = 2;

    public void fillMatrix(MyImage image1, int canal) {
        switch (canal) {
            case R_Canal:
                fillRedIntencityMatrix(image1);
                break;
            case G_Canal:
                fillGreenIntencityMatrix(image1);
                break;
            case B_Canal:
                fillBlueIntencityMatrix(image1);
                break;
        }

    }

    private void fillRedIntencityMatrix(MyImage image) {
        for (int i = 0; i < redIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (image.getRMatrix()[y][x] == i)
                        redIntencityMatrix[i]+=1;
                }
            }
        }
    }

    public int[] getRedIntencityMatrix() {
        return redIntencityMatrix;
    }

    public int[] getGreenIntencityMatrix() {
        return greenIntencityMatrix;
    }

    public int[] getBlueIntencityMatrix() {
        return blueIntencityMatrix;
    }

    private void fillGreenIntencityMatrix(MyImage image) {
        for (int i = 0; i < greenIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (image.getGMatrix()[y][x] == i)
                        greenIntencityMatrix[i] += 1;
                }
            }
        }
    }

    private void fillBlueIntencityMatrix(MyImage image) {
        for (int i = 0; i < blueIntencityMatrix.length; i++) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (image.getBMatrix()[y][x] == i)
                        blueIntencityMatrix[i] += 1;
                }
            }
        }
    }
    /*public void show(){
        int sum=0;
        for (int i = 0; i <R.length ; i++) {
            System.out.print(R[i]+" ");
           sum+=R[i];
        }
        System.out.println(sum);
    }*/
}
