package com.example.contato.controller;

import com.example.contato.dto.ContatoDto;
import com.example.contato.form.ContatoForm;
import com.example.contato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<ContatoDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Column(unique = true)
    public ResponseEntity<ContatoDto> create(@RequestBody @Valid ContatoForm form){
        return ResponseEntity.ok(service.create(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoDto> update(@PathVariable Long id, @RequestBody @Valid ContatoForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
