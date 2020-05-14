package Backend;

public class Fourier {
    private int[] Rmatrix;
    private int[] Gmatrix;
    private int[] Bmatrix;
    private double[] realMatrix;
    private double[] imgnMatrix;
    private double[] amplitudeMatrix;

    public Fourier(MyImage image) {
        Rmatrix = new int[image.getWidth() * image.getHeight()];
        Gmatrix = new int[image.getWidth() * image.getHeight()];
        Bmatrix = new int[image.getWidth() * image.getHeight()];
        fillRmatrix(image);
        fillGmatrix(image);
        fillBmatrix(image);

    }

    private void fillRmatrix(MyImage image) {
        int i = 0;
        while (i < Rmatrix.length) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Rmatrix[i] = image.getRMatrix()[y][x];
                    i++;
                }
            }
        }
    }

    private void fillGmatrix(MyImage image) {
        int i = 0;
        while (i < Gmatrix.length) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Gmatrix[i] = image.getGMatrix()[y][x];
                    i++;
                }
            }
        }
    }

    private void fillBmatrix(MyImage image) {
        int i = 0;
        while (i < Bmatrix.length) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    Bmatrix[i] = image.getBMatrix()[y][x];
                    i++;
                }
            }
        }
    }

    private void fillRealMatrix(int[] arr) {
        realMatrix = new double[arr.length/10000];


        for (int N = 0; N < realMatrix.length; N++) {
            for (int k = 0; k*10000 < arr.length; k++) {
                realMatrix[N] += arr[k*1000] * Math.cos((2 * Math.PI * k*1000 * N) / arr.length);
            }
        }
    }

    private void fillImgnMatrix(int[] arr) {
        imgnMatrix = new double[arr.length/10000];
        System.out.println(imgnMatrix.length);
        for (int N = 0; N < imgnMatrix.length; N++) {
            for (int k = 0; k*10000 < arr.length; k++) {
                imgnMatrix[N] += -(arr[k*1000] * Math.cos((2 * Math.PI * k*1000 * N) / arr.length));
            }
        }
    }

    private void setAmplitudeMatrix() {
        amplitudeMatrix = new double[realMatrix.length];
        for (int i = 0; i < amplitudeMatrix.length; i++) {
            amplitudeMatrix[i] = Math.sqrt(realMatrix[i] * realMatrix[i] + imgnMatrix[i] * imgnMatrix[i]);
        }
    }

    public double[] getAmplitudeMatrix() {
        return amplitudeMatrix;
    }

    public void redDFT() {
        Thread t=new Thread(()->fillRealMatrix(Rmatrix));
        Thread t1=new Thread(()->fillImgnMatrix(Rmatrix));
        t.start();
        t1.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setAmplitudeMatrix();
    }

    public void greenDFT() {
        Thread t=new Thread(()->fillRealMatrix(Gmatrix));
        Thread t1=new Thread(()->fillImgnMatrix(Gmatrix));
        t.start();
        t1.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setAmplitudeMatrix();
    }

    public void blueDFT() {
        Thread t=new Thread(()->fillRealMatrix(Bmatrix));
        Thread t1=new Thread(()->fillImgnMatrix(Bmatrix));
        t.start();
        t1.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setAmplitudeMatrix();
    }

    public int[] getRmatrix() {
        return Gmatrix;
    }
}
