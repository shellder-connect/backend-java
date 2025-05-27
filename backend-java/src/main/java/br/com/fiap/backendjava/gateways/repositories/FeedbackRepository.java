package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
