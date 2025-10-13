// Figura 28.30: Person.java
// Classe Person que representa uma entrada em um catálogo de endereços.
public class Person
{
   private int addressID;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;

   // construtor sem argumento
   public Person()
   {
   } // fim do construtor sem argumentos

   // construtor
   public Person(int id, String first, String last,
      String emailAddress, String phone)
   {
      setAddressID(id);
      setFirstName (first);
      setLastName(last);
      setEmail(emailAddress);
      setPhoneNumber(phone); // Corrigido para setPhoneNumber (sem espaço)
   } // fim do construtor Commission Employee de cinco argumentos

   // configura o IDdeEndereço
   public void setAddressID(int id)
   {
      addressID = id;
   } // fim do método setAddressID

   // retorna o IDde Endereço
   public int getAddressID()
   {
      return addressID;
   } // fim do método getAddressID

   // estabelece o nome
   public void setFirstName(String first)
   {
      firstName = first;
   } // fim do método setFirstName

   // retorna o nome
   public String getFirstName()
   {
      return firstName;
   } // fim do método getFirstName

   // configura o sobrenome
   public void setLastName(String last)
   {
      lastName = last;
   } // fim do método setLastName

   // retorna o sobrenome
   public String getLastName()
   {
      return lastName;
   } // fim do método getLastName

   // configura o endereço de e-mail
   public void setEmail(String emailAddress)
   {
      email = emailAddress;
   } // fim do método setEmail

   // retorna o endereço de e-mail
   public String getEmail()
   {
      return email;
   } // fim do método getEmail

   // configura o número de telefone
   public void setPhoneNumber (String phone) // Corrigido para setPhoneNumber (sem espaço)
   {
      phoneNumber = phone;
   } // fim do método setPhoneNumber

   // retorna o número de telefone
   public String getPhoneNumber() // Corrigido para getPhoneNumber (sem espaço)
   {
      return phoneNumber; // Corrigido: Adicionado o retorno da variável
   } // fim do método getPhoneNumber
} // fim da classe Person