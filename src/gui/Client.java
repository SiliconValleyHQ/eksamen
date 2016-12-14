package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Created by mariuswetterlin on 14.12.2016.
 */
public class Client {

	//if we do it like this I think the grid will be unavailable in net.client instance
	public static Grid grid = null;

	public static void main(String[] args) {
		net.Client client = new net.Client(50001); //now we will have client running . It is a thread and from now lives it's life "free" from this main() method
		//from this part we have to build our gui with a frame to draw on
		JFrame frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Grid of size 400x400 pixels. Behind it will be 8x8=64 rectangles
		gui.Client.grid = new Grid(400, 400, 8, 8); //like that? that would be a 8by8 grid

		grid.addMouseListener(new GridMouseListener());//now when the grid is clicked the rectangle will be highlighted
		//we could add some logic here: click the rectangle from where to take a piece and click another rectangle where to put a piece.
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);


		panel.add(new JLabel("Game board"));

		panel.add(grid);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		//now we have constructed our frame :D
		//now we need to repaint this grid on actions this is
		//we have a running thread Client that contains game information we need . how to hook up client and grid?
		//we could make a client pass messages to the
		// Server that passes the messages to both clients and push the updated boards?

		//Once the client receives any message processes it we could initiate repainting of a grid. To initiate repainting
		//two classes Grid and Client have to become coupled .
		//Client could be observable and inform Observers on the fact that the client's state has changed. To do this

		// we can use Observer, Observable pattern. Or subscriber, subscription pattern. Or just do it plain simple and

		//nasty: pass instance of a grid to the client and make client call grid.repaint() whenever client has to.
		//That coding will include some try and eval and take some time. Do you have any questions

		// we was thinking about the nasty way at first, but that would be quite heavy for the server? So an Obersever would
		// be more effective?  and easier?

		//no not heavy in our case. just the code will look coupled. here we can do anything what works even if it could be heavy on server on anywhere.
		// What do you think? What would you do? I would think. See at code and try to come up with working solution
		// that could be done in fastest way then in the way that fulfils our requirements.
	}
}