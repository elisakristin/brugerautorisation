package RESTServices;

import DTO.Meeting;
import DTO.UserPass;
import DTO.Virksomhed;
import Firebase.DataController;
import Firebase.FirebaseFacilitator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Path("Virksomhed")
public class VirksomhedService {

        @Context
        HttpServletRequest request;
        @Context
        HttpServletResponse response;

        FirebaseFacilitator fire = FirebaseFacilitator.getInstance();

        @GET
        @Path("/{id}")
        @Produces("application/json")
        public Virksomhed hentVirksomhed(@PathParam("id") int id){
            if (true) { // tjek om brugeren findes
                try {
                    return fire.HentVirksomhedMedId(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
            return null;
        }

        @POST
        @Path("/")
        @Produces("application/json")
        @Consumes("application/json")
        public Virksomhed tilføjVirksomhed(Virksomhed virksomhed){
        if (true) { // tjek om brugeren findes
            try {
                fire.tilføjVirksomhed(virksomhed.getVirksomhedsID(), virksomhed.getNavn());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return null;
    }


}
