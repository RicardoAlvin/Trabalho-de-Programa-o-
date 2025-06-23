import javax.swing.JOptionPane;
import java.io.File;

/**
 * Um navegador de arquivos basico utilizando javax.swing.JOptionPane.
 * Permite listar diretorios, navegar para subdiretorios e voltar ao diretorio pai.
 */
public class FileExplorer {

    private static File currentDirectory;

    public static void main(String[] args) {
        // Inicializa com o diretorio de trabalho atual do sistema
        currentDirectory = new File(System.getProperty("user.dir"));

        // Loop principal de interacao com o usuario
        while (true) {
            String[] options = {"Listar", "Entrar", "Voltar", "Sair"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Diretorio atual:\n" + currentDirectory.getAbsolutePath(),
                "Navegador de Arquivos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            // Executa a acao escolhida
            switch (choice) {
                case 0 -> listarConteudo();
                case 1 -> entrarNoSubdiretorio();
                case 2 -> voltarDiretorioPai();
                case 3, JOptionPane.CLOSED_OPTION -> {
                    JOptionPane.showMessageDialog(null, "Saindo do navegador de arquivos.");
                    return;
                }
            }
        }
    }

    /**
     * Lista o conteudo do diretorio atual.
     */
    private static void listarConteudo() {
        File[] arquivos = currentDirectory.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            JOptionPane.showMessageDialog(null, "Diretorio vazio.");
            return;
        }

        StringBuilder conteudo = new StringBuilder("Conteudo de:\n" + currentDirectory.getAbsolutePath() + "\n\n");

        for (int i = 0; i < arquivos.length; i++) {
            conteudo.append("[").append(i).append("] ");
            conteudo.append(arquivos[i].isDirectory() ? "[DIR] " : "[ARQ] ");
            conteudo.append(arquivos[i].getName()).append("\n");
        }

        JOptionPane.showMessageDialog(null, conteudo.toString());
    }

    /**
     * Permite ao usuario entrar em um subdiretorio.
     */
    private static void entrarNoSubdiretorio() {
        File[] arquivos = currentDirectory.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            JOptionPane.showMessageDialog(null, "Diretorio vazio. Nada para entrar.");
            return;
        }

        String entrada = JOptionPane.showInputDialog("Digite o numero do subdiretorio para entrar:");
        if (entrada == null) return;

        try {
            int indice = Integer.parseInt(entrada);

            if (indice < 0 || indice >= arquivos.length) {
                JOptionPane.showMessageDialog(null, "Indice invalido.");
            } else if (!arquivos[indice].isDirectory()) {
                JOptionPane.showMessageDialog(null, "O item selecionado nao e um diretorio.");
            } else {
                currentDirectory = arquivos[indice]; // Entra no subdiretorio
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida. Digite um numero.");
        }
    }

    /**
     * Volta ao diretorio pai, se possivel.
     */
    private static void voltarDiretorioPai() {
        File pai = currentDirectory.getParentFile();

        if (pai != null && pai.exists()) {
            currentDirectory = pai;
        } else {
            JOptionPane.showMessageDialog(null, "Voce ja esta no diretorio raiz.");
        }
    }
}
