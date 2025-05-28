package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
}
