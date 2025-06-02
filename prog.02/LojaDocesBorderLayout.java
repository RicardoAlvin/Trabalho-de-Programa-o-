import javax.swing.*;
import java.awt.*;

public class LojaDocesBorderLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Venda de doces");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(220, 220, 230));

        // Título
        JLabel titulo = new JLabel("Loja de doces");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titulo, BorderLayout.NORTH);

        // Painel central com Grid
        JPanel painelProdutos = new JPanel(new GridLayout(3, 3, 10, 10));
        painelProdutos.setBackground(new Color(220, 220, 230));

        // Adiciona 3 produtos (imagem, preço, campo de quantidade)
        adicionarProduto(painelProdutos, "brigadeiro.jpg", "R$ 10,00");
        adicionarProduto(painelProdutos, "cookie.jpg", "R$ 15,00");
        adicionarProduto(painelProdutos, "pudim.jpg", "R$ 25,00");

        frame.add(painelProdutos, BorderLayout.CENTER);

        // Botão inferior
        JButton btnPedir = new JButton("Pedir");
        btnPedir.setBackground(Color.BLUE);
        btnPedir.setForeground(Color.WHITE);
        frame.add(btnPedir, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void adicionarProduto(JPanel painel, String caminhoImagem, String preco) {
        // Tenta carregar a imagem, ou usa um texto alternativo
        ImageIcon imagem;
        try {
            imagem = new ImageIcon(caminhoImagem);
            Image img = imagem.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imagem = new ImageIcon(img);
        } catch (Exception e) {
            imagem = new ImageIcon();
        }

        JLabel labelImagem = new JLabel(imagem);
        JLabel labelPreco = new JLabel(preco);
        JTextField campoQtd = new JTextField("1", 5);

        painel.add(labelImagem);
        painel.add(labelPreco);
        painel.add(campoQtd);
    }
}
