import javax.swing.*;
import java.awt.*;

public class LojaDocesGridLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Venda de doces");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(5, 1));
        frame.getContentPane().setBackground(new Color(220, 220, 230));

        // Título
        JLabel titulo = new JLabel("Loja de doces", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titulo);

        // Produto 1
        frame.add(criarLinhaProduto("b.png", "R$ 10,00", "1"));

        // Produto 2
        frame.add(criarLinhaProduto("c.png", "R$ 15,00", "1"));

        // Produto 3
        frame.add(criarLinhaProduto("p.png", "R$ 25,00", "2"));

        // Botão
        JButton btnPedir = new JButton("Pedir");
        btnPedir.setBackground(Color.BLUE);
        btnPedir.setForeground(Color.WHITE);
        JPanel painelBotao = new JPanel();
        painelBotao.add(btnPedir);
        painelBotao.setBackground(new Color(220, 220, 230));
        frame.add(painelBotao);

        frame.setVisible(true);
    }

    private static JPanel criarLinhaProduto(String imagemPath, String preco, String quantidade) {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painel.setBackground(new Color(220, 220, 230));

        ImageIcon imagem;
        try {
            imagem = new ImageIcon(imagemPath);
            Image img = imagem.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imagem = new ImageIcon(img);
        } catch (Exception e) {
            imagem = new ImageIcon();
        }

        JLabel labelImagem = new JLabel(imagem);
        JLabel labelPreco = new JLabel(preco);
        JTextField campoQtd = new JTextField(quantidade, 3);

        painel.add(labelImagem);
        painel.add(labelPreco);
        painel.add(campoQtd);

        return painel;
    }
}
