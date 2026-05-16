package br.com.ifba.curso.dao;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;

import java.util.List;

/**
 * Interface específica para Curso
 * 
 * Estende o GenericDao, herdando:
 * - save
 * - update
 * - delete
 * - findAll
 * - findById
 */
public interface CursoIDao extends GenericDao<Curso> {

    /**
     * Busca cursos pelo nome
     * 
     * @param nome nome do curso
     * @return lista de cursos com esse nome
     */
    List<Curso> findByNome(String nome);
}