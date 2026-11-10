package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.*;

public class GradientDescentFactory {
    public static GradientDescentHandler createHandler(GradientAdjustmentParameters parameters) {
        LearningType descentType = LearningType.valueOf(parameters.getClass().getSimpleName());
        return switch (descentType) {
            case FixedLearningRateParameter -> new FixedLearningRateGradientDescent((FixedLearningRateParameter) parameters);
            case MomentumParameter -> new MomentumGradientDescent((MomentumParameter) parameters);
            case NesterovParameter -> new NesterovGradientDescent((NesterovParameter) parameters);
            case AdamParameter -> new AdamGradientDescent((AdamParameter) parameters);
            case InverseDecayLearningRateParameter -> new InverseDecayLearningRateGradientDescent((InverseDecayLearningRateParameter) parameters);
            default -> null;

        };
    }
}
