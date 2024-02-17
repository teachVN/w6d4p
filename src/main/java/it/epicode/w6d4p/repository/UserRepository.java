package it.epicode.w6d4p.repository;

import it.epicode.w6d4p.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findUserByEmail(String email);
}
