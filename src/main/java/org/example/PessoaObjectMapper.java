package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaObjectMapper {
    private String name;
    private int age;
    private int count;

    public PessoaObjectMapper() {
    }

    public PessoaObjectMapper(String name, int age, int count) {
        this.name = name;
        this.age = age;
        this.count = count;
    }

    @Override
    public String toString() {
        return "PessoaObjectMapper{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
