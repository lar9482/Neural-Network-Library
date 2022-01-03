package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;

public abstract class Layer {
    protected Matrix layerContents;
    protected Matrix activatedLayerContents;
    protected int numNeurons;

    protected Layer nextLayer;
    protected Layer prevLayer; 

    protected ActivationFunction activation;
    protected ErrorFunction errorFunction;

    protected abstract void feedForward();
    protected abstract void backPropagation();

    public Layer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        this.activation = activation;
        this.errorFunction = errorFunction;
        initializeLayer(size);
    }
    
    protected boolean checkDimensions() {
        if (layerContents.getNumCols() != 1) {
            return false;
        }
        else {
            return true;
        }
    }

    protected void initializeLayer(int size) {
        this.layerContents = new Matrix(size, 1);
        this.numNeurons = size;
    }

    public void setCurrentContents(Matrix layerContents) {
        this.layerContents = layerContents;
    }

    public void setNext(Layer layerContents) {
        this.nextLayer = layerContents;
    }

    public void setPrev(Layer layerContents) {
        this.prevLayer = layerContents;
    }
}
