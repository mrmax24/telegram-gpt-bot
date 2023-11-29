package app.telegramgptbot.adminpanel.repository;

import app.telegramgptbot.adminpanel.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
