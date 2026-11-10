
package fr.ensimag.deep.activators;

public class Identity implements IActivator{

    public double phi(double x)
    {
        return x;
    }

    @Override
    public double phiPrime(double x) {
        return 1;
    }
}
