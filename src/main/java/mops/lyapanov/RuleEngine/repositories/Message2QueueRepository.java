package mops.lyapanov.RuleEngine.repositories;

import mops.lyapanov.RuleEngine.models.Message2queue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Message2QueueRepository extends MongoRepository<Message2queue, String> {
}
