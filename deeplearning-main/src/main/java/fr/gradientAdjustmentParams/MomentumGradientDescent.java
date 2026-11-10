package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.MomentumParameter;
import org.ejml.simple.SimpleMatrix;

public class MomentumGradientDescent extends GradientDescentHandler{

    private SimpleMatrix velocityWeights;
    private SimpleMatrix velocityBias;
    protected MomentumParameter parameter;

    public MomentumGradientDescent(MomentumParameter parameters) {
        super(parameters);
        parameter = parameters;
        velocityWeights = null;
        velocityBias = null;
    }

    @Override
    public SimpleMatrix handleGradient(SimpleMatrix gradient, Boolean isWeights) {
        if (isWeights) {
            if (velocityWeights == null) {
                velocityWeights = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
            }
            velocityWeights = velocityWeights.scale(parameter.getMomentum()).minus(gradient.scale(parameter.getLearningRate()));
            return velocityWeights;
        }
        else {
            if (velocityBias == null) {
                velocityBias = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
            }
            velocityBias = velocityBias.scale(parameter.getMomentum()).minus(gradient.scale(parameter.getLearningRate()));
            return velocityBias;
        }
    }
}