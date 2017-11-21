package utils.exceptions;

public class SenhaInvalida extends Exception {
    
    @Override
    public String getMessage(){
        return "A senha inserida é inválida.";
    }
    
}
