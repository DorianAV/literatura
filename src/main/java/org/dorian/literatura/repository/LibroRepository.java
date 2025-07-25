package org.dorian.literatura.repository;

import org.dorian.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByIdAPI(Integer idAPI);


}
