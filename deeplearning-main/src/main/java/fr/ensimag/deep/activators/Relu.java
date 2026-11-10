
package fr.ensimag.deep.activators;

public class Relu implements IActivator{

    public double phi(double x)
    {
        return (x > 0) ? x : 0;
    }

    @Override
    public double phiPrime(double x) {
        return (x > 0) ? 1 : 0;
    }
}
