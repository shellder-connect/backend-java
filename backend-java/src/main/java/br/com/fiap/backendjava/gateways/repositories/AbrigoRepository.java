package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Integer> {
}
