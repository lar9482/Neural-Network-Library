package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import java.lang.IllegalArgumentException;

public class InputLayer extends Layer{
    
    public InputLayer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        super(activation, errorFunction, size);
        initializeLayer(size);
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

    public static void main(String[] args) {
        ErrorFunction errorFunction = new CrossEntropy();
        ActivationFunction activation = new sigmoid();
        int size = 10;
        double learningRate = 0.5;
        double[][] data = {
            {1},
            {2},
            {3},
            {4},
            {5},
            {6},
            {7},
            {8},
            {9},
            {10}
        };


        Matrix dataMatrix = new Matrix(data);
        InputLayer dummyLayer = new InputLayer(activation, errorFunction, size);
        DenseLayer testLayer = new DenseLayer(activation, errorFunction, size, dummyLayer);


        dummyLayer.feedDataIn(dataMatrix);
        testLayer.initializeLayer(size);
        testLayer.setLearningRate(learningRate);
        

        double[][] target = {
            {1},
            {0},
            {0},
            {0},
            {0},
            {0},
            {0},
            {0},
            {0},
            {0}
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
