import java.util.*;
// Sampled code from Suyash Sonawane

/*Matrix class will have the following funtionaties: 
--> Addition
--> Subtraction
--> Transpose
--> Multiplication of matricies
*/

class Matrix {
    double[][] data; // Two-D array while hold content in matricies
    int rows, cols; // Logical dimension variables of matrix

    /*
     * Matrix object will hold 3 varaibles
     * Data - 2D array that holds data of the matrix
     * Rows - number of rows in Matrix object
     * Cols - number of columns in matrix object
     */

    Matrix(int rows, int cols) /*
                                * constructor for initializing our matrix object with
                                * random values between -1 and 1 by passing rows and cols as parameters
                                */
    {
        data = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }

    }

    /*
     * 'Add' function which is overloaded with 2 parameters
     * a double and a Matrix object which does element-wise addition
     */

    public void add(double scaler) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] += scaler;

            }
        }
    }

    public void add(Matrix m) {
        if (cols != m.cols || rows != m.rows) {
            System.out.println("Output");
            return;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] += m.data[i][j];
            }
        }
    }
    // Checkpoint 1 .........

    /*
     * Next two methods will calculate the subtraction and transpose of the matrices
     * sent as parameters to the class static functions
     * These functions retrun the new Matrix object
     */
    public static Matrix subtract(Matrix a, Matrix b) {

        Matrix temp = new Matrix(a.rows, a.cols);
        for (int i = 0; i < a.cols; i++) {
            for (int j = 0; j < a.cols; j++) {
                temp.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return temp;
    }

    public static Matrix transpose(Matrix a) {
        Matrix temp = new Matrix(a.cols, a.rows);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                temp.data[j][i] = a.data[i][j];
            }
        }

        return temp;
    }
    /*---First multiply function makes 2 Matrix objects and does the dot product operation on respective matricies
    and retruns a new Matrix object
    
    ---Second function does the element-wise multiplication of the matricies
    
    ---Last function scales/multiplies the whole matrix by a scaler value
    
    */

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp = new Matrix(a.rows, b.cols);
        for (int i = 0; i < temp.rows; i++) {
            for (int j = 0; j < temp.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                temp.data[i][j] = sum;
            }
        }
        return temp;
    }

    public void multiply(Matrix a) {
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                this.data[i][j] *= a.data[i][j];
            }
        }
    }

    public void multiply(double a) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] *= a;
            }
        }
    }

    /*
     * ---Next function is a sigmoid activation function
     * Link for reference:
     * https://deepai.org/machine-learning-glossary-and-terms/sigmoid-function
     */

    public void sigmoid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
            }
        }
    }

    public Matrix dsigmoid() {
        Matrix temp = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);
            }
        }
        return temp;
    }

    /* Helper functions for converting the matirx object to and from 2D array */

    public static Matrix fromArray(double[] x) {
        Matrix temp = new Matrix(x.length, 1);
        for (int i = 0; i < x.length; i++) {
            temp.data[i][0] = x[i];
        }
        return temp;
    }

    public List<Double> toArray() {
        List<Double> temp = new ArrayList<Double>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp.add(data[i][j]);
            }
        }
        return temp;
    }

}
