package br.com.hotelaria.service;

import br.com.hotelaria.entity.Endereco;
import br.com.hotelaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco;
    }

    public Endereco save(Endereco endereco) {
        if (endereco.getId() != null) {
            return null;
        }
        Endereco savedEndereco = enderecoRepository.save(endereco);
        return savedEndereco;
    }

    public Endereco update(Endereco endereco) {
        Optional<Endereco> enderecoPersisted = findById(endereco.getId());
        if (enderecoPersisted == null) {
            return null;
        }

        Endereco updatedEndereco = enderecoRepository.save(endereco);
        return updatedEndereco;
    }

    public void delete(Integer id){
        enderecoRepository.deleteById(id);
    }











}
