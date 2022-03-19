package LearningAlgorithm;
import ActivationFunctionLibrary.ActivationFunction;
import ErrorFunctionLibrary.ErrorFunction;
import MatrixLibrary.Matrix;

import java.lang.NullPointerException;

public class GradientDescent extends LearningAlgorithm{

    public GradientDescent(ActivationFunction a, ErrorFunction e, Matrix w, Matrix b, Matrix layerContents, double alpha) {
        super(a, e, w, b, layerContents, alpha);
    }

    public Matrix calculateChangeWeights(Matrix targetMatrix, Matrix resultMatrix){
        double deltaLossInput = calculateDeltaLossInput(targetMatrix, resultMatrix);
        Matrix deltaInputActivation = calculateDeltaInputActivation();
        Matrix deltaActivationWeights = calculateDeltaActivationWeights();
        
        deltaInputActivation.scalarMultiply(deltaLossInput);

        Matrix result = deltaInputActivation.multiply(deltaActivationWeights);
        result.scalarMultiply(-learningRate);

        return result;
    }

    public Matrix calculateChangeBias(Matrix targetMatrix, Matrix resultMatrix){
        double deltaLossInput = calculateDeltaLossInput(targetMatrix, resultMatrix);
        Matrix deltaInputActivation = calculateDeltaInputActivation();
        deltaInputActivation.scalarMultiply(deltaLossInput);
        
        deltaInputActivation.scalarMultiply(-learningRate);
        return deltaInputActivation;
    }

    private double calculateDeltaLossInput(Matrix targetMatrix, Matrix resultMatrix) {
        errorFunction.InputErrorDerivative(targetMatrix, resultMatrix);
        errorFunction.numDerivativeError();

        return errorFunction.getErrorDerivative();
    }

    private Matrix calculateDeltaInputActivation() {
        return activation.inputActivationDerivative(layerContents);

    }

    private Matrix calculateDeltaActivationWeights() {
        return weights.transpose();
    }
}