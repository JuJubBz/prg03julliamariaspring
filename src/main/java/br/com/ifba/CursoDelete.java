package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por remover registros de Cursos do banco de dados.
 */
public class CursoDelete {

    // Cria a fábrica de conexões com o banco definido no seu persistence.xml
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("gerenciamento_curso");

    // O gerenciador que executa as ações no banco
    private static final EntityManager entityManager =
            entityManagerFactory.createEntityManager();

    /**
     * Método para remover um curso pelo ID.
     * Diferente da busca, operações de escrita (remover/salvar) exigem transações.
     */
    public void remover(Long id) throws Exception {
        // Primeiro, tentamos encontrar o curso que será deletado
        Curso curso = entityManager.find(Curso.class, id);

        // Se o curso não existir, nem tentamos deletar
        if (curso == null) {
            throw new Exception("Não foi possível remover: O curso " + id + " não existe no banco.");
        }

        try {
            // Iniciamos uma transação (proteção para garantir que a operação seja concluída)
            entityManager.getTransaction().begin();
            
            // Marca o objeto curso para ser removido do banco de dados
            entityManager.remove(curso);
            
            // Finaliza a transação e aplica a mudança permanentemente no PostgreSQL
            entityManager.getTransaction().commit();
            
            System.out.println("Curso removido com sucesso!");
        } catch (Exception e) {
            // Caso aconteça algum erro (ex: banco fora do ar), desfazemos o que foi tentado
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Volta atrás para manter o banco seguro
            }
            throw new Exception("Erro ao processar remoção: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Instancia a classe e tenta remover o curso com ID 1
            new CursoDelete().remover(1L);
        } catch (Exception e) {
            // Exibe mensagem de erro caso o curso não exista ou ocorra falha técnica
            System.err.println("Falha na operação: " + e.getMessage());
        } finally {
            // Sempre fecha as conexões para não gastar memória do computador
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}