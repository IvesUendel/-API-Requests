package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaJSON
{
    private String respostaJson;

    @Override
    public String toString() {
        return "RespostaJSON{" +
                "respostaJson='" + getRespostaJson() + '\'' +
                '}';
    }
}
