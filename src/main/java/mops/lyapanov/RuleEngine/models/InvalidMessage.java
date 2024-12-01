package mops.lyapanov.RuleEngine.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invalid_messages")
public class InvalidMessage extends Message {

    private String alert;

    @JsonCreator
    public InvalidMessage(@JsonProperty("device") Device device, @JsonProperty("message") int message, @JsonProperty("alert") String alert) {
        super(device, message);
        this.alert = alert;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
