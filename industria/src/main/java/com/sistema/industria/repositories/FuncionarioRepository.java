package com.sistema.industria.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.industria.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByFuncao(String funcao);
    
    List<Funcionario> findBySalarioGreaterThanEqual(BigDecimal salario);
    
    @Query("SELECT f FROM Funcionario f WHERE MONTH(f.dataNascimento) = :mes")
    List<Funcionario> findAniversariantesDoMes(Integer mes);
    
    @Query("SELECT f FROM Funcionario f WHERE f.salario > :salario")
    List<Funcionario> findBySalarioGreaterThan(BigDecimal salario);
}