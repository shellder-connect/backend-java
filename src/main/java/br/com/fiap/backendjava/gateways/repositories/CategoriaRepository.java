package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
