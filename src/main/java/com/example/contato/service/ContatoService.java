package com.example.contato.service;

import com.example.contato.dto.ContatoDto;
import com.example.contato.form.ContatoForm;
import com.example.contato.models.Contato;
import com.example.contato.repositories.ContatoRepositories;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
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

    public ContatoDto create(ContatoForm form) {
        if (repositories.findByEmail(form.getEmail()).isPresent()) {
        logger.error("Email existe{}",form.getEmail());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email cadastrado");

        }
        if (repositories.findByTelefone(form.getTelefone()).isPresent()){
            logger.error("telefone existe{}",form.getTelefone());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"telefone cadastrado");

        }
        return ContatoDto.from(repositories.save(Contato.from(form)));
    }

    public ContatoDto update(Long id, ContatoForm form){
        Contato contato =repositories.findById(id).orElseThrow(()->{
            logger.error("Id not{}",id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        return ContatoDto.from(repositories.save(contato));
    }

    public void delete(Long id) {
        Contato contato = repositories.findById(id).orElseThrow(() -> {
            logger.error("id não existe", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });

        repositories.delete(contato);
    }
}
