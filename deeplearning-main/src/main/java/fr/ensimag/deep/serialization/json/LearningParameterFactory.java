package fr.ensimag.deep.serialization.json;



public class LearningParameterFactory {
    public static GradientAdjustmentParameters create(String learningType) {
        GradientAdjustmentParameters parameters = null;

        if ("FixedLearningRate".equals(learningType))
            parameters = new FixedLearningRateParameter();
        else if ("InverseDecayLearningRate".equals(learningType))
            parameters = new InverseDecayLearningRateParameter();
        else if ("Momentum".equals(learningType))
            parameters = new MomentumParameter();
        else if ("Nesterov".equals(learningType))
            parameters = new NesterovParameter();
        else if ("Adam".equals(learningType))
            parameters = new AdamParameter();

        return parameters;
    }
}


