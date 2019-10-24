package br.com.hotelaria.service;

import br.com.hotelaria.entity.Endereco;
import br.com.hotelaria.entity.Pessoa;
import br.com.hotelaria.repository.EnderecoRepository;
import br.com.hotelaria.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Integer id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa;
    }

    public Pessoa save(Pessoa pessoa) {
        if (pessoa.getId() != null) {
            return null;
        }
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return savedPessoa;
    }

    public Pessoa update(Pessoa pessoa) {
        Optional<Pessoa> pessoaPersisted = findById(pessoa.getId());
        if (pessoaPersisted == null) {
            return null;
        }

        Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return updatedPessoa;
    }

    public void delete(Integer id){
        pessoaRepository.deleteById(id);
    }











}
