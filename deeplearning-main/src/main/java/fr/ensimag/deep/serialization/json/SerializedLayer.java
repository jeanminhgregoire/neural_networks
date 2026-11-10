
package fr.ensimag.deep.serialization.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "Size",
    "ActivatorType",
    "GradientAdjustmentParameters",
    "Type"
})
public class SerializedLayer {

    @JsonProperty("Size")
    private Integer size;
    @JsonProperty("ActivatorType")
    private String activatorType;
    @JsonProperty("GradientAdjustmentParameters")
    private GradientAdjustmentParameters gradientAdjustmentParameters;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Bias")
    private List<Double> bias;
    @JsonProperty("Weights") 
    private List<List<Double>> weights = new ArrayList<List<Double>>();    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("Size")
    public void setSize(Integer size) {
        this.size = size;
    }

    public SerializedLayer withSize(Integer size) {
        this.size = size;
        return this;
    }

    public SerializedLayer withGradientAdjustmentParameters(FixedLearningRateParameter gradientAdjustmentParameters) {
        this.gradientAdjustmentParameters = gradientAdjustmentParameters;
        return this;
    }

    @JsonProperty("ActivatorType")
    public String getActivatorType() {
        return activatorType;
    }

    @JsonProperty("ActivatorType")
    public void setActivatorType(String activatorType) {
        this.activatorType = activatorType;
    }

    public SerializedLayer withActivatorType(String activatorType) {
        this.activatorType = activatorType;
        return this;
    }

    @JsonProperty("GradientAdjustmentParameters")
    public GradientAdjustmentParameters getGradientAdjustmentParameters() {
        return gradientAdjustmentParameters;
    }

    @JsonProperty("GradientAdjustmentParameters")
    public void setGradientAdjustmentParameters(GradientAdjustmentParameters gradientAdjustmentParameters) {
        this.gradientAdjustmentParameters = gradientAdjustmentParameters;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Bias")
    public List<Double> getBias() {
        return this.bias; 
    }

    @JsonProperty("Bias")
    public void setBias(List<Double> bias) {
        this.bias = bias;
    }

    @JsonProperty("Weights")
    public List<List<Double>> getWeights() {
        return weights;
    }

    @JsonProperty("Weights")
    public void setWeights(List<List<Double>> weights) {
        this.weights = weights;
    }    

    public SerializedLayer withType(String type) {
        this.type = type;
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

    public SerializedLayer withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SerializedLayer.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("activatorType");
        sb.append('=');
        sb.append(((this.activatorType == null)?"<null>":this.activatorType));
        sb.append(',');
        sb.append("GradientAdjustmentParameters");
        sb.append('=');
        sb.append(((this.gradientAdjustmentParameters == null)?"<null>":this.gradientAdjustmentParameters));
        sb.append(',');        
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("bias");
        sb.append('=');
        sb.append(((this.bias == null)?"<null>":this.bias));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append("weights");
        sb.append('=');
        sb.append(((this.weights == null)?"<null>":this.weights));
        sb.append(',');        
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
        result = ((result* 31)+((this.gradientAdjustmentParameters == null)? 0 :this.gradientAdjustmentParameters.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.bias == null)? 0 :this.bias.hashCode()));    
        result = ((result* 31)+((this.weights == null)? 0 :this.weights.hashCode()));            
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.activatorType == null)? 0 :this.activatorType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SerializedLayer) == false) {
            return false;
        }
        SerializedLayer rhs = ((SerializedLayer) other);
        return (((((this.gradientAdjustmentParameters == rhs.gradientAdjustmentParameters)||((this.gradientAdjustmentParameters!= null)&&this.gradientAdjustmentParameters.equals(rhs.gradientAdjustmentParameters)))&&((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.weights == rhs.weights)||((this.weights!= null)&&this.weights.equals(rhs.weights))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size)))&&((this.bias == rhs.bias)||((this.bias!= null)&&this.bias.equals(rhs.bias))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.activatorType == rhs.activatorType)||((this.activatorType!= null)&&this.activatorType.equals(rhs.activatorType)))));
    }

}
