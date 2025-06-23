import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MiniNavegadorArquivos {

    public static void main(String[] args) {
        File diretorioAtual = new File(System.getProperty("user.home"));
        

        while (true) {
            File[] conteudo = diretorioAtual.listFiles();
            StringBuilder listaParaExibicao = new StringBuilder();
            ArrayList<String> subdiretorios = new ArrayList<>();

            if (conteudo != null) {
                // Ordena para que os diretórios apareçam primeiro
                Arrays.sort(conteudo, (f1, f2) -> {
                    if (f1.isDirectory() && !f2.isDirectory()) return -1;
                    if (!f1.isDirectory() && f2.isDirectory()) return 1;
                    return f1.getName().compareToIgnoreCase(f2.getName());
                });

                for (File item : conteudo) {
                    String prefixo = item.isDirectory() ? "[D] " : "[A] ";
                    listaParaExibicao.append(prefixo).append(item.getName()).append("\n");
                    if (item.isDirectory()) {
                        subdiretorios.add(item.getName());
                    }
                }
            } else {
                listaParaExibicao.append("Não foi possível acessar o conteúdo deste diretório.\n");
            }
            
            // --- MELHORIA 1: Usa JScrollPane para a lista de arquivos ---
            JTextArea areaTexto = new JTextArea(listaParaExibicao.toString());
            areaTexto.setEditable(false);
            JScrollPane painelRolagem = new JScrollPane(areaTexto);
            painelRolagem.setPreferredSize(new Dimension(450, 300)); // Tamanho fixo para a janela

            String[] opcoes = {"Entrar em Subdiretório", "Voltar ao Diretório Pai", "Sair"};
            
            String tituloDialogo = "Navegando em: " + diretorioAtual.getAbsolutePath();

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    painelRolagem, // Exibe o painel com rolagem em vez do texto longo
                    tituloDialogo,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0: // Entrar em Subdiretório
                    if (subdiretorios.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há subdiretórios para entrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        continue; // Volta para o início do loop
                    }

                    // --- MELHORIA 2: Usa um menu suspenso para escolher o diretório ---
                    String nomeSubdiretorio = (String) JOptionPane.showInputDialog(
                            null,
                            "Selecione o subdiretório para entrar:",
                            "Navegar",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            subdiretorios.toArray(), // Array de opções para o menu
                            subdiretorios.get(0)
                    );

                    if (nomeSubdiretorio != null) {
                        File proximoDiretorio = new File(diretorioAtual, nomeSubdiretorio);
                        diretorioAtual = proximoDiretorio;
                    }
                    break;

                case 1: // Voltar ao Diretório Pai
                    File diretorioPai = diretorioAtual.getParentFile();
                    if (diretorioPai != null) {
                        diretorioAtual = diretorioPai;
                    } else {
                        JOptionPane.showMessageDialog(null, "Você já está no diretório raiz.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                case 2: // Sair
                case JOptionPane.CLOSED_OPTION:
                    System.exit(0);
                    break;
            }
        }
    }
}