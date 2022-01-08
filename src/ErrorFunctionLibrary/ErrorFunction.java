package ErrorFunctionLibrary;
import MatrixLibrary.Matrix;

public abstract class ErrorFunction {
    protected double error;
    protected double errorDerivative;
    protected Matrix errorMatrix;
    protected Matrix errorDerivativeMatrix;
    protected abstract double Error(double target, double input);
    protected abstract double ErrorDerivative(double target, double input);

    public void InputError(Matrix target, Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = Error(target.getData()[i][j], input.getData()[i][j]);
            }
        }
        errorMatrix = new Matrix(newData);
    }
    
    public void InputErrorDerivative(Matrix target, Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = ErrorDerivative(target.getData()[i][j], input.getData()[i][j]);
            }
        }
        errorDerivativeMatrix = new Matrix(newData);
    }

    public void numError() {
        double sum = 0.0;
        for (int i = 0; i < errorMatrix.getNumRows(); i++) {
            for (int j = 0; j < errorMatrix.getNumCols(); j++) {
                sum += errorMatrix.getData()[i][j];
            }
        }
        error = sum;
    }

    public void numDerivativeError() {
        double sum = 0.00;
        for (int i = 0; i < errorDerivativeMatrix.getNumRows(); i++) {
            for (int j = 0; j < errorDerivativeMatrix.getNumCols(); j++) {
                sum += errorDerivativeMatrix.getData()[i][j];
            }
        }
        errorDerivative = sum;
    }

    public Matrix getErrorMatrix() {
        return this.errorMatrix;
    }

    public Matrix getErrorDerivativeMatrix() {
        return this.errorDerivativeMatrix;
    }

    public double getError() {
        return this.error;
    }

    public double getErrorDerivative() {
        return this.errorDerivative;
    }
}
