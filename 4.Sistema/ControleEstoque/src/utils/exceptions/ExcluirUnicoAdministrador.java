package utils.exceptions;

public class ExcluirUnicoAdministrador extends Exception {

    @Override
    public String getMessage() {
        return "Incapaz de excluir o único administrador.";
    }
}
