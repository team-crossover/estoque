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
 * Classe controller da entidade Funcionário.
 * 
 * @author aluno
 */
public class FuncionarioController {

    /**
     * Instancia classe FuncionarioView.
     */
    private FuncionarioView view;

    /**
     * Método getter da interface gráfica.
     * 
     * @return a instância da interface.
     */
    public FuncionarioView getView() {
        return view;
    }

    /**
     * Método setter da interface gráfica.
     * 
     * @param view A instância da interface.
     */
    public void setView(FuncionarioView view) {
        this.view = view;
    }

    /**
     * Método para exibir a funcionalidade "Detalhar Funcionário" contendo as
     * opções de editar e excluir funcionário.
     * 
     * @param user O usuário já criado, que estará listado.
     * @throws SQLException Erros relacionados a SQL.
     * @throws UserInexistente Caso de usuário que não está listado.
     */
    public void detalharFuncionario(String user) throws SQLException, UserInexistente {
        Funcionario func = Funcionario.lerPorUser(user);
        if (user == null) {
            throw new UserInexistente();
        }
        view.exibirDetalhes(func, false);
    }

    /**
     * Método para exibir a funcionalidade "Inserir Funcionário".
     * 
     * @throws SQLException Erros relacionados a SQL.
     */
    public void inserirFuncionario() throws SQLException {
        Funcionario func = new Funcionario();
        view.exibirDetalhes(func, true);
    }

    /**
     * Método para salvar alterações feitas no cadastro do funcionário.
     * 
     * @param userAntigo O usuário anterior.
     * @param funcionario A entidade correspondente ao funcionário no sistema.
     * @param insercao Para permitir a inserção do funcionário.
     * @throws SQLException Erros relacionados a SQL.
     * @throws ExcluirUnicoAdministrador Para evitar alterar a função do
     * administrador.
     */
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

    /**
     * Método para a excluir um funcionário cadastrado.
     * 
     * @param nome O nome do funcionário em questão.
     * @param user O usuário do funcionário em questão.
     * @param funcao A função do funcionário em questão.
     * @throws SQLException Erros relacionados a SQL.
     * @throws ExcluirUnicoAdministrador Para evitar remover o único usuário com
     * funções de administrador.
     */
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

    /**
     * Método para atualizar a lista com os funcionários.
     * 
     * @throws SQLException Erros relacionados a SQL.
     */
    public void atualizarTabela() throws SQLException {
        view.limparFuncionarios();
        List<Funcionario> funcs = Funcionario.lerTodos();
        for (Funcionario func : funcs) {
            view.adicionarFuncionario(func);
        }
    }
}
