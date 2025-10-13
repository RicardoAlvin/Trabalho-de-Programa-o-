// Figura 28.33: AddressBook.java
// Testa a classe AddressBookDisplay.
import javax.swing.JFrame;

public class main
{
   public static void main( String args[] )
   {
      // cria novo AddressBookDisplay
      AddressBookDisplay display = new AddressBookDisplay();
      // Define que, ao tentar fechar a janela (o 'X'), nada acontece.
      // O fechamento real é tratado pelo WindowListener dentro da classe AddressBookDisplay.
      display.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE ); 
   } // fim de main
} // fim da classe AddressBook