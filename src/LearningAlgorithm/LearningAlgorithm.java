package LearningAlgorithm;
import ActivationFunctionLibrary.ActivationFunction;
import ErrorFunctionLibrary.ErrorFunction;
import MatrixLibrary.Matrix;

public abstract class LearningAlgorithm {
    protected ActivationFunction activation;
    protected ErrorFunction errorFunction;

    protected Matrix weights;
    protected Matrix bias;

    protected Matrix inputVector;
    protected Matrix layerContents;

    protected double learningRate;

    public LearningAlgorithm(ActivationFunction a, ErrorFunction e, Matrix w, Matrix b, Matrix layerContents, double alpha) {
        this.activation = a;
        this.errorFunction = e;

        this.weights = w;
        this.bias = b;
        this.layerContents = layerContents;

        this.learningRate = alpha;
    }

    public void setInputVector(Matrix inputVector) {
        this.inputVector = inputVector;
    }

    public abstract Matrix calculateChangeWeights(Matrix targetMatrix, Matrix resultMatrix);
    public abstract Matrix calculateChangeBias(Matrix targetMatrix, Matrix resultMatrix);
}
