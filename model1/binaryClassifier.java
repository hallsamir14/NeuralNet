
import deepnetts;

public class binaryClassifer {

    // Read data from csv and build dataset
    DataSets emailDataSet = DataSets.readCsv("spam_data_processed.csv", 57, 1, true);

    // create and configure an instance of feed foward neural network using builder

    FeedFowardNetwork neuralNet;neuralNet=FeedFowardNetwork.builder().addInputLayer(57).addFullyConnectedLayer(15).addOutpuLayer(1,)

}