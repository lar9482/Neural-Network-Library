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

    public void backPropagation() {

    }
}
