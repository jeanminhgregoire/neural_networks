package fr.gradientAdjustmentParams;

import fr.ensimag.deep.serialization.json.AdamParameter;
import org.ejml.simple.SimpleMatrix;

public class AdamGradientDescent extends GradientDescentHandler {

    // Moments et timestep pour les poids
    private SimpleMatrix firstMomentWeights;
    private SimpleMatrix secondMomentWeights;
    private int timestepWeights;

    // Moments et timestep pour les biais
    private SimpleMatrix firstMomentBias;
    private SimpleMatrix secondMomentBias;
    private int timestepBias;

    protected AdamParameter parameter;

    public AdamGradientDescent(AdamParameter parameters) {
        super(parameters);
        parameter = parameters;

        firstMomentWeights = null;
        secondMomentWeights = null;
        timestepWeights = 0;

        firstMomentBias = null;
        secondMomentBias = null;
        timestepBias = 0;
    }

    @Override
    public SimpleMatrix handleGradient(SimpleMatrix gradient, Boolean isWeights) {
        // Sélectionne les bons moments et timestep selon le type
        SimpleMatrix firstMoment;
        SimpleMatrix secondMoment;
        int timestep;

        if (isWeights) {
            if (firstMomentWeights == null || secondMomentWeights == null) {
                firstMomentWeights = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
                secondMomentWeights = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
                timestepWeights = 0;
            }

            // Incrémenter le timestep spécifique aux weights
            timestepWeights++;
            timestep = timestepWeights;

            // Récupérer les moments
            firstMoment = firstMomentWeights;
            secondMoment = secondMomentWeights;

            // Mise à jour des moments
            firstMomentWeights = firstMoment.scale(parameter.getFirstMomentDecay())
                    .plus(gradient.scale(1 - parameter.getFirstMomentDecay()));

            secondMomentWeights = secondMoment.scale(parameter.getSecondMomentDecay())
                    .plus(gradient.elementMult(gradient).scale(1 - parameter.getSecondMomentDecay()));

            firstMoment = firstMomentWeights;
            secondMoment = secondMomentWeights;

        } else {
            if (firstMomentBias == null || secondMomentBias == null) {
                firstMomentBias = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
                secondMomentBias = new SimpleMatrix(gradient.getNumRows(), gradient.getNumCols());
                timestepBias = 0;
            }

            // Incrémenter le timestep spécifique aux bias
            timestepBias++;
            timestep = timestepBias;

            // Récupérer les moments
            firstMoment = firstMomentBias;
            secondMoment = secondMomentBias;

            // Mise à jour des moments
            firstMomentBias = firstMoment.scale(parameter.getFirstMomentDecay())
                    .plus(gradient.scale(1 - parameter.getFirstMomentDecay()));

            secondMomentBias = secondMoment.scale(parameter.getSecondMomentDecay())
                    .plus(gradient.elementMult(gradient).scale(1 - parameter.getSecondMomentDecay()));

            firstMoment = firstMomentBias;
            secondMoment = secondMomentBias;
        }

        // Correction de biais
        double firstMomentDecayPower = Math.pow(parameter.getFirstMomentDecay(), timestep);
        double secondMomentDecayPower = Math.pow(parameter.getSecondMomentDecay(), timestep);

        SimpleMatrix firstMomentHat = firstMoment.divide(1 - firstMomentDecayPower);
        SimpleMatrix secondMomentHat = secondMoment.divide(1 - secondMomentDecayPower);

        // Ajouter epsilon de façon propre
        SimpleMatrix epsilonMatrix = new SimpleMatrix(secondMomentHat.getNumRows(), secondMomentHat.getNumCols());
        epsilonMatrix.fill(parameter.getDenominatorFactor());

        // Calcul final de la mise à jour
        SimpleMatrix stepUpdate = firstMomentHat.elementDiv(
                secondMomentHat.elementPower(0.5).plus(epsilonMatrix)
        ).scale(parameter.getStepSize());

        // === Gérer la soustraction ici ===
        return stepUpdate.negative(); // équivalent à scale(-1.0)
    }
}