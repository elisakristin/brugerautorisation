package Tests;

import DTO.Meeting;
import DTO.Virksomhed;
import Firebase.FirebaseFacilitator;
//import com.google.firebase.database.DataSnapshot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class firebaseTest {

    @Test
    public void tilføjVirksomhed(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        try {
            firebaseobj.tilføjVirksomhed(1000, "TEST VirksomhedService");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void hentVirksomhed(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        try {
            try {
               Virksomhed virksomhed = firebaseobj.HentVirksomhedMedId(1000);
                System.out.println("navn: " + virksomhed.getNavn() + " ID: " + virksomhed.getVirksomhedsID());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void opretMøde(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        firebaseobj.opretmode(new Meeting("Testmødev4", "At teste?", "DTU lyngby 303A, lokale 4",28 , 4,  2019, "12:00", "12:15", "0"));
        firebaseobj.opretmode(new Meeting("Testmødev4", "At teste?", "DTU lyngby 303A, lokale 4",28 , 4,  2019, "12:00", "12:15", "1"));
        firebaseobj.opretmode(new Meeting("Testmødev4", "At teste?", "DTU lyngby 303A, lokale 4",28 , 4,  2019, "12:00", "12:15", "2"));
    }

    @Test
    public void getMeetingByID(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        try {
            try {
                Meeting meeting = firebaseobj.getMeetingByID("XeE5WwFlcgTTdngSPfDJ");
                System.out.println("navn: " + meeting.getName() + " ID: " + meeting.getMeetingID());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAllMeetings(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        try {
            try {
                ArrayList<Meeting> list = firebaseobj.getAllMeetings();
                System.out.println(list);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAllMeetingsById(){
        FirebaseFacilitator firebaseobj = FirebaseFacilitator.getInstance();
        try {
            try {
                ArrayList<Meeting> list3 = firebaseobj.getAllMeetingsForUser("0", null);
                System.out.println(list3);
                ArrayList<Meeting> list = firebaseobj.getAllMeetingsForUser("0", true);
                System.out.println(list);
                ArrayList<Meeting> list2 = firebaseobj.getAllMeetingsForUser("0", false);
                System.out.println(list2);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
