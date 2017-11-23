package utils.exceptions;

public class UserInexistente extends Exception {

    /**
     * Mensagem a ser exibida quando ocorre a entrada no login de um usuário não
     * cadastrado.
     * 
     * @return A mensagem em questão.
     */
    @Override
    public String getMessage() {
        return "O usuário inserido não foi encontrado.";
    }

}
