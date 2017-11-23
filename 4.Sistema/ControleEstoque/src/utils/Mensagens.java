package utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Metodos para exibicao de mensagens rapidas na tela.
 *
 * @author Nelson
 */
public class Mensagens {

    /**
     * Exibição de erro através de mensagem.
     * 
     * @param msg A mensagem em questão.
     */
    public static void exibirErro(String msg) {
        exibirErro(msg, "Erro");
    }

    /**
     * Exibição de erro através da interface gráfica.
     * 
     * @param msg A mensagem de erro.
     * @param titulo O título da mensagem de erro.
     */
    public static void exibirErro(String msg, String titulo) {
        JOptionPane.showMessageDialog(new JFrame(), msg, titulo,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibição de aviso na interface gráfica.
     * 
     * @param msg A especificação do aviso em questão.
     */
    public static void exibirAviso(String msg) {
        JOptionPane.showMessageDialog(new JFrame(), msg, "Aviso",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Exibição de confirmação.
     * 
     * @param msg A especificação da confirmação em questão.
     * @return A resposta à confirmação.
     */
    public static boolean exibirSimOuNao(String msg) {
        int resposta = JOptionPane.showConfirmDialog(new JFrame(), msg,
                "Confirmação", JOptionPane.YES_NO_OPTION);
        return (resposta == JOptionPane.YES_OPTION);
    }

}
