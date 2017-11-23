package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConexaoBanco;

/**
 * Classe correspondente à entidade "Funcionário".
 * 
 * @author Nelson
 */
public class Funcionario {
  
    /**
     * O cpf do funcionário em questão.
     */
    private String cpf = "";

    /**
     * O nome do funcionário em questão.
     */
    private String nome = "";

    /**
     * A nova instância da Classe Login.
     */
    private Login login = new Login("", "");

    /**
     * A função exercida pelo funcionário em questão.
     */
    private FuncaoEnum funcao = FuncaoEnum.values()[0];

    /**
     * Método getter do Cadastro de Pessoa Física do funcionário.
     * 
     * @return O CPF do funcionário.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método setter do Cadastro de Pessoa Física do funcionário.
     * 
     * @param cpf O CPF a ser atualizado.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método getter do nome do funcionário.
     * 
     * @return O nome do funcionário em questão.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método setter do nome do funcionário.
     * 
     * @param nome O nome a ser atualizado.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método getter do Login.
     * 
     * @return O Login.
     */
    public Login getLogin() {
        return login;
    }

    /**
     * Método setter do Login.
     * 
     * @param login A nova instância do Login.
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * Método getter da função exercida pelo funcionário em questão.
     * 
     * @return A função do funcionário.
     */
    public FuncaoEnum getFuncao() {
        return funcao;
    }

    /**
     * Método que associa uma função existente a uma String.
     * 
     * @return A função em questão.
     */
    public String getFuncaoString() {
        return funcao.toString();
    }

    /**
     * Método setter da função exercida pelo funcionário em questão.
     * 
     * @param funcao A função a ser atualizada.
     */
    public void setFuncao(String funcao) {
        this.funcao = FuncaoEnum.valueOf(funcao);
    }

    /**
     * Método para cadastro de um novo funcionário no sistema. Os dados deste
     * são inseridos em uma tabela do banco de dados.
     * 
     * @param f A instância da classe Funcionário.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static void inserir(Funcionario f) throws SQLException {
        String colunas = "`Cpf`, `Nome`, `User`, `Senha`, `Funcao`";
        String valores = "'" + f.getCpf() + "','" + f.getNome() + "','"
                + f.getLogin().getUser() + "','" + f.getLogin().getSenha()
                + "','" + f.getFuncaoString() + "'";
        ConexaoBanco.getInstance().inserir("FUNCIONARIO", colunas, valores);
    }

    /**
     * Método para atualizar dados do funcionário no sistema. Esses dados são
     * atualizados na tabela do banco de dados.
     * 
     * @param userAntigo O usuário anteriormente registrado.
     * @param novoF A instância da entidade para se extrair o nome atualizado.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static void atualizar(String userAntigo, Funcionario novoF) throws SQLException {
        String sets = "Cpf = '" + novoF.getCpf() + "', Nome = '"
                + novoF.getNome() + "', User = '" + novoF.getLogin().getUser() + "',"
                + " Senha = '" + novoF.getLogin().getSenha() + "', Funcao = '"
                + novoF.getFuncaoString() + "'";
        ConexaoBanco.getInstance().atualizar("FUNCIONARIO", sets, "User = '"
                + userAntigo + "'");
    }

    /**
     * Método para ler dados de determinado funcionário.
     * 
     * @param user O funcionário em questão.
     * @return A instância da classe Funcionário.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static Funcionario lerPorUser(String user) throws SQLException {
        ResultSet rs = ConexaoBanco.getInstance().obter("FUNCIONARIO",
                "Cpf,Nome,Funcao,User,Senha", "User = '" + user + "'");

        if (rs.isClosed()) {
            return null;
        }

        Funcionario f = new Funcionario();
        f.setCpf(rs.getString("Cpf"));
        f.setNome(rs.getString("Nome"));
        f.setFuncao(rs.getString("Funcao"));
        f.setLogin(new Login(rs.getString("User"), rs.getString("Senha")));
        return f;
    }

    /**
     * Método para listar os funcionários com seus respectivos dados.
     * 
     * @return Uma lista com os funcionários e seus dados.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static List<Funcionario> lerTodos() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        ResultSet rs = ConexaoBanco.getInstance().obter("FUNCIONARIO",
                "Cpf,Nome,Funcao,User,Senha", "");

        if (rs.isClosed()) {
            return null;
        }

        while (rs.next()) {
            Funcionario f = new Funcionario();
            f.setCpf(rs.getString("Cpf"));
            f.setNome(rs.getString("Nome"));
            f.setFuncao(rs.getString("Funcao"));
            f.setLogin(new Login(rs.getString("User"), rs.getString("Senha")));
            funcionarios.add(f);
        }

        return funcionarios;
    }

    /**
     * Método que realiza uma consulta ao banco de dados para retornar o
     * número de funcionários que são administradores.
     * 
     * @return A quantidade de administradores presentes no sistema.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static int qntAdministradores() throws SQLException {
        ResultSet rs = ConexaoBanco.getInstance().obter("FUNCIONARIO",
                "count(Cpf)", "Funcao = '" + FuncaoEnum.ADMINISTRADOR.toString() + "'");

        return rs.getInt("count(Cpf)");
    }

    /**
     * Método para remover um usuário do sistema através de seu usuário.
     * 
     * @param user O usuário do funcionário a ser removido.
     * @throws SQLException Erros relacionados a SQL.
     */
    public static void deletarPorUser(String user) throws SQLException {
        ConexaoBanco.getInstance().deletar("FUNCIONARIO",
                "User = '" + user + "'");
    }

}
