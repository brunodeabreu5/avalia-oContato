package com.example.contato.service;

import com.example.contato.dto.ContatoDto;
import com.example.contato.form.ContatoForm;
import com.example.contato.models.Contato;
import com.example.contato.repositories.ContatoRepositories;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepositories repositories;

    Logger logger = LoggerFactory.getLogger(ContatoService.class);

    public List<ContatoDto> findAll(){
        List<Contato> contatos = repositories.findAll();
        return contatos.stream().map(ContatoDto::from).collect(Collectors.toList());
    }

    public ContatoDto create(ContatoForm obj) {
        Contato from = Contato.from(obj);
        return ContatoDto.from(repositories.save(from));
    }

    public ContatoDto update(Long id, ContatoForm form){
        ModelMapper modelMapper = new ModelMapper();
        Contato contato =repositories.findById(id).orElseThrow(()->{
            logger.error("Id not{}",id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        modelMapper.map(form,contato);
        return ContatoDto.from(repositories.save(contato));
    }

    public void delete(Long id) {
        Contato contato = repositories.findById(id).orElseThrow(() -> {
            logger.error("id não existe", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        repositories.delete(contato);
    }

    public ContatoDto findById(Long id) {
        return ContatoDto.from(repositories.findById(id).orElseThrow(()->{
            logger.error("id não existe", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }));
    }
}
