package controller;

import java.sql.SQLException;
import model.Funcionario;
import model.Login;
import utils.Mensagens;
import utils.exceptions.*;
import view.FuncionarioView;
import view.LoginView;

/**
 * Classe controller das funcionalidades relacionadas a Login.
 * 
 * @author Nelson
 */
public class LoginController {

    /**
     * Declara um objeto da classe LoginView.
     */
    private LoginView view;

    /**
     * Setter da interface gráfica.
     * 
     * @param view A nova instância da interface.
     */
    public void setView(LoginView view) {
        this.view = view;
    }

    /**
     * Exibe a funcionalidade de Login.
     * 
     * @param user O usuário que irá realizar o login.
     * @param senha A senha do usuário em questão.
     * @throws SQLException Erros relacionados a SQL.
     */
    public void logar(String user, String senha) throws SQLException {
        Login login = new Login(user, senha);
        try {
            abrirMenu(login.validar());
        } catch (UserInexistente | SenhaInvalida ex) {
            Mensagens.exibirErro(ex.getMessage(), "Erro de Login");
        }
    }

    /**
     * Exibe o menu relativo aos funcionários.
     * 
     * @param funcionario A entidade correspondente aos funcionários.
     */
    private void abrirMenu(Funcionario funcionario) {
        view.dispose();
        switch (funcionario.getFuncao()) {
            case ADMINISTRADOR:
                new FuncionarioView().setVisible(true);
                break;
            default:
                Mensagens.exibirErro("Ainda não há menus para "
                        + funcionario.getFuncaoString());
                System.exit(1);
        }
    }

}
