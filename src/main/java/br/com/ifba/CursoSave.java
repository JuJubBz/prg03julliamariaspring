package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe responsável por inserir novos registros de Cursos no banco de dados PostgreSQL.
 */
public class CursoSave {

    // Cria a fábrica de conexões baseada na unidade de persistência "gerenciamento_curso"
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("gerenciamento_curso");

    // O "braço direito" do Hibernate: gerencia o ciclo de vida dos objetos e a conexão
    private static final EntityManager entityManager =
            entityManagerFactory.createEntityManager();

    /**
     * Método que cria um novo objeto Curso e o grava permanentemente no banco.
     */
    public void salvar() throws Exception {
        try {
            // Passo 1: Criamos uma nova instância da nossa entidade Curso
            Curso curso = new Curso();
            
            // Passo 2: Preenchemos os atributos do objeto com os dados desejados
            curso.setNome("Java POO");
            curso.setDescricao("Curso de Hibernate e JPA");
            curso.setQuantidade(30);
            curso.setInstituicao("IFBA");

            // Passo 3: Iniciamos uma transação. Operações de salvamento EXIGEM isso por segurança.
            entityManager.getTransaction().begin();
            
            // Passo 4: O método 'persist' torna o objeto Java "persistente" (prepara para o banco)
            entityManager.persist(curso);
            
            // Passo 5: O 'commit' envia o comando INSERT definitivo para o PostgreSQL
            entityManager.getTransaction().commit();
            
            System.out.println("Curso salvo com sucesso!");
        } catch (Exception e) {
            // Se houver qualquer erro (ex: banco fora do ar), desfazemos a operação para evitar sujeira
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // O famoso "Ctrl+Z" do banco
            }
            // Repassamos a mensagem de erro para quem chamou o método
            throw new Exception("Falha ao salvar o curso: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            // Cria o objeto de salvamento e executa o método salvar
            new CursoSave().salvar();
        } catch (Exception e) {
            // Captura falhas e exibe no console de erro (em vermelho no NetBeans)
            System.err.println("Erro capturado: " + e.getMessage());
        } finally {
            // O bloco finally garante que, dando certo ou errado, a conexão será encerrada
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}