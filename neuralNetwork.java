import java.util.*;

/*NeuralNetwork class will have several variables
 * - weights_ih:The weights matrix for the input and hidden layer.
 * - weights_ho:The weights matrix for the hidden and output layer.
 * - bias_h: The bias matrix for the hidden layer
 * - bias_o: The bias matrix for the ouput layer
 * - l_rate: The learning rate, a hyper-parameter used to control the learning steps during optimization of weights
 */
public class neuralNetwork {
    // declare class variables
    Matrix weights_ih;
    Matrix weights_ho;
    Matrix bias_h;
    Matrix bias_o;
    double l_rate = 0.1;

    public neuralNetwork(int i, int h, int o) {
        this.weights_ih = new Matrix(h, i);
        this.weights_ho = new Matrix(o, h);

        this.bias_h = new Matrix(h, 1);
        this.bias_o = new Matrix(o, 1);

    }

    // Shapes of the matricies are important to check as the dot product only works
    // on compatible matricies
    // Reference:
    // https://towardsdatascience.com/linear-algebra-basics-dot-product-and-matrix-multiplication-2a7624942810

    // Predict function for foward propogation
    /*
     * Accepts double array as input and converts it to a column matrix by helper
     * function.
     * Foward pass is then calculated on both layers and then output is flattened
     * into list by other helper function
     * (Source code for helper functions are in matrix class)
     */
    // Reference:
    // https://towardsdatascience.com/forward-propagation-in-neural-networks-simplified-math-and-code-version-bbcfef6f9250

    public List<Double> predict(double[] X) {
        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_o);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }

    /*
     * Train function will take X and Y as parameters (1-D double arrary for each
     * parameter) and convert them to matricies
     * The ouput from the foward pass (foward propogation) is subtracted from the
     * target (Parameter Y after being converted into a matrix object)
     * The result of the subtraction is the error of the current sample passed
     * This error will be used to calculate gradients for the back porpogation
     * The derivative of the sigmoid function is applied element-wise on the ouput
     * matrix, which returns the gradient matrix
     * The gradient matrix will is multiplied by the error output and learning rate
     * Reference:https://towardsdatascience.com/understanding-backpropagation-
     * algorithm-7bb3aa2f95fd
     */

    // Repeat the same steps for the privious layers. Back propogation will run from
    // output layers to input layers

    public void train(double[] X, double[] Y) {

        Matrix input = Matrix.fromArray(X);
        Matrix hidden = Matrix.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = Matrix.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = Matrix.fromArray(Y);

        Matrix error = Matrix.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(l_rate);

        Matrix hidden_T = Matrix.transpose(hidden);
        Matrix who_delta = Matrix.multiply(gradient, hidden_T);

        weights_ho.add(who_delta);
        bias_o.add(gradient);

        Matrix who_T = Matrix.transpose(weights_ho);
        Matrix hidden_errors = Matrix.multiply(who_T, error);

        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(l_rate);

        Matrix i_T = Matrix.transpose(input);
        Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);

        // Updated weights of all the layers for the current sample. First step in
        // processing dataset
    }

    /*
     * The fit function will take in two 2D arrays (X and Y) along with the number
     * of epoches
     * Epoches refers to the number of iterations to be done on the dataset
     * Train function will be repeatedly called with random datapoints to map
     * general network across dataset
     */

    public void fit(double[][] X, double[][] Y, int epochs) {
        for (int i = 0; i < epochs; i++) {
            int sampleN = (int) (Math.random() * X.length);
            this.train(X[sampleN], Y[sampleN]);
        }
    }

}
