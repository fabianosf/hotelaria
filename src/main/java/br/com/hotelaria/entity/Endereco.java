package br.com.hotelaria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;
    private String bairro;
    private String cidade;
    private Integer numero;
    private String cep;
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;

}
