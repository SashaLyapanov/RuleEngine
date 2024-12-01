package mops.lyapanov.RuleEngine.serializators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mops.lyapanov.RuleEngine.models.Message;
import mops.lyapanov.RuleEngine.models.Message1queue;
import mops.lyapanov.RuleEngine.models.Message2queue;
import org.springframework.stereotype.Service;

@Service
public class MessageSerializer {

    private ObjectMapper mapper = new ObjectMapper();

    public Message deserialize(String jsonMessage) throws JsonProcessingException {
        return mapper.readValue(jsonMessage, Message.class);
    }

    public Message1queue deserializeMessage1Queue(String jsonMessage) throws JsonProcessingException {
        return mapper.readValue(jsonMessage, Message1queue.class);
    }

    public Message2queue deserializeMessage2Queue(String jsonMessage) throws JsonProcessingException {
        return mapper.readValue(jsonMessage, Message2queue.class);
    }


}
