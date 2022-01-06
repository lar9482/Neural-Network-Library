package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class InputLayer extends DenseLayer{
    
    public InputLayer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        super(activation, errorFunction, size);
    }

    @Override
    public void initializeLayer(int size) throws IllegalArgumentException {
        this.numNeurons = size;
    }

    public void feedDataIn(Matrix layerContents) {
        this.layerContents = layerContents;
        this.activatedLayerContents = activation.inputActivation(this.layerContents);
    }

    //No implementation for these functions because they will not be used.
    @Override
    public void feedForward() throws IllegalArgumentException {
        throw new IllegalArgumentException("'feedForward' isn't a legal function in an input layer");
    }
    @Override
    public void backPropagation(Matrix targetValues) throws IllegalArgumentException{
        throw new IllegalArgumentException("'backPropagation' isn't a legal function in an input layer");
    }
}
