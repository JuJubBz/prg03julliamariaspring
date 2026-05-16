package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;

import java.util.List;
import org.springframework.stereotype.Controller;

/**
 * Controller responsável por intermediar a comunicação entre a View e o Service.
 * 
 * Não contém regras de negócio, apenas delega as operações para o Service.
 */
@Controller
public class CursoController implements CursoIController {

    private final CursoIService cursoService;

    public CursoController(CursoIService cursoService) {
        this.cursoService = cursoService;
    }

    /**
     * Atualiza um curso existente.
     */
    @Override
    public Curso update(Curso curso) throws RuntimeException {
        return cursoService.update(curso);
    }

    /**
     * Remove um curso.
     */
    @Override
    public void delete(Curso curso) throws RuntimeException {
        cursoService.delete(curso);
    }

    /**
     * Lista todos os cursos.
     */
    @Override
    public List<Curso> findAll() throws RuntimeException {
        return cursoService.findAll();
    }

    /**
     * Busca curso por ID.
     */
    @Override
    public Curso findById(Long id) throws RuntimeException {
        return cursoService.findById(id);
    }
    
    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {
    return cursoService.findByNome(nome);
}

    @Override
    public Curso save(Curso curso) throws RuntimeException {
    return cursoService.save(curso);
}
    
}