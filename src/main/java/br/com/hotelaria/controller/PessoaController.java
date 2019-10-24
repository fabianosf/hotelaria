package br.com.hotelaria.controller;

import br.com.hotelaria.entity.Pessoa;
import br.com.hotelaria.exception.ResourceNotFoundException;
import br.com.hotelaria.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping("/pessoas")
    public List<Pessoa> getAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getEmployeeById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrado com este id :: " + id));
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping("/pessoas")
    public Pessoa createEmployee(@Valid @RequestBody Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> updateEnderco(@PathVariable(value = "id") Integer id,
                                                  @Valid @RequestBody Pessoa pessoaDetails) throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrado para este id :: " + id));
        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setRg(pessoaDetails.getRg());
        pessoa.setCpf(pessoaDetails.getCpf());

        final Pessoa updatedPessoa = pessoaService.update(pessoa);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/pessoas/{id}")
    public Map<String, Boolean> deletePessoa(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Pessoa pessoa = pessoaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrado para este id :: " + id));

        pessoaService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }












}
