package main.java.brugerautorisation.transport.soap;

import main.java.brugerautorisation.data.Diverse;
import main.java.brugerautorisation.data.Bruger;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author j
 */
public class Brugeradminklient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:9901/brugeradmin?wsdl");
        //URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
        QName qname = new QName("http://soap.transport.main.java.brugerautorisation/", "BrugeradminImplService");
        Service service = Service.create(url, qname);
        Brugeradmin ba = service.getPort(Brugeradmin.class);

        Bruger off = ba.hentBrugerOffentligt("s123456");
        System.out.println("Fik offentlige data " + Diverse.toString(off));

        //ba.sendGlemtAdgangskodeEmail("s123456", "Dette er en test, husk at skifte kode");
        //ba.ændrAdgangskode("s123456", "kode1xyz", "kode1xyz");
        ba.setEkstraFelt("s123456", "kode1xyz", "hobby", "Tennis og programmering"); // Skriv noget andet her
        Bruger b = ba.hentBruger("s123456", "kode1xyz");
        System.out.println("Fik bruger = " + b);
        System.out.println("Data: " + Diverse.toString(b));
        // ba.sendEmail("jacno", "xxx", "Hurra det virker!", "Jeg er så glad");

        Object ekstraFelt = ba.getEkstraFelt("s123456", "kode1xyz", "hobby");
        System.out.println("Brugerens hobby er: " + ekstraFelt);
        
        
                        
        //Test af fjern felter
        ba.fjernAlleEkstraFelt("s123456", "kode1xyz");
        b = ba.hentBruger("s123456", "kode1xyz");
        System.out.println("Fik bruger " + b);
        System.out.println("med data " + Diverse.toString(b));
        ekstraFelt = ba.getEkstraFelt("s123456", "kode1xyz", "hobby");
        System.out.println("Brugerens hobby er: " + ekstraFelt);

    }
}
