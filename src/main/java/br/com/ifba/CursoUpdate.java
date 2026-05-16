package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por atualizar os dados de um Curso já existente no banco de dados.
 */
public class CursoUpdate {

    // Configura a fábrica de conexões apontando para o banco "gerenciamento_curso"
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("gerenciamento_curso");

    // Gerenciador de entidades que executará a atualização
    private static final EntityManager entityManager =
            entityManagerFactory.createEntityManager();

    /**
     * Método para atualizar os dados de um curso pelo ID.
     * @param id O identificador do curso que deseja modificar.
     */
    public void atualizar(Long id) throws Exception {
        // Passo 1: Busca o curso no banco para garantir que ele existe
        Curso curso = entityManager.find(Curso.class, id);

        // Se o curso não existir, interrompe o processo com um erro informativo
        if (curso == null) {
            throw new Exception("Erro de atualização: Curso com ID " + id + " não encontrado!");
        }

        try {
            // Passo 2: Inicia a transação (necessário para qualquer alteração no banco)
            entityManager.getTransaction().begin();
            
            // Passo 3: Altera os dados do objeto Java com as novas informações
            curso.setNome("Java Avançado");
            curso.setDescricao("Curso atualizado com JPA e Hibernate");
            curso.setQuantidade(50);
            curso.setInstituicao("IFBA Campus Salvador");

            // Passo 4: O método 'merge' sincroniza o estado do objeto Java com o banco de dados
            entityManager.merge(curso);
            
            // Passo 5: Confirma a alteração permanentemente no PostgreSQL
            entityManager.getTransaction().commit();
            
            System.out.println("Curso atualizado com sucesso!");
        } catch (Exception e) {
            // Se algo der errado (ex: perda de conexão), desfaz a alteração
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new Exception("Erro durante a transação de update: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Tenta atualizar o curso que possui o ID 1
            new CursoUpdate().atualizar(1L);
        } catch (Exception e) {
            // Exibe avisos caso o ID não exista no banco de dados
            System.err.println("Aviso: " + e.getMessage());
        } finally {
            // Garante o fechamento das conexões para evitar vazamento de memória
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}