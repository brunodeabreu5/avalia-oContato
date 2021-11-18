package com.example.contato.models;


import com.example.contato.form.ContatoForm;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telefone;

    public static Contato from(ContatoForm form){
        ModelMapper modelMapper =new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(form, Contato.class);
    }
}
