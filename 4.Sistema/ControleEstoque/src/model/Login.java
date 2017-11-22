package model;

import java.sql.SQLException;
import utils.exceptions.*;

/**
 * 
 * @author Nelson
 */
public class Login {

    private String user;
    private String senha;

    public Login(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Verifica se este login pertence a algum funcionário (deve ter o mesmo
     * usuário e a mesma senha).
     *
     * @return O Funcionário que possui o login fornecido.
     * @throws UserInexistente
     * @throws SenhaInvalida
     */
    public Funcionario validar() throws UserInexistente, SenhaInvalida, SQLException {
        Funcionario funcionario = Funcionario.lerPorUser(getUser());

        if (funcionario == null) {
            throw new UserInexistente();
        }

        if (!funcionario.getLogin().getSenha().equals(this.getSenha())) {
            throw new SenhaInvalida();
        }

        return funcionario;
    }
}
