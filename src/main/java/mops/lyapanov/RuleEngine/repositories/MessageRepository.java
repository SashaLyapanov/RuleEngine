package mops.lyapanov.RuleEngine.repositories;

import mops.lyapanov.RuleEngine.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

}
