package br.com.ifba.infrastructure.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Implementação genérica do DAO usando JPA.
 * 
 * <Entity> -> qualquer classe que herde de PersistenceEntity 
 * @param <Entity>
 */
@Repository
@SuppressWarnings("unchecked")
public class GenericIDao<Entity extends PersistenceEntity>
        implements GenericDao<Entity> {

    /**
     * Responsável por gerenciar as operações com o banco
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * SALVAR entidade no banco
     * @param entity
     * @return 
     */
    @Override
    public Entity save(Entity entity) {
    entityManager.persist(entity);
    return entity;
    }

    /**
     * ATUALIZAR entidade existente
     * @param entity
     * @return 
     */
    @Override
    public Entity update(Entity entity) {
    return entityManager.merge(entity);
}

    /**
     * DELETAR entidade
     * @param entity
     */
    @Override
    public void delete(Entity entity) {
    entity = findById(entity.getId());
    entityManager.remove(entity);
    }

    /**
     * LISTAR todas as entidades (Curso, Turma, etc)
     * @return 
     */
    @Override
    public List<Entity> findAll() {

        /**
         * Criamos uma query dinâmica baseada no tipo da entidade
         * Ex: "from Curso" ou "from Turma"
         */
        TypedQuery<Entity> query = entityManager.createQuery(
                "from " + getTypeClass().getName(),
                getTypeClass()
        );

        return query.getResultList();
    }

    /**
     * BUSCAR entidade pelo ID
     * @param id
     * @return 
     */
    @Override
    public Entity findById(Long id) {

        // Agora funciona corretamente porque o tipo está certo
        return entityManager.find(getTypeClass(), id);
    }

    /**
     * Descobre automaticamente o tipo da entidade (Curso, Turma, etc)
     * 
     * Parte mais importante do Generic DAO
     * Usa reflexão para pegar o tipo em tempo de execução
     */
    private Class<Entity> getTypeClass() {

        return (Class<Entity>) ((ParameterizedType)
                this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
    
    
    
}