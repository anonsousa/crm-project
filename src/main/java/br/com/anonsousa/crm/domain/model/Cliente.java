package br.com.anonsousa.crm.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "table_cliente")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    private String empresa;
    private String cnpj;
    private String cargo;
    private LocalDate dataNascimento;
    private LocalDate dataRegistro;
    private String notas;

    @Enumerated(EnumType.STRING)
    private StatusCliente statusCliente;

    private String preferenciaContato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Interacoes> interacoes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public StatusCliente getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(StatusCliente statusCliente) {
        this.statusCliente = statusCliente;
    }

    public String getPreferenciaContato() {
        return preferenciaContato;
    }

    public void setPreferenciaContato(String preferenciaContato) {
        this.preferenciaContato = preferenciaContato;
    }

    public Set<Interacoes> getInteracoes() {
        return interacoes;
    }

    public void setInteracoes(Set<Interacoes> interacoes) {
        this.interacoes = interacoes;
    }
}
