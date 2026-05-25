package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade Curso
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Curso extends PersistenceEntity implements Serializable {

    private static final long serialVersionUID = 1L;    
    
    private String nome;
    private String descricao;
    private int quantidade;
    private String instituicao;

   
}