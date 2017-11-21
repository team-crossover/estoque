package utils.exceptions;

public class UserInexistente extends Exception {

    @Override
    public String getMessage() {
        return "O usuário inserido não foi encontrado.";
    }

}
