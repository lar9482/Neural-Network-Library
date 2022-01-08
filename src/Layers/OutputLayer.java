package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;

public class OutputLayer extends DenseLayer {
    public OutputLayer(ActivationFunction activation, ErrorFunction errorFunction, int size, Layer prevLayer) {
        super(activation, errorFunction, size, prevLayer);
    }

    
}
