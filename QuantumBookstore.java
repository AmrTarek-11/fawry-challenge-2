
package quantum.bookstore;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
 //* @author amrta



 abstract class Book {
    public String isbn;
    public String title;
    public int year;
    public double price;
    
    
    public Book (String isbn , String title , int year, double price){
    this.isbn =isbn;
    this.title=title ;
    this.year =year;
    this.price = price;
}
     public abstract boolean isForSale();
}
  class PaperBook extends Book{
public int stock ;

public PaperBook(String isbn , String title , int year, double price , int stock){
    super(isbn, title, year, price);
    this.stock =stock;
}
public boolean isForSale() { return true; }
 

}

class EBook extends Book {
    public String fileType;
    
    public EBook (String isbn , String title , int year, double price, String fileType){
        super(isbn, title, year, price);
        this.fileType =fileType ;
    }
    public boolean isForSale() { return true; }

      public void send(String email) {
        System.out.println("Quantum book store: EBook sent to " + email);
    }
}

class ShowcaseORdemobook extends Book {
    public ShowcaseORdemobook (String isbn , String title , int year, double price){
       super(isbn, title, year, price);
    }
        public boolean isForSale() { return false; }

}
class inventory {


}

public class QuantumBookstore  {
  public static List<Book> inventory = new ArrayList<>();
  
  
   public static  void addBook(String isbn , String title , int year, double price , int stock) {
       
      Book b = new PaperBook(isbn, title, year, price, stock);
      inventory.add(b);
       
   }
   public static  void addBook (String isbn , String title , int year, double price, String fileType){
        Book b = new EBook(isbn, title, year, price,fileType);
      inventory.add(b);     
   }
   public static  void addBook (String isbn , String title , int year, double price){
        Book b = new ShowcaseORdemobook (isbn, title, year, price);
      inventory.add(b);     
   }
   
   public static  ArrayList<Book> removeBook (int y )
   {
     ArrayList<Book> removedbooks = new ArrayList<>();
   for(Book b : inventory ){
       int yr = Year.now().getValue();
       if(yr - b.year > y){
           removedbooks.add(b);
          
       }
   }
   int b2 = 0 ;
   int n = inventory.size();
   for( int i = 0 ; i < n;i++) {
       if(inventory.get(i).isbn.equals(removedbooks.get(b2).isbn)){
           inventory.remove(i);
           n--;
           i--;
           b2++;
           if(b2 == removedbooks.size()){
               break ;
           }
       }
   }
return removedbooks ;
   }
    public static  double purchase (String isbn , int quantity ,String email , String address){
        boolean f = false ;
        Book bf = null  ;
    for(Book b : inventory ){
if(b.isbn.equals(isbn))  {
     f = true ;
     bf = b ;
     break ;
}  

    
    }
    if(f & bf != null){
        if (bf instanceof PaperBook ){
             PaperBook bf2 = (PaperBook)bf ;
             if(bf2.stock <quantity){
                 System.out.println("error not enough stock");
                 return 0 ;
             }
             bf2.stock -= quantity;
             System.out.println("purchase is done , sent to shipping services");
             return bf2.price * quantity ;
        }
        else if (bf instanceof EBook){
          System.out.println("purchase is done , sent to mail services");
          return ((EBook) bf).price;
        }
        else if (bf instanceof ShowcaseORdemobook ){
            System.out.println("this book is not  for sale");
            return 0 ;
        }
        else {
            return 0 ;
        }
    }
    else {
        System.out.println("book not found ");
        return 0 ;
    }
    }
    
      
    public static void main(String[] args) {
        addBook("999", "chemistry", 2004, 100, 20);
        addBook("888", "physics", 2010, 300, 60);
        addBook("777", "math", 2023, 120, "pdf");
        addBook("666", "arabic", 2020, 80, "pdf");
        
        purchase("666", 10, "amr@gmail.com", "cairo");
        purchase("999", 30, "amr@gmail.com","cairo");
       ArrayList<Book> r = removeBook(10);
      purchase("999", 3, "amr@gmail.com","cairo");
        addBook("111", "english", 2007, 40);
        purchase("111", 3, "amrtarek@gmail.com", "cairo");
       
    }
    

}