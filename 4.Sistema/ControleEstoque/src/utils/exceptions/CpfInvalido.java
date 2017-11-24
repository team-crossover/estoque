/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 * Mensagem a ser exibida quando há tentativa de cadastramento
 * com Cpf Invalido.
 *
 * @return A mensagem em questão.
 */
public class CpfInvalido extends Exception {

    @Override
    public String getMessage() {
        return "CPF em formto inválido!\n"
                + "*Verifique se há onze dígitos e se são somente números";
    }
}
