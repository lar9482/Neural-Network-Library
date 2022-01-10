package MatrixLibrary;
import java.util.Random;
import java.lang.Math;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

public class Matrix {

    private double[][] data;
    private int rows;
    private int cols;
    private boolean isSquare;

    
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        this.data = generateRandomMatrix(1);
        this.isSquare = (rows == cols);
    }

    public Matrix(int rows, int cols, int upperBound) {
        this.rows = rows;
        this.cols = cols;

        this.data = generateRandomMatrix(upperBound);
        this.isSquare = (rows == cols);
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.cols = data[0].length;

        this.data = data;
        this.isSquare = (rows == cols);
    }

    private double[][] generateRandomMatrix(int upperBound) {
        Random rand = new Random(upperBound);
        double[][] dataMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dataMatrix[i][j] = rand.nextDouble();
            }
        }

        return dataMatrix;
    }

    public void setValue(int rowValue, int colValue, double value) {
        if (!(rowValue > 0 || rows > rowValue) || !(colValue > 0 || cols > colValue)) {
            System.out.println("Whoops, make sure input an acceptable row or column value");
            System.out.printf("rows %d: cols %d", rows, cols);
        }
        else {
            data[rowValue][colValue] = value;
        }
    }

    public Matrix getMultipleRows(int[] indices) throws IllegalArgumentException{
        Arrays.sort(indices);
        double[][] newData = new double[indices.length][cols];
        if (!indicesBoundCheck(0, rows, indices)) {
            throw new IllegalArgumentException("Whoops, make sure the input rowlist is within the bounds " +0+ " and " +(rows-1));
        }
        else {
            for (int i = 0; i < indices.length; i++) {
                for (int j = 0; j < cols; j++) {
                    newData[i][j] = data[indices[i]][j];
                }
            }
        }

        return new Matrix(newData);
    }

    public Matrix getMultipleColumns(int[] indices) throws IllegalArgumentException{
        Arrays.sort(indices);
        double[][] newData = new double[rows][indices.length];
        if (!indicesBoundCheck(0, cols, indices)) {
            throw new IllegalArgumentException("Whoops, make sure the input rowlist is within the bounds " +0+ " and " +(cols-1));
        }
        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < indices.length; j++) {
                    newData[i][j] = data[i][indices[j]];
                }
            }
        }
        return new Matrix(newData);
    }

    public Matrix setMultipleRows(double[][] inputData, int[] indices) {
        Arrays.sort(indices);
        double[][] newData = new double[rows][cols];
        if (!indicesBoundCheck(0, rows, indices)) {
            throw new IllegalArgumentException("Whoops, make sure the input rowlist is within the bounds" +0+ " and " +rows);
        }
        else {
            int curIndex = 0;
            
            for (int i = 0; i < rows; i++) {
                if ((curIndex < indices.length) && (i==indices[curIndex])) {
                    for (int j = 0; j < inputData[curIndex].length; j++) {
                        newData[i][j] = inputData[curIndex][j];
                    }
                    curIndex++;
                }
                else {
                    for (int j = 0; j < cols; j++) {
                        newData[i][j] = data[i][j];
                    }
                }
            }
        }

        return new Matrix(newData);
    }

    public Matrix setMultipleColumns(double[][] inputData, int[] indices) {
        Arrays.sort(indices);
        double[][] newData = new double[rows][cols];
        if(!indicesBoundCheck(0, cols, indices)) {
            throw new IllegalArgumentException("Whoops, make sure the input collist is within the bounds" +0+ " and " +cols);
        }
        else {
            int curIndex = 0;
            
            for (int i = 0; i < cols; i++) {
                if ((curIndex < indices.length) && (i == indices[curIndex])) {
                    for (int j = 0; j < rows; j++) {
                        newData[j][i] = inputData[j][curIndex];
                    }
                    curIndex++;
                }
                else {
                    for (int j = 0; j < rows; j++) {
                        newData[j][i] = data[j][i];
                    }
                }
            }
        }

        return new Matrix(newData);
    }

    private boolean indicesBoundCheck(int lowerBound, int upperBound, int[] indexList) {
        boolean valid = true;
        for (int i = 0; i < indexList.length; i++) {
            if(!(indexList[i] >= lowerBound) || !(upperBound > indexList[i])) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public Matrix add(Matrix matrix) throws IllegalArgumentException {
        double[][] newData = new double[rows][cols];
        if ((rows != matrix.getNumRows()) || (cols != matrix.getNumCols())) {
            throw new IllegalArgumentException("Make sure both matrices have the same number of rows AND columns");
        } 
        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    newData[i][j] = data[i][j] + matrix.getData()[i][j];
                }
            }
        }
        return new Matrix(newData);
    }

    public Matrix multiply(Matrix matrix) throws IllegalArgumentException{
        double[][] newData = new double[rows][matrix.getNumCols()];
        if (cols != matrix.getNumRows()) {
            throw new IllegalArgumentException("Make sure the number of columns equals the number of rows:(Dimensionality error)");
        }

        else {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < matrix.getNumCols(); j++) {
                    double sum = 0;
                    for (int k = 0; k < cols; k++) {
                        sum += data[i][k]*matrix.getData()[k][j];
                    }
                    newData[i][j] = sum;
                }  
            }
        }

        return new Matrix(newData);
    }

    public Matrix scalarMultiply(double alpha) {
        double[][] newData = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                newData[i][j] = alpha*data[i][j];
            }
        }
        return new Matrix(newData);
    }

    public Matrix transpose() {
        double[][] newData = new double[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                newData[i][j] = data[j][i];
            }
        }
        return new Matrix(newData);
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j]);
                System.out.print(" ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public int getNumRows() {
        return this.rows;
    }
    public int getNumCols() {
        return this.cols;
    }
    public double[][] getData() {
        return this.data;
    }
    public static void main(String args[]) {
    
    }
}