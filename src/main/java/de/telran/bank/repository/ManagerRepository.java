package de.telran.bank.repository;

import de.telran.bank.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Manager findByFirstName(String firstName);
}
