
public class datasetInstance {
    public static void main(String a[]) {

        double[][] X = {
                { 0, 0 },
                { 1, 0 },
                { 0, 1 },
                { 1, 1 }
        };

        double[][] Y = {
                { 0 }, { 1 }, { 1 }, { 0 }
        };

        neuralNetwork object = new neuralNetwork(2, 10, 1);
        object.fit(X, Y, 50000);

        double[][] input = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        for (double d[] : input) {

            System.out.println(object.predict(d).toString());

        }

    }
}
