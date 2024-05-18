package ms;

import weka.core.Instances;
import weka.core.DenseInstance;
import weka.classifiers.trees.RandomForest;
import java.util.Calendar;
import java.util.Random;

public class PricePredictor {
    public static double[] predictNext30Days(RandomForest rf, Instances data) {
        double[] predictedPrices = new double[30];
        try {
            int lastIndex = data.numInstances() - 1;
            double lastDateYear = data.instance(lastIndex).value(data.numAttributes() - 3);
            double lastDateMonth = data.instance(lastIndex).value(data.numAttributes() - 2);
            double lastDateDay = data.instance(lastIndex).value(data.numAttributes() - 1);

            Calendar calendar = Calendar.getInstance();
            calendar.set((int) lastDateYear, (int) lastDateMonth - 1, (int) lastDateDay);
            calendar.add(Calendar.DATE, 1);

            DenseInstance newInstance = new DenseInstance(data.numAttributes());
            newInstance.setDataset(data);

            Random random = new Random();
            for (int i = 0; i < 30; i++) {
                newInstance.setValue(data.numAttributes() - 3, calendar.get(Calendar.YEAR));
                newInstance.setValue(data.numAttributes() - 2, calendar.get(Calendar.MONTH) + 1);
                newInstance.setValue(data.numAttributes() - 1, calendar.get(Calendar.DAY_OF_MONTH));

                double predictedPrice = rf.classifyInstance(newInstance);
                double noise = random.nextGaussian() * 10;
                predictedPrices[i] = predictedPrice + noise;

                System.out.println("Predicted Close price for " + calendar.get(Calendar.DATE) + "-" +
                        (calendar.get(Calendar.MONTH) + 1) + "-" +
                        calendar.get(Calendar.YEAR) + ": " + predictedPrices[i]);

                calendar.add(Calendar.DATE, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return predictedPrices;
    }
}


