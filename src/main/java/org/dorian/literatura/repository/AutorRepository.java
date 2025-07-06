package org.dorian.literatura.repository;

import org.dorian.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThan(Integer añoNacimiento, Integer añoFallecimiento);
    List<Autor> findByFechaNacimientoLessThanEqualAndFechaFallecimientoIsNull(Integer año);
}
