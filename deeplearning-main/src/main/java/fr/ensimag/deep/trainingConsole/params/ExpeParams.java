
package fr.ensimag.deep.trainingConsole.params;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "network description",
    "training data",
    "validation data",
    "epochs",
    "trained network",
    "cost function",
    "initialize",
    "learning log file",
    "validation steps",
    "final validation",
    "activation file",
    "gnuplot"
})
@Generated("jsonschema2pojo")
public class ExpeParams {

    @JsonProperty("network description")
    private String networkDescription;
    @JsonProperty("training data")
    private String trainingData;
    @JsonProperty("validation data")
    private String validationData;
    @JsonProperty("epochs")
    private Integer epochs;
    @JsonProperty("trained network")
    private String trainedNetwork;
    @JsonProperty("cost function")
    private String costFunction;
    @JsonProperty("initialize")
    private Boolean initialize;
    @JsonProperty("learning log file")
    private String learningLogFile;
    @JsonProperty("validation steps")
    private Integer validationSteps;
    @JsonProperty("final validation")
    private String finalValidation;
    @JsonProperty("activation file")
    private String activationFile;
    @JsonProperty("gnuplot")
    private Boolean gnuplot;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("network description")
    public String getNetworkDescription() {
        return networkDescription;
    }

    @JsonProperty("network description")
    public void setNetworkDescription(String networkDescription) {
        this.networkDescription = networkDescription;
    }

    public ExpeParams withNetworkDescription(String networkDescription) {
        this.networkDescription = networkDescription;
        return this;
    }

    @JsonProperty("training data")
    public String getTrainingData() {
        return trainingData;
    }

    @JsonProperty("training data")
    public void setTrainingData(String trainingData) {
        this.trainingData = trainingData;
    }

    public ExpeParams withTrainingData(String trainingData) {
        this.trainingData = trainingData;
        return this;
    }

    @JsonProperty("validation data")
    public String getValidationData() {
        return validationData;
    }

    @JsonProperty("validation data")
    public void setValidationData(String validationData) {
        this.validationData = validationData;
    }

    public ExpeParams withValidationData(String validationData) {
        this.validationData = validationData;
        return this;
    }

    @JsonProperty("epochs")
    public Integer getEpochs() {
        return epochs;
    }

    @JsonProperty("epochs")
    public void setEpochs(Integer epochs) {
        this.epochs = epochs;
    }

    public ExpeParams withEpochs(Integer epochs) {
        this.epochs = epochs;
        return this;
    }

    @JsonProperty("trained network")
    public String getTrainedNetwork() {
        return trainedNetwork;
    }

    @JsonProperty("trained network")
    public void setTrainedNetwork(String trainedNetwork) {
        this.trainedNetwork = trainedNetwork;
    }

    public ExpeParams withTrainedNetwork(String trainedNetwork) {
        this.trainedNetwork = trainedNetwork;
        return this;
    }

    @JsonProperty("cost function")
    public String getCostFunction() {
        return costFunction;
    }

    @JsonProperty("cost function")
    public void setCostFunction(String costFunction) {
        this.costFunction = costFunction;
    }

    public ExpeParams withCostFunction(String costFunction) {
        this.costFunction = costFunction;
        return this;
    }

    @JsonProperty("initialize")
    public Boolean getInitialize() {
        return initialize;
    }

    @JsonProperty("initialize")
    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    public ExpeParams withInitialize(Boolean initialize) {
        this.initialize = initialize;
        return this;
    }

    @JsonProperty("learning log file")
    public String getLearningLogFile() {
        return learningLogFile;
    }

    @JsonProperty("learning log file")
    public void setLearningLogFile(String learningLogFile) {
        this.learningLogFile = learningLogFile;
    }

    public ExpeParams withLearningLogFile(String learningLogFile) {
        this.learningLogFile = learningLogFile;
        return this;
    }

    @JsonProperty("validation steps")
    public Integer getValidationSteps() {
        return validationSteps;
    }

    @JsonProperty("validation steps")
    public void setValidationSteps(Integer validationSteps) {
        this.validationSteps = validationSteps;
    }

    public ExpeParams withValidationSteps(Integer validationSteps) {
        this.validationSteps = validationSteps;
        return this;
    }

    @JsonProperty("final validation")
    public String getFinalValidation() {
        return finalValidation;
    }

    @JsonProperty("final validation")
    public void setFinalValidation(String finalValidation) {
        this.finalValidation = finalValidation;
    }

    public ExpeParams withFinalValidation(String finalValidation) {
        this.finalValidation = finalValidation;
        return this;
    }

    @JsonProperty("activation file")
    public String getActivationFile() {
        return activationFile;
    }

    @JsonProperty("activation file")
    public void setActivationFile(String activationFile) {
        this.activationFile = activationFile;
    }

    public ExpeParams withActivationFile(String activationFile) {
        this.activationFile = activationFile;
        return this;
    }

    @JsonProperty("gnuplot")
    public Boolean getGnuplot() {
        return gnuplot;
    }

    @JsonProperty("gnuplot")
    public void setGnuplot(Boolean gnuplot) {
        this.gnuplot = gnuplot;
    }

    public ExpeParams withGnuplot(Boolean gnuplot) {
        this.gnuplot = gnuplot;
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

    public ExpeParams withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ExpeParams.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("networkDescription");
        sb.append('=');
        sb.append(((this.networkDescription == null)?"<null>":this.networkDescription));
        sb.append(',');
        sb.append("trainingData");
        sb.append('=');
        sb.append(((this.trainingData == null)?"<null>":this.trainingData));
        sb.append(',');
        sb.append("validationData");
        sb.append('=');
        sb.append(((this.validationData == null)?"<null>":this.validationData));
        sb.append(',');
        sb.append("epochs");
        sb.append('=');
        sb.append(((this.epochs == null)?"<null>":this.epochs));
        sb.append(',');
        sb.append("trainedNetwork");
        sb.append('=');
        sb.append(((this.trainedNetwork == null)?"<null>":this.trainedNetwork));
        sb.append(',');
        sb.append("costFunction");
        sb.append('=');
        sb.append(((this.costFunction == null)?"<null>":this.costFunction));
        sb.append(',');
        sb.append("initialize");
        sb.append('=');
        sb.append(((this.initialize == null)?"<null>":this.initialize));
        sb.append(',');
        sb.append("learningLogFile");
        sb.append('=');
        sb.append(((this.learningLogFile == null)?"<null>":this.learningLogFile));
        sb.append(',');
        sb.append("validationSteps");
        sb.append('=');
        sb.append(((this.validationSteps == null)?"<null>":this.validationSteps));
        sb.append(',');
        sb.append("finalValidation");
        sb.append('=');
        sb.append(((this.finalValidation == null)?"<null>":this.finalValidation));
        sb.append(',');
        sb.append("activationFile");
        sb.append('=');
        sb.append(((this.activationFile == null)?"<null>":this.activationFile));
        sb.append(',');
        sb.append("gnuplot");
        sb.append('=');
        sb.append(((this.gnuplot == null)?"<null>":this.gnuplot));
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
        result = ((result* 31)+((this.networkDescription == null)? 0 :this.networkDescription.hashCode()));
        result = ((result* 31)+((this.costFunction == null)? 0 :this.costFunction.hashCode()));
        result = ((result* 31)+((this.learningLogFile == null)? 0 :this.learningLogFile.hashCode()));
        result = ((result* 31)+((this.trainingData == null)? 0 :this.trainingData.hashCode()));
        result = ((result* 31)+((this.validationSteps == null)? 0 :this.validationSteps.hashCode()));
        result = ((result* 31)+((this.activationFile == null)? 0 :this.activationFile.hashCode()));
        result = ((result* 31)+((this.validationData == null)? 0 :this.validationData.hashCode()));
        result = ((result* 31)+((this.gnuplot == null)? 0 :this.gnuplot.hashCode()));
        result = ((result* 31)+((this.trainedNetwork == null)? 0 :this.trainedNetwork.hashCode()));
        result = ((result* 31)+((this.finalValidation == null)? 0 :this.finalValidation.hashCode()));
        result = ((result* 31)+((this.initialize == null)? 0 :this.initialize.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.epochs == null)? 0 :this.epochs.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ExpeParams) == false) {
            return false;
        }
        ExpeParams rhs = ((ExpeParams) other);
        return ((((((((((((((this.networkDescription == rhs.networkDescription)||((this.networkDescription!= null)&&this.networkDescription.equals(rhs.networkDescription)))&&((this.costFunction == rhs.costFunction)||((this.costFunction!= null)&&this.costFunction.equals(rhs.costFunction))))&&((this.learningLogFile == rhs.learningLogFile)||((this.learningLogFile!= null)&&this.learningLogFile.equals(rhs.learningLogFile))))&&((this.trainingData == rhs.trainingData)||((this.trainingData!= null)&&this.trainingData.equals(rhs.trainingData))))&&((this.validationSteps == rhs.validationSteps)||((this.validationSteps!= null)&&this.validationSteps.equals(rhs.validationSteps))))&&((this.activationFile == rhs.activationFile)||((this.activationFile!= null)&&this.activationFile.equals(rhs.activationFile))))&&((this.validationData == rhs.validationData)||((this.validationData!= null)&&this.validationData.equals(rhs.validationData))))&&((this.gnuplot == rhs.gnuplot)||((this.gnuplot!= null)&&this.gnuplot.equals(rhs.gnuplot))))&&((this.trainedNetwork == rhs.trainedNetwork)||((this.trainedNetwork!= null)&&this.trainedNetwork.equals(rhs.trainedNetwork))))&&((this.finalValidation == rhs.finalValidation)||((this.finalValidation!= null)&&this.finalValidation.equals(rhs.finalValidation))))&&((this.initialize == rhs.initialize)||((this.initialize!= null)&&this.initialize.equals(rhs.initialize))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.epochs == rhs.epochs)||((this.epochs!= null)&&this.epochs.equals(rhs.epochs))));
    }

}
