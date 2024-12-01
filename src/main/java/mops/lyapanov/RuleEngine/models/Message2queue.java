package mops.lyapanov.RuleEngine.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages_from2_queue")
public class Message2queue extends Message{

    @JsonCreator
    public Message2queue(@JsonProperty("device") Device device, @JsonProperty("message") int message) {
        super(device, message);
    }
}
