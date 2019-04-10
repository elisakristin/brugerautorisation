package main.java.Client;

import main.java.brugerautorisation.transport.rmi.Brugeradmin;



import java.rmi.Naming;
import java.util.Scanner;

public class Client {


    public static void main(String[] arg) throws Exception {

        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        Scanner sc = new Scanner(System.in);
        System.out.println("Indtast brugernavn: ");

        String brugernavn = sc.next();
        sc.nextLine();

        System.out.println();
        System.out.println("Indtast adgangskode: ");

        String kode = sc.next();
        sc.nextLine();

        ba.hentBruger(brugernavn, kode);


    }
}
