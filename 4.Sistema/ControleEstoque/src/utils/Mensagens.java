package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Metodos para exibicao de mensagens rapidas na tela.
 * @author Nelson
 */
public class Mensagens {

    public static void exibirErro(String msg) {
        exibirErro(msg, "Erro");
    }
    
    public static void exibirErro(String msg, String titulo) {
        JOptionPane.showMessageDialog(new JFrame(), msg, titulo,
                JOptionPane.ERROR_MESSAGE);
    }
    
}
