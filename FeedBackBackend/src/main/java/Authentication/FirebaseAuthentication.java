package Authentication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseAuthentication {

    private static final String UID = "hansen.dk@hotmail.com";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, FirebaseAuthException {
        FileInputStream serviceAccount = null;

        serviceAccount = new FileInputStream("KEYS/feedbackapp-2dacb-firebase-adminsdk-ud38x-503d127add.json");

        FirebaseOptions options = null;

        options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
               // .setDatabaseUrl("https://feedbackapp-2dacb.firebaseio.com/")
              //  .setServiceAccountId("my-client-id@my-project-id.iam.gserviceaccount.com")
                .build();

        FirebaseApp.initializeApp(options);


        // laver token her f
        Map<String,Object> additionalClaims = new HashMap<String,Object>();
        additionalClaims.put("premiumAccount",true);


        String customToken = FirebaseAuth.getInstance().createCustomTokenAsync(UID ,additionalClaims ).get();
        System.out.println( "test "+customToken);


        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(UID);
        // See the UserRecord reference doc for the contents of userRecord.
        System.out.println("Successfully fetched user data: " + userRecord.getEmail());




        // to test metoder
        if(checkOmBrugerenFindes(UID)){
            System.out.println("ok");
        }

        getlist();



                // en måde til at oprette bruger i systemet,
       /* UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail("user@example.com")
                .setEmailVerified(false)
                .setPassword("secretPassword")
                .setPhoneNumber("+11234567890")
                .setDisplayName("John Doe")
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());
        */


    }


                // checker om brugeren står i systemet via Email
public  static boolean checkOmBrugerenFindes (String email) throws FirebaseAuthException {

    UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
    if (userRecord.getEmail().equals(email)) {
        System.out.println("brugeren findes");
    } else {
        System.out.println("User findes ikke d: ");
    }
    return true;
}



        // henter en liste over alle brugere i systemet.
    public  static void getlist() throws FirebaseAuthException {
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (ExportedUserRecord user : page.getValues()) {
                System.out.println("User" +user.getEmail());
            }
            page = page.getNextPage();
        }

    }
}