package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericIDao;

import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Implementação do DAO de Curso
 * 
 * - herda métodos do GenericIDao
 * - implementa métodos específicos do CursoIDao
 */
@Repository
public class CursoDao extends GenericIDao<Curso> implements CursoIDao {

    /**
     * Implementação da busca por nome
     */
    @Override
    public List<Curso> findByNome(String nome) {

        /**
         * JPQL:
         * Trabalha com a entidade (Curso), não com tabela SQL
         */
        TypedQuery<Curso> query = entityManager.createQuery(
                "SELECT c FROM Curso c WHERE c.nome = :nome",
                Curso.class
        );

        /**
         * Define o valor do parâmetro
         */
        query.setParameter("nome", nome);

        /**
         * Executa a consulta
         */
        return query.getResultList();
    }
}