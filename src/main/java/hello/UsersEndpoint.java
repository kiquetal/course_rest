package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import hello.UsersDao;
import java.util.*;
import io.spring.guides.gs_producing_web_service.GetUserRequest;
import io.spring.guides.gs_producing_web_service.GetUserResponse;
import io.spring.guides.gs_producing_web_service.ResponseUser;
import io.spring.guides.gs_producing_web_service.GetAllUserRequest;
import io.spring.guides.gs_producing_web_service.GetAllUserResponse;
import io.spring.guides.gs_producing_web_service.UsersArray;
@Endpoint
public class UsersEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

       @Autowired
       private UsersDao userDao; 

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		ResponseUser us = new ResponseUser();
                us.setStatus("OK");
      //          us.setResponse("Todo bien");
                GetUserResponse response=new GetUserResponse();
                List<User>l= userDao.getAllUsers();
                  User gdm=l.get(0);;
                 us.setResponse(gdm.getName());

                response.setResponse(us);
		return response;
	}
       
     @PayloadRoot(namespace= NAMESPACE_URI,localPart="getAllUserRequest")
     @ResponsePayload
     public GetAllUserResponse getAllUsers(@RequestPayload GetAllUserRequest request)
      {
           GetAllUserResponse g=new GetAllUserResponse();
           
           List<UsersArray> original=g.getUsers();
                   
          List<User>l= userDao.getAllUsers();
          for (User u : l)
         {
            UsersArray n=new UsersArray();
              n.setId(u.getId());
              n.setName(u.getName());
            original.add(n);

          }

          
        return g;

        }
     

  
}
 

