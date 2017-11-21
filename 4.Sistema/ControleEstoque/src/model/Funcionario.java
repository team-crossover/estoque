/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConexaoBanco;
import utils.JanelaMensagem;

/**
 *
 * @author Nelson
 */
public class Funcionario {

    private String cpf;
    private String nome;
    private Login login;
    private FuncaoEnum funcao;

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

    public boolean inserirNoBanco() {
        
        String colunas = "`Cpf`, `Nome`, `User`, `Senha`, `Funcao`";
        String valores = "'" + getCpf() + "','" + getNome() + "','" + login.getUser() + "','"
                + login.getSenha() + "','" + getFuncaoString() + "'";
        try {
            ConexaoBanco.getInstance().insert("FUNCIONARIO", colunas, valores, true);
            return true;
        } catch (SQLException ex) {
            JanelaMensagem.exibirErro(ex.getMessage());
            return false;
        }
    }

    public boolean lerDoBanco(String user) {
        ResultSet rs;
        try {
            rs = ConexaoBanco.getInstance().query("FUNCIONARIO", "Cpf,Nome,Funcao,User,Senha", "User = '" + user + "'");
            if (rs.isClosed()){
                return false;
            }
            
            setCpf(rs.getString("Cpf"));
            setNome(rs.getString("Nome"));
            setFuncao(rs.getString("Funcao"));
            setLogin(new Login(rs.getString("User"), rs.getString("Senha")));
            return true;
        } catch (SQLException ex) {
            JanelaMensagem.exibirErro(ex.getMessage());
            return false;
        }

    }

}
