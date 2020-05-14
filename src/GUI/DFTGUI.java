package GUI;

import Backend.Fourier;
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

public class DFTGUI extends ApplicationFrame {
    private double[] amplitudeMatrix;
    XYSeries red = new XYSeries("красный канал");
    XYSeries green = new XYSeries("зелёный канал");
    XYSeries blue = new XYSeries("синий канал");


    public DFTGUI(Fourier fourier) {
        super("Спектр");
        fourier.redDFT();
        this.amplitudeMatrix = fourier.getAmplitudeMatrix();
        fillXYSeries(amplitudeMatrix, red);
        fourier.greenDFT();
        this.amplitudeMatrix = fourier.getAmplitudeMatrix();
        fillXYSeries(amplitudeMatrix, green);
        fourier.blueDFT();
        this.amplitudeMatrix = fourier.getAmplitudeMatrix();
        fillXYSeries(amplitudeMatrix, blue);
        JFreeChart xylineChart = ChartFactory.createXYLineChart("Спектр", "частота", "Амплитуда", createDataSet(), PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);

    }

    private void fillXYSeries(double[] arr, XYSeries series) {
        for (int x = 0; x < arr.length; x++) {
            series.add(x, arr[x]);
        }
    }

    private XYDataset createDataSet() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(red);
        dataset.addSeries(green);
        dataset.addSeries(blue);
        return dataset;
    }


}
