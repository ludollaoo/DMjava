import javax.swing.*;

public class Main{

	public static void main(String[] args){

		JTextField jtfeuro = new JTextField(7);
		JLabel jleuro = new JLabel("€");
		JLabel jlfleche = new JLabel("=>");
		JTextField jtfdollar = new JTextField(7);
		JLabel jldollar = new JLabel("$");

		JPanel jpconverter = new JPanel();
		jpconverter.add(jtfeuro);
		jpconverter.add(jleuro);
		jpconverter.add(jlfleche);
		jpconverter.add(jtfdollar);
		jpconverter.add(jldollar);

		JLabel jltaux = new JLabel("TAUX: 1 € =");
		JTextField jtftaux = new JTextField(5);
		JLabel jldollar2 = new JLabel("$");

		JPanel jptaux = new JPanel();
		jptaux.add(jltaux);
		jptaux.add(jtftaux);
		jptaux.add(jldollar2);

		jpconverter.add(jptaux);

		JButton jbquitter = new JButton("Quitter");
		JPanel jpquitter = new JPanel();
		jpquitter.add(jbquitter);
		jpconverter.add(jpquitter);
		

		JFrame frame = new JFrame("Convertisseur");
		frame.setSize(250, 125);
		//frame.getContentPane().add(jptaux);
		frame.getContentPane().add(jpconverter);
		frame.setVisible(true);
	}
}