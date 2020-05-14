package GUI;

import Backend.ImageGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;


public class ImageGraphGUI extends ApplicationFrame {
    private int[] redIntencityMatrix;
    private int[] greenIntencityMatrix;
    private int[] blueIntencityMatrix;

    public ImageGraphGUI(ImageGraph imageGraph) {
        super("График распределения интенсивности");
        this.redIntencityMatrix = imageGraph.getRedIntencityMatrix();
        this.greenIntencityMatrix = imageGraph.getGreenIntencityMatrix();
        this.blueIntencityMatrix = imageGraph.getBlueIntencityMatrix();
        JFreeChart xylineChart = ChartFactory.createXYLineChart("Интенсивность","Интенсивность","Пиксели", createDataSet(), PlotOrientation.VERTICAL ,
                true , true , false);
        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.BLUE );
        renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );

    }

    private XYDataset createDataSet() {
        XYSeries red = new XYSeries("красный канал");
        for (int x = 0; x < redIntencityMatrix.length; x++) {
            red.add(x, redIntencityMatrix[x]);
        }
        XYSeries green = new XYSeries("зелёный канал");
        for (int x = 0; x < greenIntencityMatrix.length; x++) {
            green.add(x, greenIntencityMatrix[x]);
        }
        XYSeries blue = new XYSeries("синий канал");
        for (int x = 0; x < blueIntencityMatrix.length; x++) {
            blue.add(x, blueIntencityMatrix[x]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(red);
        dataset.addSeries(green);
        dataset.addSeries(blue);
        return dataset;
    }

}
