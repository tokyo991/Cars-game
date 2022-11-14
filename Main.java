import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame ("Java F1");
		
		//when you press the exit key, this will close the window
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setSize(1000,600);
		f.add(new Road());
		f.setVisible(true);
	}

}
