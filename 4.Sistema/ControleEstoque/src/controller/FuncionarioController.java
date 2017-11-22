/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.FuncaoEnum;
import model.Funcionario;
import utils.Mensagens;
import utils.exceptions.ExcluirUnicoAdministrador;
import utils.exceptions.UserInexistente;
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

    public void detalharFuncionario(String user) throws SQLException, UserInexistente {
        Funcionario func = Funcionario.lerPorUser(user);
        if (user == null) {
            throw new UserInexistente();
        }

        view.exibirDetalhes(func);
    }

    public void excluirFuncionario(String nome, String user, String funcao)
            throws SQLException, ExcluirUnicoAdministrador {

        if (funcao.equals(FuncaoEnum.ADMINISTRADOR.toString())
                && Funcionario.qntAdministradores() == 1) {
            throw new ExcluirUnicoAdministrador();
        }

        boolean confirma = Mensagens.exibirSimOuNao("Deseja mesmo excluir "
                + nome + "?");

        if (confirma) {
            try {
                Funcionario.deletarPorUser(user);
                popularTabela();
                Mensagens.exibirAviso(nome + " foi excluído com sucesso!");
                view.ocultarDetalhes();
            } catch (SQLException ex) {
                Mensagens.exibirErro(ex.getMessage());
            }
        }
    }

    public void popularTabela() throws SQLException {
        view.limparFuncionarios();
        List<Funcionario> funcs = Funcionario.lerTodos();
        for (Funcionario func : funcs) {
            view.adicionarFuncionario(func);
        }
    }
}
