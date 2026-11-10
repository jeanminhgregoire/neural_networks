package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.InverseDecayLearningRateParameter;
import org.ejml.simple.SimpleMatrix;

public class InverseDecayLearningRateGradientDescent extends GradientDescentHandler {
    private int timestepWeights;  // Timestep spécifique pour les poids
    private int timestepBias;    // Timestep spécifique pour les biais
    protected InverseDecayLearningRateParameter parameter;

    public InverseDecayLearningRateGradientDescent(InverseDecayLearningRateParameter parameters) {
        super(parameters);
        parameter = parameters;
        timestepWeights = 0;
        timestepBias = 0;
    }

    @Override
    public SimpleMatrix handleGradient(SimpleMatrix gradient, Boolean isWeights) {
        // Si c'est pour les poids
        if (isWeights) {
            double decayedLearningRate = parameter.getLearningRate() / (1 + parameter.getDecreaseFactorRate() * timestepWeights);
            timestepWeights++;
            return gradient.scale(-decayedLearningRate);  // Application du taux d'apprentissage décroissant pour les poids
        }
        // Si c'est pour les biais
        else {
            double decayedLearningRate = parameter.getLearningRate() / (1 + parameter.getDecreaseFactorRate() * timestepBias);
            timestepBias++;
            return gradient.scale(-decayedLearningRate);  // Application du taux d'apprentissage décroissant pour les biais
        }
    }
}
