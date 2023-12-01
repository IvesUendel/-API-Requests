package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Universidade {
    private String nome;
    private String url;

    public Universidade(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Universidade{" +
                "nome='" + getNome() + '\'' +
                ", url='" + getUrl() + '\'' +
                '}';
    }
}
