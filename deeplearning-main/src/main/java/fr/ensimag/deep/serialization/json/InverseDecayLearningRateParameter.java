
package fr.ensimag.deep.serialization.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("InverseDecayLearningRate")
public class InverseDecayLearningRateParameter extends GradientAdjustmentParameters{

    public InverseDecayLearningRateParameter()
    {
        super();
    }
    
    @JsonProperty("LearningRate")
    private Double learningRate;
    @JsonProperty("DecreaseFactor")
    private Double decreaseFactor;
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

    @JsonProperty("DecreaseFactor")
    public void setDecreaseFactor(Double decreaseFactor) {
        this.decreaseFactor = decreaseFactor;
    }

    @JsonProperty("DecreaseFactor")
    public Double getDecreaseFactorRate() {
        return decreaseFactor;
    }

    public InverseDecayLearningRateParameter withLearningRate(Double learningRate, Double decreaseFactor) {
        this.learningRate = learningRate;
        this.decreaseFactor = decreaseFactor;        
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

    public InverseDecayLearningRateParameter withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(InverseDecayLearningRateParameter.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("learningRate");
        sb.append('=');
        sb.append(((this.learningRate == null)?"<null>":this.learningRate));
        sb.append(',');
        sb.append("decreaseFactor");
        sb.append('=');
        sb.append(((this.decreaseFactor == null)?"<null>":this.decreaseFactor));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InverseDecayLearningRateParameter) == false) {
            return false;
        }
        InverseDecayLearningRateParameter rhs = ((InverseDecayLearningRateParameter) other);
        return ((((this.learningRate == rhs.learningRate)||((this.learningRate!= null)&&this.learningRate.equals(rhs.learningRate))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
