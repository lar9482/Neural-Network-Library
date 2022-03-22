package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class InputLayer extends Layer{
    
    // public InputLayer(ActivationFunction activation, ErrorFunction errorFunction) {
    //     super(activation, errorFunction);   
    // }

    public InputLayer() {
        super();
    }

    private void logDimensions(int layerSize, int featureSize) {
        this.layerSize = layerSize;
        this.featureSize = featureSize;
    }

    public void feedDataIn(Matrix layerContents) {
        this.layerContents = layerContents;
        this.activatedLayerContents = activation.inputActivation(this.layerContents);

        logDimensions(layerContents.getNumRows(), layerContents.getNumCols());
    }

    //No implementation for these functions because they will not be used.
    @Override
    public void feedForward(String selectedLearningAlgorithm) throws IllegalArgumentException {
        throw new IllegalArgumentException("'feedForward' isn't a legal function in an input layer");
    }
    @Override
    public void backPropagation(Matrix targetMatrix, Matrix resultMatrix) throws IllegalArgumentException{
        throw new IllegalArgumentException("'backPropagation' isn't a legal function in an input layer");
    }
}
