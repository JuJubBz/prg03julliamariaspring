package br.com.ifba.infrastructure.entity;

// Importações do JPA (Jakarta Persistence)
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Classe base para todas as entidades do sistema.
 * 
 * Objetivo:
 * Evitar repetição de código (principalmente o ID) em várias classes.
 * 
 * Essa classe NÃO será uma tabela no banco de dados.
 * Ela apenas fornece atributos comuns para as entidades que herdarem dela.
 */
@MappedSuperclass // Indica que essa classe NÃO vira tabela, mas será herdada
public abstract class PersistenceEntity { // abstract = não pode ser instanciada

    /**
     * Identificador único da entidade (chave primária).
     */
    @Id // Define como chave primária no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Define que o valor será gerado automaticamente pelo banco (auto incremento)
    private Long id;

    /**
     * Retorna o ID da entidade.
     * 
     * @return id único
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da entidade.
     * 
     * Normalmente não é necessário usar esse método,
     * pois o próprio banco gera o ID automaticamente.
     * 
     * @param id identificador da entidade
     */
    public void setId(Long id) {
        this.id = id;
    }
}