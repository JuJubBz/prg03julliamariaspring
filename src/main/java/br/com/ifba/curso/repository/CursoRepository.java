package br.com.ifba.curso.repository;

// Importa a entidade Curso (tabela do banco)
import br.com.ifba.curso.entity.Curso;

// Importa List para retornar listas de cursos
import java.util.List;

// Interface principal do Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Marca como componente do Spring (opcional nesse caso)
import org.springframework.stereotype.Repository;

/**
 * Interface responsável por acessar os dados da entidade Curso
 */

@Repository // Diz ao Spring que isso é um componente de acesso a dados (DAO)
public interface CursoRepository extends JpaRepository<Curso, Long>{

    // Método criado automaticamente pelo Spring Data JPA
    // Busca cursos pelo nome
    public List<Curso> findByNome(String nome);

}