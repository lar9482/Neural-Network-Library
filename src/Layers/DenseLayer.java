package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class DenseLayer extends Layer{
    private double learningRate; 
    private Matrix weights;
    private Matrix bias;

    public DenseLayer(ActivationFunction activation, ErrorFunction errorFunction, int size, Layer prevLayer) {
        super(activation, errorFunction, size);
        this.prevLayer = prevLayer;
    }

    public void initializeLayer(int size) throws IllegalArgumentException{
        if (prevLayer == null) {
            throw new IllegalArgumentException("Link a previous layer before iniitializing this layer.");
        }

        weights = new Matrix(prevLayer.getSize(), size);
        bias = new Matrix(size, 1);
    }
    
    public void feedForward() throws IllegalArgumentException{
        if (checkDimensions(numNeurons)) {
            Matrix weightsTranspose = weights.transpose();
            Matrix weightMultipliedByLayer = weightsTranspose.multiply(prevLayer.getActivatedContents());

            layerContents = (weightMultipliedByLayer.add(bias));
            activatedLayerContents = activation.inputActivation(layerContents);
        }
        else {
            throw new IllegalArgumentException("Make sure the number of neurons of this layer is equal to be of the previous layer");
        }
    }

    public void setLearningRate(double rate) {
        this.learningRate = rate;
    }


    public void backPropagation(Matrix targetValues) {
        Matrix deltaLossWeights = backPropagateWeights(targetValues);
        Matrix deltaLossBias = backPropagateBias(targetValues);

        deltaLossWeights = deltaLossWeights.scalarMultiply(-learningRate);
        deltaLossBias = deltaLossBias.scalarMultiply(-learningRate);

        weights = weights.add(deltaLossWeights);
        bias = bias.add(deltaLossBias);
    }

    private Matrix backPropagateWeights(Matrix targetValues) {
        Matrix deltaLossInput = calculateDeltaLossInput(targetValues);

        //eltaLossInput.printMatrix();
        return deltaLossInput.multiply(prevLayer.getActivatedContents().transpose());
    }

    private Matrix backPropagateBias(Matrix targetValues) {
        Matrix deltaLossInput = calculateDeltaLossInput(targetValues);

        return deltaLossInput;
    }

    private Matrix calculateDeltaLossInput(Matrix targetValues) {
        //Calculating partial deriative of loss function with respect to the activation function.
        errorFunction.InputErrorDerivative(targetValues, activatedLayerContents);
        errorFunction.numDerivativeError();
        double deltaLossActivate = errorFunction.getErrorDerivative();

        //Calculating partial deriative of activation function with respect to the input matrix.
        Matrix deltaActivationInput = activation.inputActivationDerivative(layerContents);

        return deltaActivationInput.scalarMultiply(deltaLossActivate);
    }

    public void printAttributes() {
        System.out.println("Weights:");
        weights.printMatrix();
        System.out.println();

        System.out.println("Bias:");
        bias.printMatrix();
        System.out.println();

    }
}