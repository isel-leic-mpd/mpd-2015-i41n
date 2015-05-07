import java.util.List;
import java.util.Arrays;

class Trader{
    public final String name;
    public  final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}

class Transaction{
    public final Trader trader;
    public  final int year;
    public  final int value;
    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public String toString(){
        return "{" + this.trader + ", " + "year: "+this.year+", " + "value:" + this.value +"}";
    }
}

public class Transactions{
    public static List<Transaction> init(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        return Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
    }
    public static void main(String [] args){
        // 1. Find all transactions in the year 2011 and sort them by value (small to high).
        // 2. What are all the unique cities where the traders work?
        // 3. Find all traders from Cambridge and sort them by name.
        // 4. Return a string of all traders’ names sorted alphabetically.
        // 5. Are any traders based in Milan?
        // 6. Print all transactions’ values from the traders living in Cambridge.
        // 7. What’s the highest value of all the transactions?
        // 8. Find the transaction with the smallest value. 
        
    }
}