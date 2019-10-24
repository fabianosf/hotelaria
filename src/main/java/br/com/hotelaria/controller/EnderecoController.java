package br.com.hotelaria.controller;

import br.com.hotelaria.entity.Endereco;
import br.com.hotelaria.exception.ResourceNotFoundException;
import br.com.hotelaria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/enderecos")
    public List<Endereco> getAll() {
        return enderecoService.findAll();
    }

    @GetMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> getEmployeeById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Endereco endereco = enderecoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado com este id :: " + id));
        return ResponseEntity.ok().body(endereco);
    }

    @PostMapping("/enderecos")
    public Endereco createEmployee(@Valid @RequestBody Endereco endereco) {
        return enderecoService.save(endereco);
    }

    @PutMapping("/enderecos/{id}")
    public ResponseEntity<Endereco> updateEnderco(@PathVariable(value = "id") Integer id,
                                                  @Valid @RequestBody Endereco enderecoDetails) throws ResourceNotFoundException {
        Endereco endereco = enderecoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado para este id :: " + id));
        endereco.setRua(enderecoDetails.getRua());
        endereco.setBairro(enderecoDetails.getBairro());
        endereco.setCidade(enderecoDetails.getCidade());
        endereco.setNumero(enderecoDetails.getNumero());
        endereco.setCep(enderecoDetails.getCep());
        final Endereco updatedEndereco = enderecoService.update(endereco);
        return ResponseEntity.ok(updatedEndereco);
    }

    @DeleteMapping("/enderecos/{id}")
    public Map<String, Boolean> deleteEndereco(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Endereco endereco = enderecoService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereco nao encontrado para este id :: " + id));

        enderecoService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
