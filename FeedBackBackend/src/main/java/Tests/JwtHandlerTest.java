package Tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DTO.User;
import Login.JWTHandler;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.testng.Assert.fail;

public class JwtHandlerTest {

	@Test
	public void testGenerateJwtToken() {
		String token = JWTHandler.generateJwtToken(new User(-1L,"test","testpass"));
		System.out.println(token);

		try{
		JWTHandler.validateToken(token);
		} catch (Exception e ){
			fail();
		}
		
		
	}

	@Test
	public void testValidateToken() {
		int i = 0;
		do {
			
			try {
				String token = JWTHandler.generateJwtToken(new User(-1L,"test","testpass"));
				List<Character> cList = new ArrayList<Character>();
				for(char c : token.toCharArray()) {
				    cList.add(c);
				}
				Collections.shuffle(cList);
				token = "";
				for (Character character : cList) {
					token+=character;
				} 
				JWTHandler.validateToken(token);
				fail();
			} catch (Exception e) {
			
			}
			
			
		} while (i++<10000);
	}

}
