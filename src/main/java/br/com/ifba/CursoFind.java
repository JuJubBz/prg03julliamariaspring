package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por realizar buscas de Cursos no banco de dados.
 */
public class CursoFind {

    // Cria a fábrica de conexões baseada na unidade de persistência definida no persistence.xml
    private static final EntityManagerFactory entityManagerFactory = 
            Persistence.createEntityManagerFactory("gerenciamento_curso");

    // Gerenciador de entidades: é quem realmente executa as operações (buscar, salvar, etc.)
    private static final EntityManager entityManager = 
            entityManagerFactory.createEntityManager();

    /**
     * Busca um curso pelo seu identificador (ID).
     * @param id O ID do curso no banco de dados.
     * @return O objeto Curso preenchido com os dados do banco.
     * @throws Exception Caso o curso não seja encontrado.
     */
    public Curso buscar(Long id) throws Exception {
        // O método find tenta localizar o registro na tabela 'curso' usando a Chave Primária
        Curso curso = entityManager.find(Curso.class, id);

        // Verifica se o banco retornou algum resultado
        if (curso == null) {
            // Lançando exceção caso a busca retorne null (ID inexistente)
            throw new Exception("Sistema: Curso com ID " + id + " não localizado.");
        }
        return curso;
    }

    public static void main(String[] args) {
        try {
            // Instancia a classe de busca
            CursoFind finder = new CursoFind();
            
            // Tenta buscar o curso com ID 1 (L indica que é do tipo Long)
            Curso curso = finder.buscar(1L);

            // Exibe os dados recuperados do PostgreSQL no console
            System.out.println("=== CURSO ENCONTRADO ===");
            System.out.println("ID: " + curso.getId());
            System.out.println("Nome: " + curso.getNome());
            System.out.println("Instituição: " + curso.getInstituicao());

        } catch (Exception e) {
            // Captura e exibe mensagens de erro (ex: curso não encontrado ou falha na conexão)
            System.err.println("Erro de busca: " + e.getMessage());
        } finally {
            // Bloco que sempre executa para fechar as conexões e liberar memória
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}