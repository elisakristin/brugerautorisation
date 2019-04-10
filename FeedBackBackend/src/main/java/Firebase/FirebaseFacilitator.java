package Firebase;

import DTO.Meeting;
import DTO.Virksomhed;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseFacilitator {

    private static FirebaseFacilitator single_instance = null;

    private FirebaseFacilitator() {
        try {
            init();
        } catch (IOException e) {
            System.err.println("Firebase fejlet.");
            e.printStackTrace();
        }
    }

    public static FirebaseFacilitator getInstance() {
        if (single_instance == null)
            single_instance = new FirebaseFacilitator();

        return single_instance;
    }


    public void init() throws IOException {
        FileInputStream serviceAccount = null;
        //  System.out.println("Working Directory = " +
        //         System.getProperty("user.dir"));

        //serviceAccount = new FileInputStream("KEYS/feedbackapp-2dacb-firebase-adminsdk-ud38x-3df2d22128.json");
        serviceAccount = new FileInputStream("KEYS/key.json");
//https://feedbackapp-2dacb.firebaseio.com/
        FirebaseOptions options = null;

        options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://feedbackdb-9c056.firebaseio.com")
                .build();
// https://feedback-61121.firebaseio.com
        FirebaseApp.initializeApp(options);
    }

/*
    public DataSnapshot hentAlleBrugere() throws InterruptedException {
        FirebaseDatabase FB = FirebaseDatabase.getInstance();
        // Holder til svaret fra Firebase

        MinSnapshotHolder minSnapshotHolder = new MinSnapshotHolder();

        DatabaseReference rod = FirebaseDatabase.getInstance().getReference();
        // DatabaseReference brugere = rod.child("brugere");

        final Semaphore ventPåSvar = new Semaphore(0);

        rod.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Log.d("INIT modtaget " + snapshot.getRef());
                minSnapshotHolder.snapshot = snapshot;
                ventPåSvar.release(); // væk hovedtråden
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //   Log.d("INIT fejlet " + error);
                ventPåSvar.release(); // væk hovedtråden
            }
        });
        ventPåSvar.acquire(); // Vent på callback

        // Log.d("INIT hurra, nu har vi svaret " + minSnapshotHolder.snapshot);
        return minSnapshotHolder.snapshot;
    }


    public DataSnapshot getMeeting(String id) throws InterruptedException {
        FirebaseDatabase FB = FirebaseDatabase.getInstance();

        MinSnapshotHolder minSnapshotHolder = new MinSnapshotHolder();

        DatabaseReference rod = FirebaseDatabase.getInstance().getReference();
        DatabaseReference modeID = rod.child("ModeID").child(id);

        final Semaphore ventPåSvar = new Semaphore(0);

        modeID.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Log.d("INIT modtaget " + snapshot.getRef());
                minSnapshotHolder.snapshot = snapshot;
                ventPåSvar.release(); // væk hovedtråden
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //   Log.d("INIT fejlet " + error);
                ventPåSvar.release(); // væk hovedtråden
            }
        });
        ventPåSvar.acquire(); // Vent på callback
        return minSnapshotHolder.snapshot;
    }

    public DataSnapshot userINFO(String id) throws InterruptedException {
        FirebaseDatabase FB = FirebaseDatabase.getInstance();

        MinSnapshotHolder minSnapshotHolder = new MinSnapshotHolder();

        DatabaseReference rod = FirebaseDatabase.getInstance().getReference();
        DatabaseReference modeID = rod.child("ModeID").child(id);

        final Semaphore ventPåSvar = new Semaphore(0);

        modeID.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Log.d("INIT modtaget " + snapshot.getRef());
                minSnapshotHolder.snapshot = snapshot;
                ventPåSvar.release(); // væk hovedtråden
            }

            @Override
            public void onCancelled(DatabaseError error) {
                //   Log.d("INIT fejlet " + error);
                ventPåSvar.release(); // væk hovedtråden
            }
        });
        ventPåSvar.acquire(); // Vent på callback
        return minSnapshotHolder.snapshot;
    }

    */

    public void tilføjVirksomhed(int virksomhedsID, String navn) throws InterruptedException {

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference docRef = db.collection("Virksomhed");

        List<ApiFuture<WriteResult>> futures = new ArrayList<>();

        futures.add(docRef.document(virksomhedsID+"").set(new Virksomhed(virksomhedsID, navn)));

        DocumentSnapshot document = null;
        try {
            ApiFutures.allAsList(futures).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Virksomhed HentVirksomhedMedId(int id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("Virksomhed").document(id+"");
// asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
// block on response
        DocumentSnapshot document = future.get();
        Virksomhed virksomhed = null;
        if (document.exists()) {
            // convert document to POJO
            virksomhed = document.toObject(Virksomhed.class);
            System.out.println(virksomhed.getNavn());
        } else {
            System.out.println("No such document!");
        }
    return virksomhed;
    }

    public void opretmode(Meeting meeting){
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference docRef = db.collection("Meetings");

        List<ApiFuture<WriteResult>> futures = new ArrayList<>();

       // Meeting meeting = new Meeting("Testmøde", "At teste?", "DTU lyngby 303A, lokale 4",28 , 4,  2019, "12:00", "12:15");

        futures.add(docRef.document().set(meeting));

        DocumentSnapshot document = null;
        try {
            try {
                ApiFutures.allAsList(futures).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Meeting getMeetingByID(String id) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("Meetings").document(id+"");
// asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
// block on response
        DocumentSnapshot document = future.get();

        Meeting meeting = null;
        if (document.exists()) {
            // convert document to POJO
            meeting = document.toObject(Meeting.class);
            System.out.println(meeting.getName());
        } else {
            System.out.println("No such document!");
        }

        return meeting;
    }


    public ArrayList<Meeting> getAllMeetings() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        ArrayList<Meeting> listToretun = new ArrayList<>();

        ApiFuture<QuerySnapshot> docRef = db.collection("Meetings").get();

        QuerySnapshot future = docRef.get();

        for (Iterator<QueryDocumentSnapshot> iter = future.iterator(); iter.hasNext(); ) {
            QueryDocumentSnapshot element = iter.next();

            Meeting meeting = element.toObject(Meeting.class);

            listToretun.add(meeting);
        }
        return listToretun;
    }

    public ArrayList<Meeting> getAllMeetingsForUser(String userid, Boolean done) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        ArrayList<Meeting> listToretun = new ArrayList<>();

        ApiFuture<QuerySnapshot> docRef = db.collection("Meetings").get();

        ApiFuture<QuerySnapshot> snapshotApiFuture = null;
        if(done == null) { // få alle møder fra brugeren
            snapshotApiFuture = docRef.get().getQuery().whereEqualTo("createdById", userid).get();
        }else if(done){ // få alle afholdte møder
            snapshotApiFuture = docRef.get().getQuery().whereEqualTo("createdById", userid).whereEqualTo("state", 2).get();
        }else{ // få alle ikke afholdte møder.
            snapshotApiFuture = docRef.get().getQuery().whereEqualTo("createdById", userid).whereEqualTo("state", 0).get();
        }

        QuerySnapshot data = snapshotApiFuture.get();

        for (Iterator<QueryDocumentSnapshot> iter = data.iterator(); iter.hasNext(); ) {
            QueryDocumentSnapshot element = iter.next();

            Meeting meeting = element.toObject(Meeting.class);

            listToretun.add(meeting);
        }
        return listToretun;
    }

}
// data kontainer.

class MinSnapshotHolder {
    DataSnapshot snapshot;
}