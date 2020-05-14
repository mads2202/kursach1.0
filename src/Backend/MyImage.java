package Backend;

import GUI.DFTGUI;
import GUI.ImageGraphGUI;
import GUI.MainWindow;
import org.jfree.ui.RefineryUtilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
// todo 01.05.2020: сделать одномерное преобразование фурье для каждого канала.
//  Построить график посмотреть что будет.
//  Заполнить провал на графике таким образом чтобы его не было.
//  Сделать обратное преобразование фурье.
//  Попробовать отфильтровать цифровым фильтром

public class MyImage extends BufferedImage {
    private static BufferedImage image;
    private int[][][] RGBMatrix;
    private int[][] RMatrix;
    private int[][] GMatrix;
    private int[][] BMatrix;


    private MyImage() {
        super(image.getWidth(), image.getHeight(), image.getType());
    }

    //метод который создаёт экземляр класс
    public static MyImage getMyImage(File file) {
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        return new MyImage();
    }

    public int[][][] getRGBMatrix() {
        RGBMatrix = new int[image.getHeight()][image.getWidth()][3];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                RGBMatrix[y][x][0] = color.getRed();
                RGBMatrix[y][x][1] = color.getGreen();
                RGBMatrix[y][x][2] = color.getBlue();

            }
        }
        return RGBMatrix;
    }
public void fillRMatrix(){
    RMatrix = new int[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
        for (int x = 0; x < image.getWidth(); x++) {
            Color color = new Color(image.getRGB(x, y));
            RMatrix[y][x] = color.getRed();


        }
    }
    }
    public int[][] getRMatrix() {
        return RMatrix;
    }
    public void fillGMatrix(){
        GMatrix = new int[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                GMatrix[y][x] = color.getGreen();

            }
        }
    }

    public int[][] getGMatrix() {
        return GMatrix;
    }

    public void fillBMatrix(){
        BMatrix = new int[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                BMatrix[y][x] = color.getBlue();

            }
        }
    }
    public int[][] getBMatrix() {
        return BMatrix;
    }
    public void show(int[][] arr){
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        }
        public void save(){
            try {
                File output = new File("D:\\kursach","newPepe.jpg");
                ImageIO.write(image, "jpg", output);
            } catch (IOException e) {
                System.out.println("Something wrong during output");
            }

        }

    public static BufferedImage getImage() {
        return image;
    }

    public static void main(String[] args) {
        MainWindow mw=new MainWindow();

    }
}
