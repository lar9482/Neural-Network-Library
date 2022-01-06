package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class DenseLayer extends Layer{
    private double learningRate; 
    private Matrix weights;
    private Matrix bias;

    public DenseLayer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        super(activation, errorFunction, size);
    }

    public void initializeLayer(int size) throws IllegalArgumentException{
        if (prevLayer == null) {
            throw new IllegalArgumentException("Link a previous layer before iniitializing this layer.");
        }

        weights = new Matrix(prevLayer.getSize(), size);
        bias = new Matrix(size, 1);
    }
    
    public void feedForward() throws IllegalArgumentException{
        if (checkDimensions()) {
            Matrix weightsTranspose = weights.transpose();
            Matrix weightMultipliedByLayer = weightsTranspose.multiply(prevLayer.getActivatedContents());

            layerContents = (weightMultipliedByLayer.add(bias));
            activatedLayerContents = activation.inputActivation(layerContents);
        }
        else {
            throw new IllegalArgumentException("Make sure the 'layerContent' matrix is of the correct dimensions");
        }
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
        return deltaLossInput.multiply(prevLayer.getActivatedContents());
    }

    private Matrix backPropagateBias(Matrix targetValues) {
        Matrix deltaLossInput = calculateDeltaLossInput(targetValues);

        return deltaLossInput.multiply(generateOneMatrix(bias.getNumRows()));
    }

    private Matrix calculateDeltaLossInput(Matrix targetValues) {
        //Calculating partial deriative of loss function with respect to the activation function.
        errorFunction.InputErrorDerivative(targetValues, activatedLayerContents);
        Matrix deltaLossActivate = errorFunction.getErrorDerivativeMatrix();

        //Calculating partial deriative of activation function with respect to the input matrix.
        Matrix deltaActivationInput = activation.inputActivationDerivative(layerContents);
        deltaActivationInput = deltaActivationInput.transpose();

        return deltaLossActivate.multiply(deltaActivationInput);
    }

    private Matrix generateOneMatrix(int size) {
        double newData[][] = new double[size][1];

        for (int i = 0; i < size; i++) {
            newData[i][0] = 1.0;
        }

        return new Matrix(newData);
    }
}