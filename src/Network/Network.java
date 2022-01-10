package Network;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import Layers.*;
import MatrixLibrary.*;
import java.util.LinkedList;

public class Network {
    private InputLayer input;
    private LinkedList<DenseLayer> hidden_layers;
    private OutputLayer output;

    private Matrix targetValues;
    private double learningRate;
    private int numNeurons;

    private ActivationFunction activation;
    private ErrorFunction loss;

    public Network(ActivationFunction activation, ErrorFunction lossFunction, double learningRate, int size) {
        this.activation = activation;
        this.loss = lossFunction;
        this.learningRate = learningRate;
        this.numNeurons = size;
    }

    public void setTargetValues(Matrix values) {
        this.targetValues = values;
    }

}
