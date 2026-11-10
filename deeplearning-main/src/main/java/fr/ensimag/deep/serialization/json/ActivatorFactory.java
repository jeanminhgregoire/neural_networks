package fr.ensimag.deep.serialization.json;

import fr.ensimag.deep.activators.IActivator;
import fr.ensimag.deep.activators.Identity;
import fr.ensimag.deep.activators.LeakyRelu;
import fr.ensimag.deep.activators.Relu;
import fr.ensimag.deep.activators.Sigmoid;
import fr.ensimag.deep.activators.Tanh;

public class ActivatorFactory {
    public static IActivator create(String activatorName)
    {
        IActivator activator = null;
        if ("LeakyRelu".equals(activatorName))
            activator = new LeakyRelu();
        else if ("Relu".equals(activatorName))
            activator = new Relu();
        else if ("Sigmoid".equals(activatorName))
            activator = new Sigmoid();
        else if ("Tanh".equals(activatorName))
            activator = new Tanh();
        else if ("Identity".equals(activatorName))
            activator = new Identity();

        return activator;
    }
}
