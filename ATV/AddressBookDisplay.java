// Figura 28.32: AddressBookDisplay.java
// Um catálogo de endereços simples.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class AddressBookDisplay extends JFrame
{
   private Person currentEntry;
   private PersonQueries personQueries;
   private List<Person> results;
   private int numberOfEntries = 0;
   private int currentEntryIndex;

   private JButton browseButton;
   private JLabel emailLabel;
   private JTextField emailTextField;
   private JLabel firstNameLabel;
   private JTextField firstNameTextField;
   private JLabel idLabel;
   private JTextField idTextField;
   private JTextField indexTextField;
   private JLabel lastNameLabel;
   private JTextField lastNameTextField;
   private JTextField maxTextField;
   private JButton nextButton;
   private JLabel ofLabel;
   private JLabel phoneLabel;
   private JTextField phoneTextField;
   private JButton previousButton;
   private JButton queryButton;
   private JLabel queryLabel;
   private JPanel queryPanel;
   private JPanel navigatePanel;
   private JPanel displayPanel;
   private JTextField queryTextField;
   private JButton insertButton;

   // construtor sem argumento
   public AddressBookDisplay()
   {
      super( "Address Book" );

      // estabelece a conexão de banco de dados e configura PreparedStatements
      personQueries = new PersonQueries();

      // cria a GUI
      navigatePanel = new JPanel();
      previousButton = new JButton();
      indexTextField = new JTextField( 2 );
      ofLabel = new JLabel();
      maxTextField = new JTextField( 2 );
      nextButton = new JButton();
      displayPanel = new JPanel();
      idLabel = new JLabel();
      idTextField = new JTextField( 10 );
      firstNameLabel = new JLabel();
      firstNameTextField = new JTextField( 10 );
      lastNameLabel = new JLabel();
      lastNameTextField = new JTextField( 10 );
      emailLabel = new JLabel();
      emailTextField = new JTextField( 10 );
      phoneLabel = new JLabel();
      phoneTextField = new JTextField( 10 );
      insertButton = new JButton();
      queryPanel = new JPanel();
      queryLabel = new JLabel();
      queryTextField = new JTextField( 10 );
      queryButton = new JButton();
      browseButton = new JButton();

      setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );

      // configura painel de navegação
      navigatePanel.setLayout( new BoxLayout( navigatePanel,
         BoxLayout.X_AXIS ) );
      navigatePanel.setBorder( BorderFactory.createTitledBorder(
         "Navegação" ) );

      previousButton.setText( "Anterior" );
      navigatePanel.add( previousButton );
      navigatePanel.add( Box.createHorizontalStrut( 5 ) );
      navigatePanel.add( indexTextField );
      navigatePanel.add( Box.createHorizontalStrut( 5 ) );
      ofLabel.setText( "de" );
      navigatePanel.add( ofLabel );
      navigatePanel.add( Box.createHorizontalStrut( 5 ) );
      navigatePanel.add( maxTextField );
      navigatePanel.add( Box.createHorizontalStrut( 5 ) );
      nextButton.setText( "Próximo" );
      navigatePanel.add( nextButton );
      add( navigatePanel );

      // configura campos ocultos
      idTextField.setEnabled( false );
      maxTextField.setEnabled( false );
      indexTextField.setEnabled( false );

      // configura painel de exibição
      displayPanel.setLayout( new GridLayout( 5, 2, 4, 4 ) );

      idLabel.setText( "ID Endereço:" );
      displayPanel.add( idLabel );
      displayPanel.add( idTextField );
      firstNameLabel.setText( "Nome:" );
      displayPanel.add( firstNameLabel );
      displayPanel.add( firstNameTextField );
      lastNameLabel.setText( "Sobrenome:" );
      displayPanel.add( lastNameLabel );
      displayPanel.add( lastNameTextField );
      emailLabel.setText( "Email:" );
      displayPanel.add( emailLabel );
      displayPanel.add( emailTextField );
      phoneLabel.setText( "Telefone:" );
      displayPanel.add( phoneLabel );
      displayPanel.add( phoneTextField );
      add( displayPanel );

      // configura painel de consulta
      queryPanel.setLayout( new BoxLayout( queryPanel, BoxLayout.X_AXIS ) );
      queryPanel.setBorder( BorderFactory.createTitledBorder(
         "Consulta por Sobrenome" ) );
      queryLabel.setText( "Sobrenome:" );
      queryPanel.add( queryLabel );
      queryPanel.add( Box.createHorizontalStrut( 5 ) );
      queryPanel.add( queryTextField );
      queryPanel.add( Box.createHorizontalStrut( 5 ) );
      queryButton.setText( "Consulta" );
      queryPanel.add( queryButton );
      queryPanel.add( Box.createHorizontalStrut( 5 ) );
      browseButton.setText( "Procurar Todas as Entradas" );
      queryPanel.add( browseButton );
      add( queryPanel );

      // configura botão de inserção
      insertButton.setText( "Inserir Nova Entrada" );
      add( insertButton );

      // anexa ouvintes
      nextButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               nextButtonActionPerformed( e );
            } // fim do método actionPerformed
         } // fim da classe interna anônima
      ); // fim da chamada para addActionListener

      previousButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               previousButtonActionPerformed( e );
            } // fim do método actionPerformed
         } // fim da classe interna anônima
      ); // fim da chamada para addActionListener

      browseButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               browseButtonActionPerformed( e );
            } // fim do método actionPerformed
         } // fim da classe interna anônima
      ); // fim da chamada para addActionListener

      queryButton.addActionListener(
         new ActionListener()
            {
               public void actionPerformed( ActionEvent e )
               {
                  queryButtonActionPerformed( e );
               } // fim do método actionPerformed
            } // fim da classe interna anônima
      ); // fim da chamada para addActionListener

      insertButton.addActionListener(
         new ActionListener()
         {
            public void actionPerformed( ActionEvent e )
            {
               insertButtonActionPerformed( e );
            } // fim do método actionPerformed
         } // fim da classe interna anônima
      ); // fim da chamada para addActionListener

      addWindowListener(
         new WindowAdapter()
         {
            public void windowClosing( WindowEvent e )
            {
               personQueries.close(); // fecha conexão de banco de dados
               System.exit( 0 );
            } // fim do método windowClosing
         } // fim da classe interna anônima
      ); // fim da chamada para addWindowListener

      setSize( 400, 300 );
      setResizable( false );
      setVisible( true );

      browseButtonActionPerformed( null );
   } // fim do construtor AddressBookDisplay

   // lida com eventos onde o usuário clica no botão próximo
   private void nextButtonActionPerformed( ActionEvent e )
   {
      // determina se há mais resultados
      if ( currentEntryIndex < numberOfEntries - 1 )
      {
         currentEntryIndex++;
         currentEntry = results.get( currentEntryIndex );
         displayEntry();
      } // fim do if
      else
         JOptionPane.showMessageDialog( this,
            "Nenhuma entrada restante.", "Fim das entradas",
            JOptionPane.PLAIN_MESSAGE );
   } // fim do método nextButtonActionPerformed

   // lida com eventos onde o usuário clica no botão anterior
   private void previousButtonActionPerformed( ActionEvent e )
   {
      // determina se há entradas anteriores
      if ( currentEntryIndex > 0 )
      {
         currentEntryIndex--;
         currentEntry = results.get( currentEntryIndex );
         displayEntry();
      } // fim do if
      else
         JOptionPane.showMessageDialog( this,
            "Nenhuma entrada restante.", "Fim das entradas",
            JOptionPane.PLAIN_MESSAGE );
   } // fim do método previousButtonActionPerformed

   // lida com eventos onde o usuário clica no botão procurar todas as entradas
   private void browseButtonActionPerformed( ActionEvent e )
   {
      try
      {
         results = personQueries.getAllPeople();
         numberOfEntries = results.size();

         if ( numberOfEntries != 0 )
         {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            displayEntry();
         } // fim do if
         else
            insertButtonActionPerformed( e );
      } // fim do try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // fim do catch
   } // fim do método browseButtonActionPerformed

   // lida com eventos onde o usuário clica no botão de consulta
   private void queryButtonActionPerformed( ActionEvent e )
   {
      try
      {
         results = personQueries.getPeopleByLastName(
            queryTextField.getText() );
         numberOfEntries = results.size();

         if ( numberOfEntries != 0 )
         {
            currentEntryIndex = 0;
            currentEntry = results.get( currentEntryIndex );
            displayEntry();
         } // fim do if
         else
            JOptionPane.showMessageDialog( this,
               "Sobrenome não encontrado.", "Resultado da Consulta",
               JOptionPane.PLAIN_MESSAGE );
      } // fim do try
      catch ( Exception exception )
      {
         exception.printStackTrace();
      } // fim do catch
      finally
      {
         queryTextField.setText( "" );
      } // fim do finally
   } // fim do método queryButtonActionPerformed

   // lida com eventos onde o usuário clica no botão de inserção
   private void insertButtonActionPerformed( ActionEvent e )
   {
      String[] newPersonInfo = getNewPerson();
      
      // Corrigido o erro de compilação: addPerson espera 4 Strings separadas.
      int result = personQueries.addPerson( 
         newPersonInfo[0], newPersonInfo[1], newPersonInfo[2], newPersonInfo[3] );

      if ( result == 1 )
         JOptionPane.showMessageDialog( this,
            "Pessoa adicionada!", "Pessoa Adicionada",
            JOptionPane.PLAIN_MESSAGE );
      else
         JOptionPane.showMessageDialog( this,
            "Pessoa não adicionada!", "Pessoa Não Adicionada",
            JOptionPane.PLAIN_MESSAGE );

      browseButtonActionPerformed( e ); // carrega próxima pessoa

      clearFields();
   } // fim do método insertButtonActionPerformed

   // limpa campos de texto
   private void clearFields()
   {
      idTextField.setText( "" );
      firstNameTextField.setText( "" );
      lastNameTextField.setText( "" );
      emailTextField.setText( "" );
      phoneTextField.setText( "" );
   } // fim do método clearFields

   // exibe o conteúdo da entrada atual nos campos de texto
   private void displayEntry()
   {
      idTextField.setText(
         String.valueOf( currentEntry.getAddressID() ) );
      firstNameTextField.setText( currentEntry.getFirstName() );
      lastNameTextField.setText( currentEntry.getLastName() );
      emailTextField.setText( currentEntry.getEmail() );
      phoneTextField.setText( currentEntry.getPhoneNumber() );
      maxTextField.setText( String.valueOf( numberOfEntries ) );
      indexTextField.setText( String.valueOf( currentEntryIndex + 1 ) );
   } // fim do método displayEntry

   // obtém informações da pessoa a partir dos campos de texto
   private String[] getNewPerson()
   {
      String[] info = new String[ 4 ];

      info[ 0 ] = firstNameTextField.getText();
      info[ 1 ] = lastNameTextField.getText();
      info[ 2 ] = emailTextField.getText();
      info[ 3 ] = phoneTextField.getText();

      return info;
   } // fim do método getNewPerson
} // fim da classe AddressBookDisplay