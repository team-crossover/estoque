/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 * Mensagem a ser exibida quando há tentativa de cadastramento
 * com campos obrigatórios não preenchidos.
 *
 * @author Natalia
 *
 * @return A mensagem em questão.
 */
public class CampoObrigatorio extends Exception {

    @Override
    public String getMessage() {
        return "Preencha os campos obrigatórios (*)";
    }
}
