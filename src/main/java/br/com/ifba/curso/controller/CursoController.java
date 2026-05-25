package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Controller responsável pela comunicação entre View e Service.
 */
@Controller
public class CursoController implements CursoIController {

    // Cria o logger da classe
    private static final Logger log =
            LoggerFactory.getLogger(CursoController.class);

    private final CursoIService cursoService;

    public CursoController(CursoIService cursoService) {
        this.cursoService = cursoService;
    }

    /**
     * Atualiza um curso.
     */
    @Override
    public Curso update(Curso curso) throws RuntimeException {

        // Log informando atualização
        log.info("Atualizando curso com ID: {}", curso.getId());

        return cursoService.update(curso);
    }

    /**
     * Remove um curso.
     */
    @Override
    public void delete(Curso curso) throws RuntimeException {

        // Log informando remoção
        log.warn("Removendo curso com ID: {}", curso.getId());

        cursoService.delete(curso);
    }

    /**
     * Lista todos os cursos.
     */
    @Override
    public List<Curso> findAll() throws RuntimeException {

        // Log da listagem
        log.info("Listando todos os cursos");

        return cursoService.findAll();
    }

    /**
     * Busca curso por ID.
     */
    @Override
    public Curso findById(Long id) throws RuntimeException {

        // Log da busca por ID
        log.info("Buscando curso pelo ID: {}", id);

        return cursoService.findById(id);
    }

    /**
     * Busca cursos pelo nome.
     */
    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {

        // Log da busca por nome
        log.info("Buscando cursos com nome: {}", nome);

        return cursoService.findByNome(nome);
    }

    /**
     * Salva um novo curso.
     */
    @Override
    public Curso save(Curso curso) throws RuntimeException {

        // Log do salvamento
        log.info("Salvando novo curso: {}", curso.getNome());

        return cursoService.save(curso);
    }
}