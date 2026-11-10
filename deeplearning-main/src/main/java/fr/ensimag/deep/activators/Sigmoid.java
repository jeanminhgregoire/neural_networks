
package fr.ensimag.deep.activators;

public class Sigmoid implements IActivator{

    public double phi(double x)
    {
        return 1.0 / (1 + Math.exp(-x));
    }

    @Override
    public double phiPrime(double x) {
        double tmp = phi(x); 
        return tmp * (1-tmp);
    }
}
