package ms;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;

public class PricePlotter {
    public static void plotPrices(double[] historicalDates, double[] historicalPrices, double[] predictedDates, double[] predictedPrices) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] series1 = {historicalDates, historicalPrices};
        double[][] series2 = {predictedDates, predictedPrices};
        dataset.addSeries("Historical", series1);
        dataset.addSeries("Predicted", series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Stock Price Prediction",
                "Day",
                "Price",
                dataset
        );

        JFrame frame = new JFrame("Price Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}


