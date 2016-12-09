
import Kommunikasjon.Klient;
import Kommunikasjon.Server;

import java.io.IOException;

public class StartSpill {

    public static void main(String[] args) throws IOException {
        new Server(22222);
        new Klient("localhost", 2222);
    }

}