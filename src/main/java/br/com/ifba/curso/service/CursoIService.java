package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * Interface que define as operações da camada de serviço para Curso.
 * 
 * Funciona como um contrato: a classe CursoService deve implementar esses métodos.
 */
public interface CursoIService {

    /**
     * Salva um novo curso no banco.
     */
    Curso save(Curso curso) throws RuntimeException;

    /**
     * Atualiza um curso existente.
     */
    Curso update(Curso curso) throws RuntimeException;

    /**
     * Lista todos os cursos cadastrados.
     */
    List<Curso> findAll() throws RuntimeException;

    /**
     * Busca um curso pelo ID.
     */
    Curso findById(Long id) throws RuntimeException;

    /**
     * Remove um curso do banco.
     */
    void delete(Curso curso) throws RuntimeException;
    
    /**
     * Busca cursos pelo nome.
     */
    List<Curso> findByNome(String nome) throws RuntimeException;
}