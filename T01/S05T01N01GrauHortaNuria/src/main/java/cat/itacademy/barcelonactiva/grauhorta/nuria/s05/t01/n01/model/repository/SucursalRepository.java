package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.repository;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {

    Optional<Sucursal> findBynomSucursalIgnoreCase(String nomSucursal);
}
