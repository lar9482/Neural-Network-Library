package Network;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import Layers.*;
import MatrixLibrary.*;
import java.util.LinkedList;

public class Network {
    private InputLayer input;
    private LinkedList<DenseLayer> hiddenLayers;
    private OutputLayer output;

    private Matrix targetValues;
    private int featureSize;

    public Network(int featureSize) {
        this.featureSize = featureSize;
        this.input = new InputLayer();
        this.hiddenLayers = new LinkedList<DenseLayer>();
    }

    public void addLayer(ActivationFunction activation, ErrorFunction errorFunction, int samplingSize, double learningRate) {
        DenseLayer layer = new DenseLayer(activation, errorFunction, samplingSize, featureSize, learningRate);
        
        if (hiddenLayers.size() == 0) {
            layer.linkLayers(input);
        }
        else {
            layer.linkLayers(hiddenLayers.getLast());
        }

        layer.initializeLayer();
        hiddenLayers.add(layer);
        
    }

    private void setUpInputLayer() {

    }

    private void setUpOutputLayer() {

    }
    
    public void setTargetValues(Matrix values) {
        this.targetValues = values;
    }   

    public void fit(Matrix inputMatrix, Matrix outputMatrix) {

    }

    public void predict(Matrix inputMatrix) {
        
    }
}
