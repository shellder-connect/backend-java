package br.com.fiap.backendjava.gateways.repositories;

import br.com.fiap.backendjava.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findUserByUsername(String username);
}
