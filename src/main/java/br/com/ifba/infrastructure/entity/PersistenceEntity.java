package br.com.ifba.infrastructure.entity;

// Importações do JPA (para mapear a classe no banco)
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

// Importações do Lombok (para evitar código repetido)
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Classe base para todas as entidades do sistema.
 * 
 * Serve para evitar repetir código (principalmente o ID)
 * em várias classes do projeto.
 * 
 * Essa classe NÃO vira uma tabela no banco,
 * ela só passa seus atributos para as classes filhas.
 */

// Indica que essa classe não é tabela, mas será herdada por outras entidades
@MappedSuperclass

// Lombok cria automaticamente os métodos GET (getId)
@Getter

// Lombok cria equals() e hashCode()
@EqualsAndHashCode

// Classe abstrata = não pode criar objeto direto dela
public abstract class PersistenceEntity {

    /**
     * ID único da entidade (chave primária no banco)
     */

    // Define como chave primária
    @Id

    // Define que o banco vai gerar o ID automaticamente (auto incremento)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    // Atributo que será herdado por todas as entidades
    private Long id;

}