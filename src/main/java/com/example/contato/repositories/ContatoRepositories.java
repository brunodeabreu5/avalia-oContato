package com.example.contato.repositories;

import com.example.contato.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepositories extends JpaRepository<Contato, Long> {

    Optional<Contato> findByEmail(String email);
    Optional<Contato> findByTelefone(String telefone);
}
