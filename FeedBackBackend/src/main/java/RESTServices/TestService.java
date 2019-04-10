package RESTServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestService {

    @GET
    public String getTest(){
        return "RESTServices.Hello World";
    }

    @Path("json")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Hello getHelloJson(){
        return new Hello();
    }
}
