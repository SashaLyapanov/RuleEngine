package mops.lyapanov.RuleEngine.repositories;

import mops.lyapanov.RuleEngine.models.InvalidMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidMessageRepository extends MongoRepository<InvalidMessage, String> {
}
