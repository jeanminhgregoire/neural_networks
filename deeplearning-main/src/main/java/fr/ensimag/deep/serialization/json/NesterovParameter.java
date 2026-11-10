
package fr.ensimag.deep.serialization.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Nesterov")
public class NesterovParameter extends GradientAdjustmentParameters{

    public NesterovParameter()
    {
        super();
    }
    
    @JsonProperty("LearningRate")
    private Double learningRate;
    @JsonProperty("Momentum")
    private Double momentum;    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("LearningRate")
    public Double getLearningRate() {
        return learningRate;
    }

    @JsonProperty("LearningRate")
    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    @JsonProperty("Momentum")
    public Double getMomentum() {
        return momentum;
    }

    @JsonProperty("Momentum")
    public void setMomentum(Double momentum) {
        this.momentum = momentum;
    }

    public NesterovParameter withLearningRate(Double learningRate) {
        this.learningRate = learningRate;
        return this;
    }

    public NesterovParameter withMomentum(Double momentum) {
        this.momentum = momentum;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public NesterovParameter withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FixedLearningRateParameter.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("LearningRate");
        sb.append('=');
        sb.append(((this.learningRate == null)?"<null>":this.learningRate));
        sb.append(',');
        sb.append("Momentum");
        sb.append('=');
        sb.append(((this.momentum == null)?"<null>":this.momentum));
        sb.append(',');        
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.learningRate == null)? 0 :this.learningRate.hashCode()));
        result = ((result* 31)+((this.momentum == null)? 0 :this.momentum.hashCode()));        
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NesterovParameter) == false) {
            return false;
        }
        NesterovParameter rhs = ((NesterovParameter) other);
        return ((((this.momentum == rhs.momentum)||((this.momentum!= null)&&this.momentum.equals(rhs.momentum))))&&(((this.learningRate == rhs.learningRate)||((this.learningRate!= null)&&this.learningRate.equals(rhs.learningRate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
