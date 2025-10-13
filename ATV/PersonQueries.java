// Figura 28.31: PersonQueries.java
// PreparedStatements utilizadas pelo aplicativo AddressBook.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PersonQueries
{
   private static final String URL = "jdbc:derby:AddressBook";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "mysql";

   private Connection connection = null; // gerencia a conexão
   private PreparedStatement selectAllPeople = null;
   private PreparedStatement selectPeopleByLastName = null;
   private PreparedStatement insertNewPerson = null;

   // construtor
   public PersonQueries()
   {
      try
      {
         connection =
            DriverManager.getConnection( URL, USERNAME, PASSWORD );

         // cria a consulta que seleciona todas as entradas no AddressBook
         selectAllPeople = connection.prepareStatement(
            "SELECT * FROM Addresses" );

         // cria a consulta que seleciona entradas com um sobrenome específico
         selectPeopleByLastName = connection.prepareStatement(
            "SELECT * FROM Addresses WHERE LastName = ?" );

         // cria a inserção que adiciona uma nova entrada no banco de dados
         insertNewPerson = connection.prepareStatement(
            "INSERT INTO Addresses " +
            "(FirstName, LastName, Email, PhoneNumber) " +
            "VALUES (?, ?, ?, ?)" );
      } // fim do try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         System.exit( 1 );
      } // fim do catch
   } // fim de construtor

   // seleciona todos os endereços no banco de dados
   public List<Person> getAllPeople()
   {
      List<Person> results = null;
      ResultSet resultSet = null;

      try
      {
         // executeQuery retorna ResultSet que contém entradas correspondentes
         resultSet = selectAllPeople.executeQuery();
         results = new ArrayList<Person>();

         while ( resultSet.next() )
         {
            results.add( new Person( resultSet.getInt( "addressID" ),
               resultSet.getString( "FirstName" ),
               resultSet.getString( "LastName" ),
               resultSet.getString( "Email" ),
               resultSet.getString( "PhoneNumber" ) ) );
         } // fim do while
      } // fim do try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // fim do catch
      finally
      {
         try
         {
            resultSet.close();
         } // fim do try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
            close();
         } // fim do catch
      } // fim de finally

      return results;
   } // fim do método getAllPeople

   // seleciona a pessoa pelo sobrenome
   public List<Person> getPeopleByLastName( String name )
   {
      List<Person> results = null;
      ResultSet resultSet = null;

      try
      {
         selectPeopleByLastName.setString( 1, name ); // especifica o sobrenome

         // executeQuery retorna ResultSet que contém entradas correspondentes
         resultSet = selectPeopleByLastName.executeQuery();
         results = new ArrayList<Person>();

         while ( resultSet.next() )
         {
            results.add( new Person( resultSet.getInt( "addressID" ),
               resultSet.getString( "FirstName" ),
               resultSet.getString( "LastName" ),
               resultSet.getString( "Email" ),
               resultSet.getString( "PhoneNumber" ) ) );
         } // fim do while
      } // fim do try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // fim do catch
      finally
      {
         try
         {
            resultSet.close();
         } // fim do try
         catch ( SQLException sqlException )
         {
            sqlException.printStackTrace();
            close();
         } // fim do catch
      } // fim de finally

      return results;
   } // fim do método getPeopleByLastName

   // adiciona uma entrada
   public int addPerson(
      String fname, String lname, String email, String num )
   {
      int result = 0;

      // configura parâmetros e então executa insertNewPerson
      try
      {
         insertNewPerson.setString( 1, fname );
         insertNewPerson.setString( 2, lname );
         insertNewPerson.setString( 3, email );
         insertNewPerson.setString( 4, num );

         // insere a nova entrada, retorna o número de linhas atualizadas
         result = insertNewPerson.executeUpdate();
      } // fim do try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
         close();
      } // fim do catch

      return result;
   } // fim do método addPerson

   // fecha a conexão do banco de dados
   public void close()
   {
      try
      {
         connection.close();
      } // fim do try
      catch ( SQLException sqlException )
      {
         sqlException.printStackTrace();
      } // fim do catch
   } // fim do método close
} // fim da classe PersonQueries