package ErrorFunctionLibrary;
import MatrixLibrary.Matrix;

public class MSE extends ErrorFunction {
    protected double Error(double target, double input) {
        return (input - target)*(input-target);
    }
    protected double ErrorDerivative(double target, double input) {
        return 2*(input-target);
    }
    @Override
    public void InputError(Matrix target, Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = Error(target.getData()[i][j], input.getData()[i][j])/(target.getNumRows()*target.getNumCols());
            }
        }
        errorMatrix = new Matrix(newData);
    }
    @Override
    public void InputErrorDerivative(Matrix target, Matrix input) {
        double[][] newData = new double[input.getNumRows()][input.getNumCols()];
        for (int i = 0; i < input.getNumRows(); i++) {
            for (int j = 0; j < input.getNumCols(); j++) {
                newData[i][j] = ErrorDerivative(target.getData()[i][j], input.getData()[i][j])/(target.getNumRows()*target.getNumCols());
            }
        }
        errorDerivativeMatrix = new Matrix(newData);
    }
}
