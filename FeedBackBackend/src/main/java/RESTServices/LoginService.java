package RESTServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import DTO.User;
import DTO.UserPass;
import Login.JWTHandler;


@Path("login")
public class LoginService {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	@POST
	public void getLogin(UserPass userPass){
		if (validate(userPass)) { // tjek om brugeren findes
			response.addHeader("Authorization", "Bearer " + JWTHandler.generateJwtToken(getUser(userPass.getuserName())));
		} else {
			throw new WebApplicationException(Status.FORBIDDEN);
		}
	}
	
	@POST
	@Path("validate")
	public String validateToken(String token){		
			try {
				JWTHandler.validateToken(token);
				return "Token valid!"; 
			} catch (JWTHandler.AuthException e) {
				throw new WebApplicationException("Token invalid: " + e.getMessage(), 403);
			}

		
	}
	
	

	private User getUser(String userName) {
		return new User(-1L, userName, "");
	}



	private boolean validate(UserPass userPass) {
		return "test".equals(userPass.getPassword()) && "test".equals(userPass.getPassword());
	}

}
