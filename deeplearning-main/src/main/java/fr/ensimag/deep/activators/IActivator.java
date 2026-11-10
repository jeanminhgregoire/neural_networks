package fr.ensimag.deep.activators;
/** Definition of a generic activation function of a neuron */
public interface IActivator {
    /**
     * Activation function 
     * @param x the input
     * @return the result
     */
    public double phi(double x);
    
    /**
     * Derivative of the activation function
     * @param x the input
     * @return the result
     */
    public double phiPrime(double x);
}
