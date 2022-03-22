package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import LearningAlgorithm.*;

import java.lang.IllegalArgumentException;

public class OutputLayer extends DenseLayer {

    public OutputLayer(ActivationFunction activation, ErrorFunction errorFunction, int layerSize, int featureSize, double learningRate) {
        super(activation, errorFunction, layerSize, featureSize, learningRate);
    }

    public Matrix getTargetMatrix() {
        return this.activatedLayerContents;
    }

    public boolean validTargetMatrix(Matrix resultMatrix){
        return (resultMatrix.getNumRows() != activatedLayerContents.getNumRows()) && (resultMatrix.getNumCols() != activatedLayerContents.getNumCols());
    }

    public double calculateError(Matrix resultMatrix) throws IllegalArgumentException{
        if (!validTargetMatrix(resultMatrix)) {
            throw new IllegalArgumentException("The result matrix must be the same dimensions as the targetMatrix");
        }

        errorFunction.InputError(resultMatrix, activatedLayerContents);
        errorFunction.numError();
        return errorFunction.getError();
    }


}
