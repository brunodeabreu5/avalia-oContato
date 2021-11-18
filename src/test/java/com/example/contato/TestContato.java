package com.example.contato;

import com.example.contato.form.ContatoForm;
import com.example.contato.models.Contato;
import com.example.contato.repositories.ContatoRepositories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContato {

    @Autowired
    private ContatoRepositories repositories;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

   /* @Test
    public void saveCantatotest() {
        Contato contato = Contato.builder()
                .nome("Pedro")
                .email("www@hotmail")
                .telefone("55049923")
                .build();
        repositories.save(contato);
        Assertions.assertThat(contato.getId()).isGreaterThan(0);


    }

    @Test
    @Order(2)
    public void listContato() {
        Contato contato = repositories.findById(1L).get();
        Assertions.assertThat(contato.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getContatoTest() {
        Contato contato = repositories.findById(1L).get();

        Assertions.assertThat(contato.getId()).isEqualTo(1L);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateContatoTest() {
        Contato contato = repositories.findById(1l).get();
        contato.setEmail("teste@gmail.com");
        Contato contatoUpdadte = repositories.save(contato);

        Assertions.assertThat(contatoUpdadte.getEmail()).isEqualTo("teste@gmail.com");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteContatoTest() {
        Contato contato = repositories.findById(1L).get();

        repositories.delete(contato);

        Contato contato1 = null;

        Optional<Contato> optionalContato = repositories.findByEmail("teste@gmail.com");

        if (optionalContato.isPresent()) {
            contato1 = optionalContato.get();
        }
        Assertions.assertThat(contato1).isNull();
    }*/

    @Test
    @Order(6)
    public void verificarCadastro() throws Exception {
        ContatoForm contatoForm = ContatoForm.builder()
                .nome("teste")
                .email("joaquimpedro@uniube.br")
                .telefone("99405679")
                .build();

        mockMvc.perform(post("/contato").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contatoForm)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(7)
    public void verificarEmail() throws Exception {
        ContatoForm contatoForm = ContatoForm.builder()
                .nome("Pedro Luan da Conceição")
                .email("joaquimpedro@uniube.br")
                .telefone("26360971")
                .build();

        mockMvc.perform(post("/contato").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contatoForm)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    @Order(8)
    public void verificarTelefone() throws Exception{
        ContatoForm contatoForm = ContatoForm.builder()
                .nome("Bruno")
                .email("eduardo@email.com")
                .telefone("99405679")
                .build();

        mockMvc.perform(post("/contato").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contatoForm)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
