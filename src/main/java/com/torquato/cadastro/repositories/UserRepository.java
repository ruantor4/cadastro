package com.torquato.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.torquato.cadastro.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
