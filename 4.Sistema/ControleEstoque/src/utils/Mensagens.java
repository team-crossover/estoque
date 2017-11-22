package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Metodos para exibicao de mensagens rapidas na tela.
 *
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

    public static void exibirAviso(String msg) {
        JOptionPane.showMessageDialog(new JFrame(), msg, "Aviso",
                JOptionPane.WARNING_MESSAGE);
    }

    public static boolean exibirSimOuNao(String msg) {
        int resposta = JOptionPane.showConfirmDialog(new JFrame(), msg,
                "Confirmação", JOptionPane.YES_NO_OPTION);
        return (resposta == JOptionPane.YES_OPTION);
    }

}
