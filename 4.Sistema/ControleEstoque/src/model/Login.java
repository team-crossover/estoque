package model;

import java.sql.SQLException;
import utils.exceptions.*;

/**
 * 
 * @author Nelson
 */
public class Login {

    /**
     * O usuário do funcionário.
     */
    private String user;

    /**
     * A senha do funcionário.
     */
    private String senha;

    /**
     * Construção da classe.
     * 
     * @param user O usuário em questão.
     * @param senha A senha do usuário em questão.
     */
    public Login(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    /**
     * Getter para o usuário.
     * 
     * @return O usuário em questão.
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter para o usuário.
     * 
     * @param user O usuário a ser atualizado.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Getter para a senha.
     * 
     * @return A senha em questão.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Setter para a senha.
     * 
     * @param senha A senha a ser atualizada.
     */
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
