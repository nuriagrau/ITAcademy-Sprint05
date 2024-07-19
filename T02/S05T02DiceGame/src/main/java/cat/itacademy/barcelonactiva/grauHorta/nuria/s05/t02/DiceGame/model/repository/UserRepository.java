package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
