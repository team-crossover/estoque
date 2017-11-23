package utils.exceptions;

public class SenhaInvalida extends Exception {

    /**
     * Mensagem a ser exibida quando há entrada de uma senha inválida.
     * 
     * @return A mensagem em questão.
     */
    @Override
    public String getMessage(){
        return "A senha inserida é inválida.";
    }
    
}
