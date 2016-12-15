package gui;

import javax.swing.*;

public class Klient {

	//if we do it like this I think the matrise will be unavailable in nettverk.client instance
	private static Matrise matrise = null;

	public static void main(String[] args) {
		nettverk.Klient klient = new nettverk.Klient(22222); //now we will have klient running . It is a thread and from now lives it's life "free" from this main() method
		//from this part we have to build our gui with a frame to draw on
		JFrame frame = new JFrame("Matrise");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Matrise of size 400x400 pixels. Behind it will be 8x8=64 rectangles
		Klient.matrise = new Matrise(400, 400, 8, 8); //like that? that would be a 8by8 matrise

		matrise.addMouseListener(new MatriseMuselytter());//now when the matrise is Trykket the rectangle will be highlighted
		//we could add some logic here: click the rectangle from where to take a piece and click another rectangle where to put a piece.
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);

		panel.add(new JLabel("Spill brett"));

		panel.add(matrise);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		//now we have constructed our frame :D
		//now we need to repaint this matrise on actions this is
		//we have a running thread Klient that contains spill information we need . how to hook up klient and matrise?
		//we could make a klient pass messages to the
		// Server that passes the messages to both clients and push the updated boards?

		//Once the klient receives any message processes it we could initiate repainting of a matrise. To initiate repainting
		//two classes Matrise and Klient have to become coupled .
		//Klient could be observable and inform Observers on the fact that the klient's state has changed. To do this

		// we can use Observer, Observable pattern. Or subscriber, subscription pattern. Or just do it plain simple and

		//nasty: pass instance of a matrise to the klient and make klient call matrise.repaint() whenever klient has to.
		//That coding will include some try and eval and take some time. Do you have any questions

		// we was thinking about the nasty way at first, but that would be quite heavy for the server? So an Obersever would
		// be more effective?  and easier?

		//no not heavy in our case. just the code will look coupled. here we can do anything what works even if it could be heavy on server on anywhere.
		// What do you think? What would you do? I would think. See at code and try to come up with working solution
		// that could be done in fastest way then in the way that fulfils our requirements.
	}

}