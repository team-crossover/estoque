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
        view.exibirDetalhes(func, false);
    }

    public void inserirFuncionario() throws SQLException {
        Funcionario func = new Funcionario();
        view.exibirDetalhes(func, true);
    }

    public void salvarFuncionario(String userAntigo, Funcionario funcionario,
            boolean insercao) throws SQLException, ExcluirUnicoAdministrador {

        if (insercao) {
            Funcionario.inserir(funcionario);
            
        } else {
            //Evita atualizar o único administrador para uma função diferente
            String funcao = funcionario.getFuncaoString();
            if (!funcao.equals(FuncaoEnum.ADMINISTRADOR.toString())
                    && Funcionario.qntAdministradores() == 1) {
                throw new ExcluirUnicoAdministrador();
            }
            
            Funcionario.atualizar(userAntigo, funcionario);
        }
        
        atualizarTabela();
        view.ocultarDetalhes();
        Mensagens.exibirAviso(funcionario.getNome() + " foi atualizado com sucesso!");
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
                atualizarTabela();
                view.ocultarDetalhes();
                Mensagens.exibirAviso(nome + " foi excluído com sucesso!");
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }

    public void atualizarTabela() throws SQLException {
        view.limparFuncionarios();
        List<Funcionario> funcs = Funcionario.lerTodos();
        for (Funcionario func : funcs) {
            view.adicionarFuncionario(func);
        }
    }
}
