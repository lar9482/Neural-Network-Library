package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class InputLayer extends Layer{
    
    public InputLayer(ActivationFunction activation, ErrorFunction errorFunction, int sampleSize, int featureSize) {
        super(activation, errorFunction, sampleSize, featureSize);
        initializeLayer(sampleSize, featureSize);
    }

    @Override
    public void initializeLayer(int sampleSize, int featureSize) throws IllegalArgumentException {
        this.sampleSize = sampleSize;
        this.featureSize = featureSize;
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

    public static void main(String[] args) {
        ErrorFunction errorFunction = new CrossEntropy();
        ActivationFunction activation = new sigmoid();
        int size = 10;
        double learningRate = 0.5;
        double[][] data = {
            {1, 1},
            {2, 2},
            {3, 2},
            {4, 2},
            {5, 2},
            {6, 2},
            {7, 2},
            {8, 2},
            {9, 2},
            {10, 0}
        };


        Matrix dataMatrix = new Matrix(data);
        InputLayer dummyLayer = new InputLayer(activation, errorFunction, size);
        DenseLayer testLayer = new DenseLayer(activation, errorFunction, size, dummyLayer);


        dummyLayer.feedDataIn(dataMatrix);
        // testLayer.initializeLayer(size);
        testLayer.setLearningRate(learningRate);
        9
        double[][] target = {
            {1, 1},
            {2, 2},
            {3, 2},
            {4, 2},
            {5, 2},
            {6, 2},
            {7, 2},
            {8, 2},
            {9, 2},
            {10, 0}
        };
        Matrix targetMatrix = new Matrix(target);
        testLayer.feedForward();
        testLayer.backPropagation(targetMatrix);

        testLayer.printAttributes();

        testLayer.backPropagation(targetMatrix);
        testLayer.backPropagation(targetMatrix);
        testLayer.backPropagation(targetMatrix);
        testLayer.printAttributes();
    }
}
