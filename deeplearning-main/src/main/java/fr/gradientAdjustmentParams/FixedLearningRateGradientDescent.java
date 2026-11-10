package fr.gradientAdjustmentParams;
import fr.ensimag.deep.serialization.json.FixedLearningRateParameter;
import org.ejml.simple.SimpleMatrix;


class FixedLearningRateGradientDescent extends GradientDescentHandler {

    protected FixedLearningRateParameter parameter;
    public FixedLearningRateGradientDescent(FixedLearningRateParameter parameters) {
        super(parameters);
        parameter = parameters;
    }

    @Override
    public SimpleMatrix handleGradient(SimpleMatrix simpleMatrix, Boolean isWeights) {

        return simpleMatrix.scale(-parameter.getLearningRate());
    }


}
