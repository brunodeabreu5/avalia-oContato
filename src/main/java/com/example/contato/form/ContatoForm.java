package com.example.contato.form;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContatoForm {
    private String nome;
    private String email;
    private String telefone;
}
