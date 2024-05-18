package ms;

import weka.core.Instances;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;

public class RandomForestModel {
    private RandomForest rf;

    public void train(Instances data) throws Exception {
        rf = new RandomForest();
        rf.buildClassifier(data);
    }

    public RandomForest getModel() {
        return rf;
    }

    public Evaluation evaluate(Instances data) throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(rf, data, 5, new java.util.Random(1));
        return eval;
    }
}
