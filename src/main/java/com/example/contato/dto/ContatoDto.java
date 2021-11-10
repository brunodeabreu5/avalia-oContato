package com.example.contato.dto;

import com.example.contato.models.Contato;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
public class ContatoDto {

    private int id;
    private String nome;
    private String email;
    private String telefone;

    public static ContatoDto from(Contato contato){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(contato, ContatoDto.class);
    }
}
