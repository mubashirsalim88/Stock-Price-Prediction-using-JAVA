package ms;

import weka.core.Instances;
import weka.core.Attribute;
import java.text.SimpleDateFormat;

public class FeatureExtractor {
    public static void extractFeatures(Instances data) {
        Attribute year = new Attribute("Year");
        Attribute month = new Attribute("Month");
        Attribute day = new Attribute("Day");

        data.insertAttributeAt(year, data.numAttributes());
        data.insertAttributeAt(month, data.numAttributes());
        data.insertAttributeAt(day, data.numAttributes());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < data.numInstances(); i++) {
            try {
                String timestampStr = data.instance(i).stringValue(data.attribute("Timestamp"));
                java.util.Date date = dateFormat.parse(timestampStr);
                data.instance(i).setValue(data.numAttributes() - 3, date.getYear() + 1900);
                data.instance(i).setValue(data.numAttributes() - 2, date.getMonth() + 1);
                data.instance(i).setValue(data.numAttributes() - 1, date.getDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


