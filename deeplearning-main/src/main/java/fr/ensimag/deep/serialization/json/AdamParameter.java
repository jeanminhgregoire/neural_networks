
package fr.ensimag.deep.serialization.json;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Adam")
public class AdamParameter extends GradientAdjustmentParameters{

    public AdamParameter()
    {
        super();
    }
    
    @JsonProperty("StepSize")
    private Double stepSize;
    @JsonProperty("FirstMomentDecay")
    private Double firstMomentDecay;    
    @JsonProperty("SecondMomentDecay")
    private Double secondMomentDecay;
    @JsonProperty("DenominatorFactor")
    private Double denominatorFactor;    
    @JsonIgnore    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("StepSize")
    public Double getStepSize() {
        return stepSize;
    }

    @JsonProperty("StepSize")
    public void setStepSize(Double stepSize) {
        this.stepSize = stepSize;
    }

    @JsonProperty("FirstMomentDecay")
    public Double getFirstMomentDecay() {
        return firstMomentDecay;
    }

    @JsonProperty("FirstMomentDecay")
    public void setFirstMomentDecay(Double firstMomentDecay) {
        this.firstMomentDecay = firstMomentDecay;
    }

    public AdamParameter withStepSize(Double stepSize) {
        this.stepSize = stepSize;
        return this;
    }

    public AdamParameter withFirstMomentDecay(Double firstMomentDecay) {
        this.firstMomentDecay = firstMomentDecay;
        return this;
    }


    @JsonProperty("SecondMomentDecay")
    public Double getSecondMomentDecay() {
        return secondMomentDecay;
    }

    @JsonProperty("SecondMomentDecay")
    public void setSecondMomentDecay(Double secondMomentDecay) {
        this.secondMomentDecay = secondMomentDecay;
    }

    @JsonProperty("DenominatorFactor")
    public Double getDenominatorFactor() {
        return denominatorFactor;
    }

    @JsonProperty("DenominatorFactor")
    public void setDenominatorFactor(Double denominatorFactor) {
        this.denominatorFactor = denominatorFactor;
    }

    public AdamParameter withSecondMomentDecay(Double secondMomentDecay) {
        this.secondMomentDecay = secondMomentDecay;
        return this;
    }

    public AdamParameter withDenominatorFactor(Double denominatorFactor) {
        this.denominatorFactor = denominatorFactor;
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

    public AdamParameter withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FixedLearningRateParameter.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("StepSize");
        sb.append('=');
        sb.append(((this.stepSize == null)?"<null>":this.stepSize));
        sb.append(',');
        sb.append("FirstMomentDecay");
        sb.append('=');
        sb.append(((this.firstMomentDecay == null)?"<null>":this.firstMomentDecay));
        sb.append(',');     
        sb.append("SecondMomentDecay");
        sb.append('=');
        sb.append(((this.secondMomentDecay == null)?"<null>":this.secondMomentDecay));
        sb.append(',');              
        sb.append("DenominatorFactor");
        sb.append('=');
        sb.append(((this.denominatorFactor == null)?"<null>":this.denominatorFactor));
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
        result = ((result* 31)+((this.stepSize == null)? 0 :this.stepSize.hashCode()));
        result = ((result* 31)+((this.firstMomentDecay == null)? 0 :this.firstMomentDecay.hashCode()));        
        result = ((result* 31)+((this.denominatorFactor == null)? 0 :this.denominatorFactor.hashCode()));
        result = ((result* 31)+((this.secondMomentDecay == null)? 0 :this.secondMomentDecay.hashCode()));        
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AdamParameter) == false) {
            return false;
        }
        AdamParameter rhs = ((AdamParameter) other);
        return ((((this.firstMomentDecay == rhs.firstMomentDecay)||((this.firstMomentDecay!= null)&&this.firstMomentDecay.equals(rhs.firstMomentDecay))))&&(((this.stepSize == rhs.stepSize)||((this.stepSize!= null)&&this.stepSize.equals(rhs.stepSize))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&(((this.secondMomentDecay == rhs.secondMomentDecay)||((this.secondMomentDecay!= null)&&this.secondMomentDecay.equals(rhs.secondMomentDecay))))&&(((this.denominatorFactor == rhs.denominatorFactor)||((this.denominatorFactor!= null)&&this.denominatorFactor.equals(rhs.denominatorFactor)))));
    }

}
