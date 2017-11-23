package utils.exceptions;

public class ExcluirUnicoAdministrador extends Exception {

    /**
     * Mensagem a ser exibida quando há a tentativa de remoção do único
     * administrador cadastrado.
     * 
     * @return A mensagem em questão.
     */
    @Override
    public String getMessage() {
        return "Incapaz de remover o único administrador.";
    }
}
