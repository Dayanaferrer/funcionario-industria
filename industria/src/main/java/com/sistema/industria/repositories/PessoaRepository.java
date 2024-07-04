package com.sistema.industria.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.industria.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNome(String nome);
    List<Pessoa> findByDataNascimento(LocalDate dataNascimento);
}
