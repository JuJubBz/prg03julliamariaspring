package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * Interface que define as operações de negócio para a entidade Curso.
 * 
 * Estabelece um contrato que deve ser implementado pela classe de serviço,
 * garantindo padronização, desacoplamento e aplicação das regras de negócio.
 */
public interface CursoIService {

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

    /**
     * Remove um curso do sistema.
     * 
     * @param curso objeto a ser removido
     * @throws RuntimeException em caso de erro na operação
     */
    void delete(Curso curso) throws RuntimeException;
    
    List<Curso> findByNome(String nome) throws RuntimeException;
    
}