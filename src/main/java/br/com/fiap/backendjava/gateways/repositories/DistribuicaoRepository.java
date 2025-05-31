package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.Distribuicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistribuicaoRepository extends JpaRepository<Distribuicao, Integer> {
}
