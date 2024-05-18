package ms;

import weka.core.Instances;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;

public class Main {
    public static void main(String[] args) {
        try {
            // Load data from CSV file
            Instances data = DataLoader.loadData("stock_prediction/src/main/resources/INFY_01.csv");

            // Extract features from Timestamp
            FeatureExtractor.extractFeatures(data);

            // Define target variable ("Close")
            data.setClassIndex(data.attribute("Close").index());

            // Train Random Forest model
            RandomForestModel model = new RandomForestModel();
            model.train(data);

            // Output model
            RandomForest rf = model.getModel();
            System.out.println("Random Forest model:");
            System.out.println(rf);

            // Evaluate model using cross-validation
            Evaluation eval = model.evaluate(data);
            System.out.println("Evaluation results:");
            System.out.println(eval.toSummaryString());

            // Predict Close prices for the next 30 days
            double[] predictedPrices = PricePredictor.predictNext30Days(rf, data);

            // Extract historical prices and dates for plotting
            double[] historicalPrices = new double[data.numInstances()];
            double[] historicalDates = new double[data.numInstances()];
            for (int i = 0; i < data.numInstances(); i++) {
                historicalPrices[i] = data.instance(i).value(data.attribute("Close"));
                historicalDates[i] = i;
            }

            // Prepare predicted dates for plotting
            double[] predictedDates = new double[30];
            for (int i = 0; i < 30; i++) {
                predictedDates[i] = data.numInstances() + i;
            }

            // Plot historical and predicted values
            PricePlotter.plotPrices(historicalDates, historicalPrices, predictedDates, predictedPrices);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



