package mops.lyapanov.RuleEngine.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages_from1_queue")
public class Message1queue extends Message {

    @JsonCreator
    public Message1queue(@JsonProperty("device") Device device, @JsonProperty("message") int message) {
        super(device, message);
    }

}
