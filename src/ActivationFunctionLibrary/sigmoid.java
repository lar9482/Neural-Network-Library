package ActivationFunctionLibrary;
import java.lang.Math;
import MatrixLibrary.Matrix;

public class sigmoid extends ActivationFunction{
    protected double Activation(double input) {
        return ((Math.exp(input))/(Math.exp(input)+1));
    }

    protected double ActivationDerivative(double input) {
        return ((Activation(input))*(1-Activation(input)));
    }


    public static void main(String args[]) {
        double[][] data = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        Matrix test = new Matrix(data);
        sigmoid function = new sigmoid();
        Matrix output = function.inputActivation(test);

        output.printMatrix();

    }
}
