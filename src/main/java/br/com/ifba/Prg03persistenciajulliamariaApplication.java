package br.com.ifba;

import br.com.ifba.curso.view.CursoListar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Prg03persistenciajulliamariaApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(Prg03persistenciajulliamariaApplication.class, args);

        java.awt.EventQueue.invokeLater(() -> {

            CursoListar tela = context.getBean(CursoListar.class);

            tela.setLocationRelativeTo(null);
            System.out.println("AQUI");
            tela.setVisible(true);
        });
    }
}