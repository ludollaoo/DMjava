import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Converter extends JFrame{

	private double taux;
	private JTextField jtftaux, jtfdollar, jtfeuro;

	public Converter(){

		super("Convertisseur");
		this.taux = 1;
		this.setSize(250, 125);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());


		jtfeuro = new JTextField(7);
		jtfeuro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertEuroToDollar();
			}
		});
		JLabel jleuro = new JLabel("€");
		JLabel jlfleche = new JLabel("=>");
		jtfdollar = new JTextField(7);
		jtfdollar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertDollarToEuro();
			}
		});
		JLabel jldollar = new JLabel("$");

		JPanel jpconverter = new JPanel();
		jpconverter.add(jtfeuro);
		jpconverter.add(jleuro);
		jpconverter.add(jlfleche);
		jpconverter.add(jtfdollar);
		jpconverter.add(jldollar);

		JLabel jltaux = new JLabel("TAUX: 1 € =");
		jtftaux = new JTextField("1", 5);
		jtftaux.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeTaux();
				System.out.println("Le nouveau taux est de " + getTaux());
			}
		});
		JLabel jldollar2 = new JLabel("$");

		JPanel jptaux = new JPanel();
		jptaux.add(jltaux);
		jptaux.add(jtftaux);
		jptaux.add(jldollar2);


		JButton jbquitter = new JButton("Quitter");
		jbquitter.addActionListener(new ActionListener() {          
		    public void actionPerformed(ActionEvent e) {
		         System.exit(0);
		    }
		}); 


		this.getContentPane().add(jpconverter, BorderLayout.NORTH);
		this.getContentPane().add(jptaux, BorderLayout.CENTER);
		this.getContentPane().add(jbquitter, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public double toDollars(double s){
		return this.taux*s;
	}

	public double toEuros(double s){
		return s*(1/this.taux);
	}

	public void setTaux(double t){
		this.taux = t;
	}

	public double getTaux(){
		return this.taux;
	}

	public void changeTaux(){
		double newTaux = Double.parseDouble(jtftaux.getText());
		this.setTaux(newTaux);
	}

	public void convertEuroToDollar(){
		double euro = Double.parseDouble(jtfeuro.getText());
		double dollar = toDollars(euro);
		jtfdollar.setText(Double.toString(dollar));
	}

	public void convertDollarToEuro(){
		double dollar = Double.parseDouble(jtfdollar.getText());
		double euro = toEuros(dollar);
		jtfeuro.setText(Double.toString(euro));
	}
}