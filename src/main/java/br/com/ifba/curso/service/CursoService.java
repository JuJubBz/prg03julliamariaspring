package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Classe responsável pelas regras de negócio da entidade Curso.
 * 
 * Atua como intermediária entre o Controller e o DAO,
 * garantindo que os dados estejam válidos antes de serem persistidos.
 */
@Service
@Transactional
public class CursoService implements CursoIService {

    private final CursoDao cursoDao;

    public CursoService(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }
    
    @Override
    @Transactional
    public Curso save(Curso curso) throws RuntimeException {

        validarCurso(curso);

        return cursoDao.save(curso);
    }

    /**
     * Atualiza um curso existente no banco.
     */
    @Override
    @Transactional
    public Curso update(Curso curso) throws RuntimeException {

        // Valida antes de atualizar
        validarCurso(curso);

        // Garante que o curso existe (tem ID)
        if (curso.getId() == null) {
            throw new RuntimeException("Curso não pode ser atualizado sem ID");
        }

        return cursoDao.update(curso);
    }

    /**
     * Retorna todos os cursos cadastrados no banco.
     */
    @Override
    public List<Curso> findAll() throws RuntimeException {
        return cursoDao.findAll();
    }

    /**
     * Busca um curso pelo ID.
     */
    @Override
    public Curso findById(Long id) throws RuntimeException {

        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        Curso curso = cursoDao.findById(id);

        if (curso == null) {
            throw new RuntimeException("Curso não encontrado");
        }

        return curso;
    }

    /**
     * Remove um curso do banco de dados.
     */
    @Override
    @Transactional
    public void delete(Curso curso) throws RuntimeException {

        if (curso == null || curso.getId() == null) {
            throw new RuntimeException("Curso inválido para remoção");
        }

        cursoDao.delete(curso);
    }

    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {

    if (StringUtil.isNullOrEmpty(nome)) {
        throw new RuntimeException("Nome para busca não pode ser vazio");
    }

    return cursoDao.findByNome(nome);
    }
    
    /**
     * Método responsável por validar as regras de negócio do Curso.
     */
    private void validarCurso(Curso curso) {

        if (curso == null) {
            throw new RuntimeException("Curso não pode ser nulo");
        }

        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("Nome é obrigatório");
        }

        if (StringUtil.isNullOrEmpty(curso.getDescricao())) {
            throw new RuntimeException("Descrição é obrigatória");
        }

        if (curso.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        if (StringUtil.isNullOrEmpty(curso.getInstituicao())) {
            throw new RuntimeException("Instituição é obrigatória");
        }
    }
}