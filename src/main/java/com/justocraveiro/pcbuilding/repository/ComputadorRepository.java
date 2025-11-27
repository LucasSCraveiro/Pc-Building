package com.justocraveiro.pcbuilding.repository;
import com.justocraveiro.pcbuilding.model.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long> {
    List<Computador> findByCategoriaIgnoreCase(String categoria);
}
