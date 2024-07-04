package com.sistema.industria.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;

@Entity
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;
    
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
    
    

}
