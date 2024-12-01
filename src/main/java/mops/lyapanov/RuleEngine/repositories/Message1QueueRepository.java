package mops.lyapanov.RuleEngine.repositories;

import mops.lyapanov.RuleEngine.models.Message1queue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Message1QueueRepository extends MongoRepository<Message1queue, String> {
}
