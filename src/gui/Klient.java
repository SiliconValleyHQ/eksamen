package gui;

import javax.swing.*;

/**
 * Denne klassen er Klienten. Denne klienten er en av 2, og dette er klienten som inneholder GUI og selve spillet.
 * Denne setter sammen de andre visuelle klassene, og kobler seg opp mot nettverk.Klient også.
 */
public class Klient {

	//Her henter vi inn matrisen. Denne kan være statisk, fordi alle matrisene skal være like. Vi har derimot ett problem med at den er utilgjengelig for
	//nettverk.Klient
	private static Matrise matrise = null;

	public static void main(String[] args) {
		//Her henter vi inn en ny nettverk.klient og setter porten til 22222.
		//Det startes også en ny tråd her, fordi nettverk.Klient inneholder dette. Og denne lever nå sitt eget liv fra denne main metoden.
		nettverk.Klient klient = new nettverk.Klient(22222);
		//Her starter vi å bygge selve GUI vår. Dette starter vi men en frame av typen JFrame som vi kan tegne på.
		JFrame frame = new JFrame("Matrise");
		//Dette er en standard handling som skal skje når brukeren krysser ut vinduet. Da skal programmet også lukkes.
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//Her henter vi inn en ny matrise. Den skal være 400x400 pixler og skal bestå av 8 rader og 8 kolonner.
		Klient.matrise = new Matrise(400, 400, 8, 8);

		//Her legger vi til en MouseListner til matrisen. Nå når matrisen blir trykket på , vil retangelet bli uthevet.
		matrise.addMouseListener(new MatriseMuselytter());
		//Her kan den komme logikk som kan flytte en brikke fra en posisjon til en annen.
		JPanel panel = new JPanel();
		BoxLayout bl = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(bl);

		panel.add(new JLabel("Spill brett"));

		panel.add(matrise);

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		/**
		 * Nå har vi lagd rammen med innholdet.
		 * Vi spinner ut en ny tråd som jobber med og holder på informasjonen som skjer.
		 * Planen er å kunne sende informasjon til serveren som dytter informasjonen ut igjen
		 * til begge klientene som da starter en repaint av matrisen slik at brettene oppdateres.
		 * Det vi trenger for at dette skal skjer, er at Klienten må lytte etter endringer.
		 * Dette kan vi gjøre på flere måter som vi har sett på. Vi kan bruke en Observer, Subscriber eller
		 * vi kan sende matrisen til klienten og kalle en metode matrise.repaint() når klienten må.
		 * Den siste er den letteste, men ikke den peneste. Og vil være den raskeste måten å fylle kravet til oppgaven på.
		 */


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