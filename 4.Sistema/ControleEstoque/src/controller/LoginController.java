/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Funcionario;
import model.Login;
import utils.Mensagens;
import utils.exceptions.*;
import view.FuncionarioView;
import view.LoginView;

/**
 *
 * @author Nelson
 */
public class LoginController {

    private LoginView view;

    public void setView(LoginView view) {
        this.view = view;
    }
    
    public void logar(String user, String senha) {
        Login login = new Login(user, senha);
        try {
            abrirMenu(login.validar());
        } catch (UserInexistente | SenhaInvalida ex) {
            Mensagens.exibirErro(ex.getMessage(), "Erro de Login");
        }
    }

    private void abrirMenu(Funcionario funcionario) {
        view.dispose();
        switch (funcionario.getFuncao()) {
            case ADMINISTRADOR:
                new FuncionarioView().setVisible(true);
                break;
            default:
                Mensagens.exibirErro("Ainda não há menus para sua função.");
                System.exit(1);
        }
    }

}
