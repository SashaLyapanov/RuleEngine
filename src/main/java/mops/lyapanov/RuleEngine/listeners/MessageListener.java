package mops.lyapanov.RuleEngine.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import mops.lyapanov.RuleEngine.models.InvalidMessage;
import mops.lyapanov.RuleEngine.models.Message;
import mops.lyapanov.RuleEngine.models.Message1queue;
import mops.lyapanov.RuleEngine.models.Message2queue;
import mops.lyapanov.RuleEngine.repositories.InvalidMessageRepository;
import mops.lyapanov.RuleEngine.repositories.Message1QueueRepository;
import mops.lyapanov.RuleEngine.repositories.Message2QueueRepository;
import mops.lyapanov.RuleEngine.repositories.MessageRepository;
import mops.lyapanov.RuleEngine.serializators.MessageSerializer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class MessageListener {

    private final MessageSerializer messageSerializer;
    private final MessageRepository messageRepository;
    private final Message1QueueRepository message1QueueRepository;
    private final Message2QueueRepository message2QueueRepository;
    private final InvalidMessageRepository invalidMessageRepository;

    private List<Message> messages = new ArrayList<>();
    private final int MAX_PACKAGE_SIZE = 2;
    private ConcurrentLinkedQueue<List<Message>> packagesQueue = new ConcurrentLinkedQueue<>();

    @Autowired
    public MessageListener(MessageSerializer messageSerializer, MessageRepository messageRepository, Message1QueueRepository message1QueueRepository,
                           Message2QueueRepository message2QueueRepository, InvalidMessageRepository invalidMessageRepository) {
        this.messageSerializer = messageSerializer;
        this.messageRepository = messageRepository;
        this.message1QueueRepository = message1QueueRepository;
        this.message2QueueRepository = message2QueueRepository;
        this.invalidMessageRepository = invalidMessageRepository;
    }

    @RabbitListener(queues = "iot_queue1")
    public void listenQueue1(String jsonMessage) throws JsonProcessingException {
        Message1queue message = messageSerializer.deserializeMessage1Queue(jsonMessage);
        if (message.getMessage() > 0) {
            System.out.println("Message from Q1 that > 0: " + message.getMessage());
            message1QueueRepository.save(message);
        } else {
            System.out.println("Message from Q1 that < 0: " + message.getMessage());
            InvalidMessage invalidMessage = new InvalidMessage(message.getDevice(), message.getMessage(), "Сообщение невалидно! Значение меньше 0!");
            invalidMessageRepository.save(invalidMessage);
        }
    }

    @RabbitListener(queues = "iot_queue2")
    public void listenQueue2(String jsonMessage) throws JsonProcessingException {
        Message message = messageSerializer.deserialize(jsonMessage);
        collectingPackageData(message);
        System.out.println("Message from Q2: " + message.getMessage());
    }

    public void collectingPackageData(Message message) {
        messages.add(message);
        if (messages.size() == 100) {
            if (packagesQueue.size() < MAX_PACKAGE_SIZE) {
                List<Message> messagesForQueue = new ArrayList<>(messages);
                packagesQueue.add(messagesForQueue);
            }
            messages.clear();
        }
        if (packagesQueue.size() == MAX_PACKAGE_SIZE) {
            checkingLongRule(packagesQueue);
            packagesQueue.clear();
        }
    }

    public void checkingLongRule(ConcurrentLinkedQueue<List<Message>> packagesQueue) {
        List<Message> checkingMessages = new ArrayList<>();
        List<Message2queue> resultMessages = new ArrayList<>();
        for (List<Message> messages : packagesQueue) {
            Message messageFrom42Device = messages.get(42);
            if (messageFrom42Device.getMessage() < 0) {
                break;
            } else {
                checkingMessages.add(messageFrom42Device);
            }
        }
        if (checkingMessages.size() == MAX_PACKAGE_SIZE) {
            for (Message message : checkingMessages) {
                Message2queue result = new Message2queue(message.getDevice(), message.getMessage());
                resultMessages.add(result);
            }
            message2QueueRepository.saveAll(resultMessages);
            System.out.println("Повезло и фортануло!!!");
        } else {
            System.out.println("Не повезло, не фортануло!!!");
        }
    }


}
