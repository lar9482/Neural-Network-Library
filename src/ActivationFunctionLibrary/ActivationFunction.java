package ActivationFunctionLibrary;
import MatrixLibrary.Matrix;

public abstract class ActivationFunction {
    protected abstract double Activation(double input);
    protected abstract double ActivationDerivative(double input);
    
    public Matrix inputActivation(Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = Activation(input.getData()[i][j]);
            }
        }
        return new Matrix(newData);
    } 

    public Matrix inputActivationDerivative(Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = ActivationDerivative(input.getData()[i][j]);
            }
        }
        return new Matrix(newData);
    } 
}
