package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.NesterovParameter;
import org.ejml.simple.SimpleMatrix;

public class NesterovGradientDescent extends GradientDescentHandler {
    // Velocités séparées pour les poids et les biais
    private SimpleMatrix velocityWeights;
    private SimpleMatrix velocityBias;

    protected NesterovParameter parameter;

    public NesterovGradientDescent(NesterovParameter parameters) {
        super(parameters);
        parameter = parameters;
        velocityWeights = null;
        velocityBias = null;
    }

    @Override
    public SimpleMatrix handleGradient(SimpleMatrix gradient, Boolean isWeights) {
        // Initialisation des vitesses si nécessaire
        if (isWeights) {
            if (velocityWeights == null) {
                velocityWeights = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
            }
            // Calcul de la prévision (lookahead) pour la mise à jour des poids
            SimpleMatrix lookahead = velocityWeights.scale(parameter.getMomentum())
                    .minus(gradient.scale(parameter.getLearningRate()));

            // Mise à jour de la vélocité des poids (soustraction)
            velocityWeights = velocityWeights.scale(parameter.getMomentum())
                    .minus(gradient.scale(parameter.getLearningRate()));

            return lookahead; // retour du lookahead pour les poids
        } else {
            if (velocityBias == null) {
                velocityBias = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
            }
            // Calcul de la prévision (lookahead) pour la mise à jour des biais
            SimpleMatrix lookahead = velocityBias.scale(parameter.getMomentum())
                    .minus(gradient.scale(parameter.getLearningRate()));

            // Mise à jour de la vélocité des biais (soustraction)
            velocityBias = velocityBias.scale(parameter.getMomentum())
                    .minus(gradient.scale(parameter.getLearningRate()));

            return lookahead; // retour du lookahead pour les biais
        }
    }
}