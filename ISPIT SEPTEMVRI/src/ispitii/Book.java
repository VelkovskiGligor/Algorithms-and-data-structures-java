package ispitii;
//Индекс на книга Задача 1 (0 / 0)
//Да се овозможи пребарување на збор или израз во индекс на книга и како
//резултат да се врати на која страна се појавува или зборот или ако е израз,
//целиот израз. Доколку зборот не постои во индексот на книгата, 
//да се испечати “Not found”. Да не се прави разлика помеѓу големи 
//и мали букви при пребарување на збор.
import java.io.*;
import java.util.NoSuchElementException;

//Доколку даден збор се повторува во повеќе изрази од индексот,
//при пребарување на тој збор, се прикажува бројот на страната која е
//последно внесена. (пример, доколку во индексот се внесе: Alan Turing, 72 Alan Ford, 44 и потоа се пребара зборот Alan, програмата треба да отпечати 44, бидејќи таа страна е последно внесена. Пребарувањата на изразите Alan Turing и Alan Ford како изрази треба да вратат 72 и 44, соодветно)

//Влез: Се внесува индексот на книгата (израз и број на страница разделени со ,) 
//се до внесување на знакот #. По знакот #, се внесува N кој означува колку зборови
//ќе се пребаруваат. Во следните редови се читаат N зборови

//Излез: За секој од внесените зборови/изрази, во нов 
//ред треба да се ипечати страницата во која се наоѓа зборот 
//(или “Not found”, доколку зборот не е во индексот на книгата).

//Делумно решение: Проблемот е делумно решен ако поминат 7 тест случаи.

//Име на класа (Јава): Book.java

//Забелешка: При реализација на задачите МОРА
//да се користат соодветни податочни структури. 
//Забрането користење на поврзани листи, низи и сл.
//input
//Mongol Invasions of Japan, 21
//diplomatic relations, 46
//Shelter for the Indigent, 29
//Vehicle storage, 166
//Soldiers, 265
//Soviet Union, 193
//Engineer-Building Workers team, 288
//Medical services, 300
//Temporary Rules, 147
//Teutonic Knights, 130
//Travel Pass, 271
//World War II, 236
//#
//6
//diplomatic relations
//Japan
//Cloud
//mongol japan
//eNgineer-building Workers team
//MEDICA
//Output:
//46
//21
//Not found
//Not found
//288
//300

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    	// Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    	// Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    	// Go prazni stekot.

    public void push (E x);
    	// Go dodava x na vrvot na stekot.

    public E pop ();
    	// Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}
interface Queue<E> {

	// Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    	// Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    	// Ja vrakja dolzinata na redicata.

    public E peek ();
    	// Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    	// Ja prazni redicata.

    public void enqueue (E x);
    	// Go dodava x na kraj od redicata.

    public E dequeue ();
    	// Go otstranuva i vrakja pochetniot element na redicata.

}
class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}
class LinkedQueue<E> implements Queue<E> {

	// Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
       	// Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
    	// Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
       	// Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
    	// Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
    	// Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
    	// Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}
class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;

    public LinkedStack () {
    	// Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty () {
    	// Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public E peek () {
    	// Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void clear () {
    	// Go prazni stekot.
        top = null;
    }

    public void push (E x) {
    	// Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
    }

    public E pop () {
    	// Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        top = top.succ;
        return topElem;
    }

}

public class Book {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	
		while(true) {
			
		String 	s=input.readLine();
			if(s.equals("#")) {
				break;
			}
			
		}

	}

}
