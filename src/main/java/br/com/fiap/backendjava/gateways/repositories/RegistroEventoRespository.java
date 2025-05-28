package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.RegistroEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroEventoRespository extends JpaRepository<RegistroEvento, Integer> {
}
