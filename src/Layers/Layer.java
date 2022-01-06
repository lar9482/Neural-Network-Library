package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;

public abstract class Layer {
    protected Matrix layerContents;
    protected Matrix activatedLayerContents;
    protected int numNeurons;
    
    protected Layer prevLayer; 
    protected Layer nextLayer;

    protected ActivationFunction activation;
    protected ErrorFunction errorFunction;

    public abstract void feedForward();
    public abstract void backPropagation(Matrix targetValues);
    public abstract void initializeLayer(int size);

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

    public void linkLayers(Layer prev, Layer next) {
        this.prevLayer = prev;
        this.nextLayer = next;
    }

    public void setCurrentContents(Matrix layerContents) {
        this.layerContents = layerContents;
    }   

    public Matrix getActivatedContents() {
        return this.activatedLayerContents;
    }

    public int getSize() {
        return this.numNeurons;
    }
}
