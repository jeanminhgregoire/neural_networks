package fr.ensimag.deep.activators;

public class LeakyRelu implements IActivator{

    public double phi(double x)
    {
        return (x > 0) ? x : x*0.01;
    }

    @Override
    public double phiPrime(double x) {
        return (x > 0) ? 1 : 0.01;
    }
}
