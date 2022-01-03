package ActivationFunctionLibrary;

public class leakyrelu {
    protected double Activation(double input) {
        return (Math.max(0.1*input, input));
    }

    protected double ActivationDerivative(double input) {
        if (input < 0.0) {
            return 0.1;
        }
        else {
            return 1.0;
        }
    }
}
