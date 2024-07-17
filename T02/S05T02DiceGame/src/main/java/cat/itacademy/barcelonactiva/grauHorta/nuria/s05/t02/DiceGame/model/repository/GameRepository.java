package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    Optional<List<Game>> findByPlayerId(int playerId);

    void deleteAllByPlayerId(int playerId);
}
