package ActivationFunctionLibrary;

public class relu extends ActivationFunction{
    protected double Activation(double input) {
        return (Math.max(0.0, input));
    }

    protected double ActivationDerivative(double input) {
        if (input < 0.0) {
            return 0;
        }
        else {
            return 1.0;
        }
    }
}
