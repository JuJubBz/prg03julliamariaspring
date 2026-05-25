package br.com.ifba.curso.service;

// Entidade (tabela do banco)
import br.com.ifba.curso.entity.Curso;

// Repository do Spring Data JPA
import br.com.ifba.curso.repository.CursoRepository;

// Classe utilitária para validar String
import br.com.ifba.infrastructure.util.StringUtil;

import java.util.List;

import lombok.RequiredArgsConstructor;

// Importações de log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Indica que é uma classe de serviço
import org.springframework.stereotype.Service;

// Controla transações com o banco
import org.springframework.transaction.annotation.Transactional;

@Service // Marca como camada de serviço
@Transactional // Todas operações são transacionais
@RequiredArgsConstructor
public class CursoService implements CursoIService {

    // Cria o logger da classe
    private static final Logger log =
            LoggerFactory.getLogger(CursoService.class);

    // Injeta o repository
    private final CursoRepository repository;

    @Override
    public Curso save(Curso curso) throws RuntimeException {

        // Log do início do salvamento
        log.info("Iniciando salvamento do curso");

        // Valida os dados antes de salvar
        validarCurso(curso);

        // Salva no banco
        Curso cursoSalvo = repository.save(curso);

        // Log de sucesso
        log.info("Curso salvo com sucesso. ID: {}", cursoSalvo.getId());

        return cursoSalvo;
    }

    @Override
    public Curso update(Curso curso) throws RuntimeException {

        // Log do início da atualização
        log.info("Atualizando curso ID: {}", curso.getId());

        // Valida os dados
        validarCurso(curso);

        // Garante que possui ID
        if (curso.getId() == null) {

            // Log de erro
            log.error("Tentativa de atualizar curso sem ID");

            throw new RuntimeException("Curso não pode ser atualizado sem ID");
        }

        // Atualiza no banco
        Curso cursoAtualizado = repository.save(curso);

        // Log de sucesso
        log.info("Curso atualizado com sucesso");

        return cursoAtualizado;
    }

    @Override
    public List<Curso> findAll() throws RuntimeException {

        // Log da listagem
        log.info("Listando todos os cursos");

        return repository.findAll();
    }

    @Override
    public Curso findById(Long id) throws RuntimeException {

        // Log da busca
        log.info("Buscando curso pelo ID: {}", id);

        // Valida ID
        if (id == null) {

            // Log de erro
            log.error("ID informado é nulo");

            throw new RuntimeException("ID não pode ser nulo");
        }

        // Busca por ID
        return repository.findById(id)
                .orElseThrow(() -> {

                    // Log caso não encontre
                    log.error("Curso não encontrado");

                    return new RuntimeException("Curso não encontrado");
                });
    }

    @Override
    public void delete(Curso curso) throws RuntimeException {

        // Log da remoção
        log.warn("Removendo curso");

        // Valida o curso
        if (curso == null || curso.getId() == null) {

            // Log de erro
            log.error("Curso inválido para remoção");

            throw new RuntimeException("Curso inválido para remoção");
        }

        // Remove do banco
        repository.delete(curso);

        // Log de sucesso
        log.info("Curso removido com sucesso");
    }

    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {

        // Log da busca por nome
        log.info("Buscando cursos pelo nome: {}", nome);

        // Valida o nome
        if (StringUtil.isNullOrEmpty(nome)) {

            // Log de erro
            log.error("Nome informado está vazio");

            throw new RuntimeException("Nome para busca não pode ser vazio");
        }

        // Busca cursos pelo nome
        return repository.findByNome(nome);
    }

    // Método interno para validar regras de negócio
    private void validarCurso(Curso curso) {

        // Log de validação
        log.debug("Validando dados do curso");

        // Verifica se o curso existe
        if (curso == null) {

            log.error("Curso está nulo");

            throw new RuntimeException("Curso não pode ser nulo");
        }

        // Valida nome
        if (StringUtil.isNullOrEmpty(curso.getNome())) {

            log.error("Nome do curso é obrigatório");

            throw new RuntimeException("Nome é obrigatório");
        }

        // Valida descrição
        if (StringUtil.isNullOrEmpty(curso.getDescricao())) {

            log.error("Descrição do curso é obrigatória");

            throw new RuntimeException("Descrição é obrigatória");
        }

        // Valida quantidade
        if (curso.getQuantidade() <= 0) {

            log.error("Quantidade inválida");

            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        // Valida instituição
        if (StringUtil.isNullOrEmpty(curso.getInstituicao())) {

            log.error("Instituição é obrigatória");

            throw new RuntimeException("Instituição é obrigatória");
        }

        // Log de sucesso da validação
        log.debug("Curso validado com sucesso");
    }
}