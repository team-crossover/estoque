/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Funcionario;
import utils.Mensagens;
import view.FuncionarioView;

/**
 *
 * @author aluno
 */
public class FuncionarioController {
    
    private FuncionarioView view;

    public FuncionarioView getView() {
        return view;
    }

    public void setView(FuncionarioView view) {
        this.view = view;
    }
        
    public void detalharFuncionario(String user){
        Funcionario func = new Funcionario();
        /*if (!func.lerDoBancoPorUser(user)) {
            Mensagens.exibirErro("Usuário não encontrado.", user);
            return;
        }*/
        
        view.exibirJanelaDetalhes(func);        
    }
}
