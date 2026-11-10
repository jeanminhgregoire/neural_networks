
package fr.ensimag.deep.activators;

public class Tanh implements IActivator{

    public double phi(double x)
    {
        return 2.0 / (1.0 + Math.exp(-2.0 * x)) - 1.0;
    }

    @Override
    public double phiPrime(double x) {
        double tmp = phi(x); 
        return 1 - tmp * tmp;
    }
}
