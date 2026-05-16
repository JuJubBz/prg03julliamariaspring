package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * Interface que define o contrato da camada de Controller para a entidade Curso.
 * 
 * Segue o padrão de nomenclatura e responsabilidades:
 * - Métodos com verbos em inglês
 * - Sem lógica de negócio
 * - Apenas delegação para o Service
 */
public interface CursoIController {

    /**
     * Salva um novo curso.
     * 
     * @param curso objeto a ser persistido
     * @return curso salvo
     * @throws RuntimeException em caso de erro na operação
     */
    Curso save(Curso curso) throws RuntimeException;

    /**
     * Atualiza um curso existente.
     * 
     * @param curso objeto a ser atualizado
     * @return curso atualizado
     * @throws RuntimeException em caso de erro na operação
     */
    Curso update(Curso curso) throws RuntimeException;

    /**
     * Remove um curso.
     * 
     * @param curso objeto a ser removido
     * @throws RuntimeException em caso de erro na operação
     */
    void delete(Curso curso) throws RuntimeException;

    /**
     * Retorna todos os cursos cadastrados.
     * 
     * @return lista de cursos
     * @throws RuntimeException em caso de erro na operação
     */
    List<Curso> findAll() throws RuntimeException;

    /**
     * Busca um curso pelo ID.
     * 
     * @param id identificador do curso
     * @return curso encontrado
     * @throws RuntimeException em caso de erro na operação
     */
    Curso findById(Long id) throws RuntimeException;
    
    List<Curso> findByNome(String nome) throws RuntimeException;
    
}