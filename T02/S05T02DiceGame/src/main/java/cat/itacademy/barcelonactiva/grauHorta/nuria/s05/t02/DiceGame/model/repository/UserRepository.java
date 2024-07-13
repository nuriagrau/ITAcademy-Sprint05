package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
