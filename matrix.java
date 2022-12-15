/*Matrix class that will have funtionaties 
--> Addition
--> Subtraction
--> Transpose
--> Multiplication of matricies
*/

class Matrix
{
    double [][] data;
    int rows, cols;

    /*Matrix object will hold 3 varaibles 
     * Data - 2D array that holds data of the matrix
     * Rows - number of rows in Matrix object
     * Cols - number of columns in matrix object
    */

    Matrix(int rows, int cols) /*constructor for initializing our matrix object with
    random values between -1 and 1 by passing rows and cols as parameters*/

    {
        data = new double [rows][cols];
        this.rows = rows;
        this.cols=cols;

        for (int i =0; i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                data[i][j]=Math.random()*2-1;
            }
        }
        
    }
    
    public void add(double scaler)
    {
        for(int i=0;i<rows;i++)
        {
            for (int j=0;j<cols;j++)
            {
                this.data[i][j] += scaler;
        
            }
        }
    }

    public void add(Matrix m)
    {
        if(cols!=m.cols|| rows !=m.rows)
        {
            System.out.println("Shape Mismatch");
            return;
        }

        for(int i =o;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j] += m.data [i][j];
            }
        }
    }
   //Endpoint .........  
}


