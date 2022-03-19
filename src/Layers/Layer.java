package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;
import LearningAlgorithm.*;

public abstract class Layer {
    protected Matrix layerContents;
    protected Matrix activatedLayerContents;

    protected int layerSize;
    protected int featureSize;
    
    protected Layer prevLayer; 
    protected Layer nextLayer;

    protected ActivationFunction activation;
    protected ErrorFunction errorFunction;

    protected LearningAlgorithm algorithm; 

    public abstract void feedForward(String selectedAlgorithm);
    public abstract void backPropagation(Matrix targetMatrix, Matrix resultMatrix);

    public Layer(ActivationFunction activation, ErrorFunction errorFunction, int layerSize, int featureSize) {
        this.activation = activation;
        this.errorFunction = errorFunction;
        this.layerSize = layerSize;
        this.featureSize = featureSize;
    }

    public void linkLayers(Layer prev, Layer next) {
        this.prevLayer = prev;
        this.nextLayer = next;
    }

    public Matrix getActivatedContents() {
        return this.activatedLayerContents;
    }

    public int getLayerSize() {
        return this.layerSize;
    }

    public int getFeatureSize() {
        return this.featureSize;
    }

    public void setActivatedContents(Matrix contents) {
        this.activatedLayerContents = contents;
    } 
}
