package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.ConexaoBanco;

/**
 *
 * @author Nelson
 */
public class Funcionario {

    private String cpf = "";
    private String nome = "";
    private Login login = new Login("", "");
    private FuncaoEnum funcao = FuncaoEnum.values()[0];

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public FuncaoEnum getFuncao() {
        return funcao;
    }

    public String getFuncaoString() {
        return funcao.toString();
    }

    public void setFuncao(String funcao) {
        this.funcao = FuncaoEnum.valueOf(funcao);
    }

    public static void inserir(Funcionario f) throws SQLException {
        String colunas = "`Cpf`, `Nome`, `User`, `Senha`, `Funcao`";
        String valores = "'" + f.getCpf() + "','" + f.getNome() + "','"
                + f.getLogin().getUser() + "','" + f.getLogin().getSenha()
                + "','" + f.getFuncaoString() + "'";
        ConexaoBanco.getInstance().inserir("FUNCIONARIO", colunas, valores);
    }

    public static void atualizar(String userAntigo, Funcionario novoF) throws SQLException {
        String sets = "Cpf = '" + novoF.getCpf() + "', Nome = '"
                + novoF.getNome() + "', User = '" + novoF.getLogin().getUser() + "',"
                + " Senha = '" + novoF.getLogin().getSenha() + "', Funcao = '"
                + novoF.getFuncaoString() + "'";
        ConexaoBanco.getInstance().atualizar("FUNCIONARIO", sets, "User = '"
                + userAntigo + "'");
    }

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

    public static int qntAdministradores() throws SQLException {
        ResultSet rs = ConexaoBanco.getInstance().obter("FUNCIONARIO",
                "count(Cpf)", "Funcao = '" + FuncaoEnum.ADMINISTRADOR.toString() + "'");

        return rs.getInt("count(Cpf)");
    }

    public static void deletarPorUser(String user) throws SQLException {
        ConexaoBanco.getInstance().deletar("FUNCIONARIO",
                "User = '" + user + "'");
    }

}
