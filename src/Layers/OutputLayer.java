package Layers;
import MatrixLibrary.Matrix;
import ActivationFunctionLibrary.*;
import ErrorFunctionLibrary.*;

public class OutputLayer extends DenseLayer {
    public OutputLayer(ActivationFunction activation, ErrorFunction errorFunction, int size) {
        super(activation, errorFunction, size);
    }

    
}
