package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + getNome() + '\'' +
                ", idade=" + getIdade() +
                '}';
    }
}
