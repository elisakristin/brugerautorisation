package RESTServices;

import DTO.Meeting;
import DTO.UserPass;
import Firebase.DataController;
import Firebase.FirebaseFacilitator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Path("Meeting")
public class MeetingService {
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;


    FirebaseFacilitator fire = FirebaseFacilitator.getInstance();

    @GET
    @Path("/All")
    @Produces("application/json")
    public ArrayList<Meeting> getAllMeetings(UserPass userPass){
        if (true) { // tjek om brugeren findes
            try {
                return fire.getAllMeetings();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

    @GET
    @Path("/AllByUser/{userId}")
    @Produces("application/json")
    public ArrayList<Meeting> getAllMeetingsForUser(UserPass userPass, @PathParam("userId") String userId, @QueryParam("done") Boolean done){
        if (true) { // tjek om brugeren findes
            try {
                return fire.getAllMeetingsForUser(userId, done);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        throw new WebApplicationException(Response.Status.FORBIDDEN);
    }

/*
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String meetingsById(@PathParam("id") String id){
        if (true) { // tjek om brugeren findes
            try {
                return fire.getMeeting(id).getValue().toString();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }


    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String userINFO(@PathParam("id") String id){
        if (true) { // tjek om brugeren findes
            try {
                return fire.getMeeting(id).getValue().toString();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

*/

    @POST
    @Path("/Opret")
    @Consumes("application/json")
    public boolean getAllMeetings(Meeting meeting){
        if (true) { // tjek om brugeren findes
            fire.opretmode(meeting);
            System.out.println(meeting.getName());
            return true;
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }


}
