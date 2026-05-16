package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 * Entidade Curso
 */
@Entity
public class Curso extends PersistenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private int quantidade;
    private String instituicao;

    public Curso() {
    }

    // getters e setters (SEM id!)

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
}