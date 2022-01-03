package ErrorFunctionLibrary;
import java.lang.Math;
import MatrixLibrary.Matrix;

public class CrossEntropy extends ErrorFunction{
    protected double Error(double target, double input) {
        return -((target*Math.log(input)) - (1.0-target)*(Math.log(1.0 - input)));
    }

    protected double ErrorDerivative(double target, double input) {
        return -((target/input)+(1-target)/(1-input));
    }

    public static void main(String args[]) {
        ErrorFunction errorTest = new CrossEntropy();

        // double[][] data1 = {
        //     {2, 2, 2},
        //     {2, 2, 2},
        //     {2, 2, 2}
        // };

        // double[][] data2 = {
        //     {0.5, 0.5, 0.5},
        //     {0.5, 0.5, 0.5},
        //     {0.5, 0.5, 0.5}
        // };

        double[][] data1 = {
            {2}
        };

        double[][] data2 = {
            {0.9}
        };

        errorTest.InputError(new Matrix(data1), new Matrix(data2));
        errorTest.numError();
        System.out.print(errorTest.getError());

    }
}
