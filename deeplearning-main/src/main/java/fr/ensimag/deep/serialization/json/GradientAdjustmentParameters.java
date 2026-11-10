package fr.ensimag.deep.serialization.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(
        use = Id.NAME,
        include = As.PROPERTY,
        property = "Type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FixedLearningRateParameter.class, name = "FixedLearningRate"),
        @JsonSubTypes.Type(value = InverseDecayLearningRateParameter.class, name = "InverseDecayLearningRate"),
        @JsonSubTypes.Type(value = MomentumParameter.class, name = "Momentum"),
        @JsonSubTypes.Type(value = NesterovParameter.class, name = "Nesterov"),
        @JsonSubTypes.Type(value = AdamParameter.class, name = "Adam"),        
})

public class GradientAdjustmentParameters {

        public GradientAdjustmentParameters()
        {}

}
