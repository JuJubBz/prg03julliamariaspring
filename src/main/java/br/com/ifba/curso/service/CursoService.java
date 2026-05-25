package br.com.ifba.curso.service;

// Entidade (tabela do banco)
import br.com.ifba.curso.entity.Curso;

// Repository do Spring Data JPA (substitui o DAO)
import br.com.ifba.curso.repository.CursoRepository;

// Classe utilitária para validar String
import br.com.ifba.infrastructure.util.StringUtil;

import java.util.List;
import lombok.RequiredArgsConstructor;

// Indica que é uma classe de serviço (regra de negócio)
import org.springframework.stereotype.Service;

// Controla transações com o banco
import org.springframework.transaction.annotation.Transactional;

@Service // Marca como camada de serviço
@Transactional // Todas operações são transacionais
@RequiredArgsConstructor
public class CursoService implements CursoIService {

    // Injeta o repository (acesso ao banco)
    private final CursoRepository repository;

    @Override
    public Curso save(Curso curso) throws RuntimeException {

        // Valida os dados antes de salvar
        validarCurso(curso);

        // Salva no banco (insert)
        return repository.save(curso);
    }

    @Override
    public Curso update(Curso curso) throws RuntimeException {

        // Valida os dados
        validarCurso(curso);

        // Garante que tem ID (senão não atualiza)
        if (curso.getId() == null) {
            throw new RuntimeException("Curso não pode ser atualizado sem ID");
        }

        // No JPA, save também faz update
        return repository.save(curso);
    }

    @Override
    public List<Curso> findAll() throws RuntimeException {

        // Retorna todos os cursos
        return repository.findAll();
    }

    @Override
    public Curso findById(Long id) throws RuntimeException {

        // Valida ID
        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        // Busca por ID e lança erro se não encontrar
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    @Override
    public void delete(Curso curso) throws RuntimeException {

        // Valida se o curso é válido
        if (curso == null || curso.getId() == null) {
            throw new RuntimeException("Curso inválido para remoção");
        }

        // Remove do banco
        repository.delete(curso);
    }

    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {

        // Valida o nome
        if (StringUtil.isNullOrEmpty(nome)) {
            throw new RuntimeException("Nome para busca não pode ser vazio");
        }

        // Busca cursos pelo nome
        return repository.findByNome(nome);
    }

    // Método interno para validar regras de negócio
    private void validarCurso(Curso curso) {

        // Verifica se o objeto curso existe
        if (curso == null) {
            throw new RuntimeException("Curso não pode ser nulo");
        }

        // Valida nome
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("Nome é obrigatório");
        }

        // Valida descrição
        if (StringUtil.isNullOrEmpty(curso.getDescricao())) {
            throw new RuntimeException("Descrição é obrigatória");
        }

        // Valida quantidade
        if (curso.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        // Valida instituição
        if (StringUtil.isNullOrEmpty(curso.getInstituicao())) {
            throw new RuntimeException("Instituição é obrigatória");
        }
    }
}