/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

/**
 *
 * @author Natalia
 */
public class CampoObrigatorio extends Exception {

    @Override
    public String getMessage() {
        return "Preencha os campos obrigatórios (*)";
    }
}
