package fr.ensimag.deep.serialization.json;

import fr.ensimag.deep.trainer.initializer.GaussianInitializer;
import fr.ensimag.deep.trainer.initializer.HeInitializer;
import fr.ensimag.deep.trainer.initializer.INewtorkInitializer;
import fr.ensimag.deep.trainer.initializer.XavierInitializer;

public class InitializerFactory {
    public static INewtorkInitializer create(String initializer)
    {
        INewtorkInitializer result = null;

        if ("Gaussian".equals(initializer))
            result = new GaussianInitializer();
        else if ("Xavier".equals(initializer))
            result = new XavierInitializer();   
        else if ("He".equals(initializer))
            result = new HeInitializer();

        return result;
    }
}
