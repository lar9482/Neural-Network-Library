package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;

public abstract class Layer {
    protected Matrix layerContents;
    protected Matrix activatedLayerContents;
    protected int numNeurons;
    
    protected Layer prevLayer; 

    protected ActivationFunction activation;
    protected ErrorFunction errorFunction;

    public abstract void feedForward();
    public abstract void backPropagation(Matrix targetValues);
    public abstract void initializeLayer(int size);

    public Layer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        this.activation = activation;
        this.errorFunction = errorFunction;
        this.numNeurons = size;
        linkLayers(null);
    }
    
    protected boolean checkDimensions(int size) {
        if (prevLayer.getSize() != size) {
            return false;
        }
        else {
            return true;
        }
    }

    public void linkLayers(Layer prev) {
        this.prevLayer = prev;
    }

    public Matrix getActivatedContents() {
        return this.activatedLayerContents;
    }

    public int getSize() {
        return this.numNeurons;
    }
}
