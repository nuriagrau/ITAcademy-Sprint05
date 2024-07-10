package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.repository;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.domain.FlorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlorRepository extends JpaRepository<FlorEntity, Integer> {
        Optional<FlorEntity> findByNomFlorIgnoreCase(String nomFlor);
}
