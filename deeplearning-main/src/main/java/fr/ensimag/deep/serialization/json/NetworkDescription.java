
package fr.ensimag.deep.serialization.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "InputSize",
    "BatchSize",
    "Initializer",
    "SerializedLayers"
})
public class NetworkDescription {

    @JsonProperty("BatchSize")
    private Integer batchSize;
    @JsonProperty("InputSize")
    private Integer inputSize;
    @JsonProperty("Layers")
    private List<SerializedLayer> serializedLayers = new ArrayList<SerializedLayer>();
    @JsonProperty("Initializer")
    private String initializer;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("InputSize")
    public Integer getInputSize()
    {
        return this.inputSize;
    }

    @JsonProperty("InputSize")
    public void setInputSize(Integer inputSize)
    {
        this.inputSize = inputSize;
    }

    @JsonProperty("BatchSize")
    public Integer getBatchSize() {
        return batchSize;
    }

    @JsonProperty("BatchSize")
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    @JsonProperty("Initializer")
    public String getInitializer() {
        return initializer;
    }

    @JsonProperty("Initializer")
    public void setInitializer(String initializer) {
        this.initializer = initializer;
    }

    public NetworkDescription withBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    @JsonProperty("Layers")
    public List<SerializedLayer> getSerializedLayers() {
        return serializedLayers;
    }

    @JsonProperty("Layers")
    public void setSerializedLayers(List<SerializedLayer> serializedLayers) {
        this.serializedLayers = serializedLayers;
    }

    public NetworkDescription withSerializedLayers(List<SerializedLayer> serializedLayers) {
        this.serializedLayers = serializedLayers;
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

    public NetworkDescription withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NetworkDescription.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("BatchSize");
        sb.append('=');
        sb.append(((this.batchSize == null)?"<null>":this.batchSize));
        sb.append(',');
        sb.append("InputSize");
        sb.append('=');
        sb.append(((this.inputSize == null)?"<null>":this.inputSize));
        sb.append(',');        
        sb.append("Initializer");
        sb.append('=');
        sb.append(((this.initializer == null)?"<null>":this.initializer));
        sb.append(',');        
        sb.append("SerializedLayers");
        sb.append('=');
        sb.append(((this.serializedLayers == null)?"<null>":this.serializedLayers));
        sb.append(',');
        sb.append("AdditionalProperties");
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
        result = ((result* 31)+((this.serializedLayers == null)? 0 :this.serializedLayers.hashCode()));
        result = ((result* 31)+((this.initializer == null)? 0 :this.initializer.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.batchSize == null)? 0 :this.batchSize.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetworkDescription) == false) {
            return false;
        }
        NetworkDescription rhs = ((NetworkDescription) other);
        return ((((this.initializer == rhs.initializer)||((this.initializer!=null)&&this.initializer.equals(rhs.initializer))))&&((this.serializedLayers == rhs.serializedLayers)||((this.serializedLayers!= null)&&this.serializedLayers.equals(rhs.serializedLayers))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.batchSize == rhs.batchSize)||((this.batchSize!= null)&&this.batchSize.equals(rhs.batchSize)));
    }

}
