package Authentication;

import DTO.UserPass;
import Login.JWTHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Path("login")

public class loginService {


        @Context
        HttpServletRequest request;
        @Context
        HttpServletResponse response;

        @POST
        public void getLogin(String email) throws FirebaseAuthException {

            if (FirebaseAuthentication.checkOmBrugerenFindes(email)) { // tjek om brugeren findes
             System.out.println("det virker");
            } else {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        }




}
