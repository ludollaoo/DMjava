

public class Main{

	public static void main(String[] args){

		Converter c1 = new Converter();
		c1.setTaux(2);
		System.out.println("10€ est équivalent à " + c1.toDollars(10) +"$");
		System.out.println("20$ est équivalent à " + c1.toEuros(20) +"€");
	}
}