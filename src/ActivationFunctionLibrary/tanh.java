package ActivationFunctionLibrary;

public class tanh extends ActivationFunction{
    protected double Activation(double input) {
        return ((Math.exp(input)-Math.exp(-input))/(Math.exp(input)+Math.exp(-input)));
    }

    protected double ActivationDerivative(double input) {
        return (1-Activation(input)*Activation(input));
    }
}
