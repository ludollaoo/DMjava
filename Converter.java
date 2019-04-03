import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Converter extends JFrame{

	private double taux;
	private JLabel jlsens;
	private JTextField jtftaux, jtfdollar, jtfeuro;

	public Converter(){

		super("Convertisseur");
		this.taux = 1;
		this.setSize(250, 125);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JOptionPane jopexception = new JOptionPane();
		this.add(jopexception);

		jtfeuro = new JTextField(7);
		jtfeuro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertEuroToDollar();
			}
		});
		JLabel jleuro = new JLabel("€");
		JLabel jlsens = new JLabel("<=>");
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
		jpconverter.add(jlsens);
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

	public void setTaux(double t) throws TauxNegatifException{
		if(t <= 0){
			throw new TauxNegatifException();
		}
		this.taux = t;
	}

	public double getTaux(){
		return this.taux;
	}

	public void changeTaux(){
		double newTaux = 1;
		try{
			newTaux = Double.parseDouble(jtftaux.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur de format", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		try{
			this.setTaux(newTaux);
		}catch(TauxNegatifException e){
			JOptionPane.showMessageDialog(null, "Taux négatif", "Erreur", JOptionPane.ERROR_MESSAGE); 
		}
	}

	public void convertEuroToDollar(){
		double euro = -1;
		try{
			euro = Double.parseDouble(jtfeuro.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur de format", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		double dollar = toDollars(euro);
		jtfdollar.setText(Double.toString(dollar));
		//jlsens.setText("=>");
	}

	public void convertDollarToEuro(){
		double dollar = -1;
		try{
			dollar = Double.parseDouble(jtfdollar.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur de format", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		double euro = toEuros(dollar);
		jtfeuro.setText(Double.toString(euro));
		//jlsens.setText("<=");
	}
}