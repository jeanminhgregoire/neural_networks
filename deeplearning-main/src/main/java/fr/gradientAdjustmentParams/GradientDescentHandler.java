package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.GradientAdjustmentParameters;
import org.ejml.simple.SimpleMatrix;

public abstract class GradientDescentHandler {
    protected GradientAdjustmentParameters parameters;

    public GradientDescentHandler(GradientAdjustmentParameters parameters) {
        this.parameters = parameters;
    }

    public abstract SimpleMatrix handleGradient(SimpleMatrix simpleMatrix, Boolean isWeights);
}