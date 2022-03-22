package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import LearningAlgorithm.*;

import java.lang.IllegalArgumentException;

public class DenseLayer extends Layer{
    protected double learningRate; 
    protected Matrix weights;
    protected Matrix bias;

    public DenseLayer(ActivationFunction activation, ErrorFunction errorFunction, int layerSize, int featureSize, double learningRate) {
        super(activation, errorFunction, layerSize, featureSize);
        this.learningRate = learningRate;
    }

    
    public void initializeLayer() throws IllegalArgumentException{
        if (prevLayer == null) {
            throw new IllegalArgumentException("This layer doesn't have linked layers.");
        }

        int previousLayerSize = prevLayer.getLayerSize();
        weights = new Matrix(layerSize, previousLayerSize);
        bias = new Matrix(layerSize, featureSize);

    }

    @Override
    public void feedForward(String selectedAlgorithm) {
        Matrix weightMultipliedByLayer = weights.multiply(prevLayer.getActivatedContents());
            
        layerContents = (weightMultipliedByLayer.add(bias));
        activatedLayerContents = activation.inputActivation(layerContents);       

        algorithm = selectLearningAlgorithm(selectedAlgorithm);
    }

    @Override
    public void backPropagation(Matrix targetMatrix, Matrix resultMatrix) throws IllegalArgumentException{
        if (algorithm == null) {
            throw new IllegalArgumentException("Make sure this layer an algorithm to backpropagate on");
        }
        
        Matrix deltaWeights = algorithm.calculateChangeWeights(targetMatrix, resultMatrix);
        Matrix deltaBias = algorithm.calculateChangeBias(targetMatrix, resultMatrix);

        weights.add(deltaWeights);
        bias.add(deltaBias);
        
        // if (targetValues.getNumRows() != numNeurons) {
        //     throw new IllegalArgumentException("Make sure the number of neurons of the target matrix is equal to be of the this layer");
        // }
        // else {
        //     Matrix deltaLossWeights = backPropagateWeights(targetValues);
        //     Matrix deltaLossBias = backPropagateBias(targetValues);

        //     deltaLossWeights = deltaLossWeights.scalarMultiply(-learningRate);
        //     deltaLossBias = deltaLossBias.scalarMultiply(-learningRate);

        //     weights = weights.add(deltaLossWeights);
        //     bias = bias.add(deltaLossBias);
        // }
    }

    protected LearningAlgorithm selectLearningAlgorithm(String algorithmName) {
        LearningAlgorithm algo;
        if (algorithmName.equals("Gradient Descent")) {
            algo = new GradientDescent(activation, errorFunction, weights, bias, layerContents, learningRate);
        }
        else {
            algo = new GradientDescent(activation, errorFunction, weights, bias, layerContents, learningRate);
        }
        return algo;
    }

    public void printAttributes() {
        System.out.println("Weights:");
        weights.printMatrix();
        System.out.println();

        System.out.println("Bias:");
        bias.printMatrix();
        System.out.println();
    }

    public Matrix getWeightRows(int[] indices) {
        return weights.getMultipleRows(indices);
    }

    public Matrix getBiasRows(int[] indices) {
        return bias.getMultipleRows(indices);
    }

    public void setLearningRate(double rate) {
        this.learningRate = rate;
    }
    
}