import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Converter extends JFrame{

	//JComponent passés en attribut pour une modification plus facile
	private double taux;
	private JLabel jlsens;
	private JTextField jtftaux, jtfdollar, jtfeuro;

	public Converter(){

		super("Convertisseur"); 							//creation du JFRrame et parametrages
		this.taux = 1;
		this.setSize(250, 125);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());					//ajout d'un Layout

		JOptionPane jopexception = new JOptionPane();		//JOptionPane qui affiche les messages d'erreur
		this.add(jopexception);

		jtfeuro = new JTextField(7);						//JTextField avec ActionLister pour les euros
		jtfeuro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertEuroToDollar();
			}
		});
		jtfdollar = new JTextField(7);						//pareil mais pour les dollars
		jtfdollar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				convertDollarToEuro();
			}
		});
		JLabel jleuro = new JLabel("€");
		JLabel jlsens = new JLabel("<=>");
		JLabel jldollar = new JLabel("$");

		JPanel jpconverter = new JPanel();					//JPanel qui contient les JComponent relatifs aux valeurs à convertir
		jpconverter.add(jtfeuro);							//Il s'agit de la partie haute du convertisseur
		jpconverter.add(jleuro);
		jpconverter.add(jlsens);
		jpconverter.add(jtfdollar);
		jpconverter.add(jldollar);

		jtftaux = new JTextField("1", 5);					//JTextField avec ActionListener pour le taux
		jtftaux.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeTaux();
				System.out.println("Le nouveau taux est de " + getTaux());
			}
		});
		JLabel jldollar2 = new JLabel("$");
		JLabel jltaux = new JLabel("TAUX: 1 € =");

		JPanel jptaux = new JPanel();						//JPanel qui contient les JComponent relatifs au taux
		jptaux.add(jltaux);									//Il s'agit de la partie centrale
		jptaux.add(jtftaux);
		jptaux.add(jldollar2);


		JButton jbquitter = new JButton("Quitter");			//JButton avec Actionlister qui quitte le programme
		jbquitter.addActionListener(new ActionListener() {  //IL s'agit de la partie basse       
		    public void actionPerformed(ActionEvent e) {
		         System.exit(0);
		    }
		}); 


		this.getContentPane().add(jpconverter, BorderLayout.NORTH); //On place les différents JPanel dans notre JFrame
		this.getContentPane().add(jptaux, BorderLayout.CENTER);
		this.getContentPane().add(jbquitter, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public double toDollars(double s){ //conversion en Dollar
		return this.taux*s;
	}

	public double toEuros(double s){ //conversion en Euro
		return s*(1/this.taux);
	}

	public void setTaux(double t) throws TauxNegatifException{ //setTaux() avec lancement d'exceptions
		if(t <= 0){
			throw new TauxNegatifException();
		}
		this.taux = t;
	}

	public double getTaux(){
		return this.taux;
	}

	public void changeTaux(){ //methode appelée par le ActionLister de jtftaux, avec gestion d'exceptions
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

	public void convertEuroToDollar(){ //methode appelée par le ActionListener de jtfeuro, avec gestion d'exceptions
		double euro = -1;
		try{
			euro = Double.parseDouble(jtfeuro.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur de format", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		double dollar = toDollars(euro);
		jtfdollar.setText(Double.toString(dollar));
		//jlsens.setText("=>");  //cette ligne lance une NullPointerException je ne sais pourquoi
								//elle aurait normalement permis de changer le sens de la fleche
	}

	public void convertDollarToEuro(){ //methode appelée par le ActionListener de jtfdollar, avec gestion d'exceptions
		double dollar = -1;
		try{
			dollar = Double.parseDouble(jtfdollar.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Erreur de format", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		double euro = toEuros(dollar);
		jtfeuro.setText(Double.toString(euro));
		//jlsens.setText("<="); //pareil ici
	}
}