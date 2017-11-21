package model;

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
    public Funcionario validar() throws UserInexistente, SenhaInvalida {
        Funcionario funcionario = new Funcionario();

        //Verifica se o usuário existe e o obtém se existir
        if (!funcionario.lerDoBancoPorUser(user)) {
            throw new UserInexistente();
        }

        //Verifica se a senha do usuário obtido é a igual à que foi inserida
        if (!funcionario.getLogin().getSenha().equals(senha)) {
            throw new SenhaInvalida();
        }

        return funcionario;
    }
}
