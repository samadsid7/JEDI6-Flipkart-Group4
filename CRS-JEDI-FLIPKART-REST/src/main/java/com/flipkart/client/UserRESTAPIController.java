package com.flipkart.client;

import com.flipkart.bean.User;
import com.flipkart.exceptions.AuthException;
import com.flipkart.exceptions.InvalidCredentialsException;
import com.flipkart.exceptions.UserAlreadyExistsException;
import com.flipkart.exceptions.UserNotFoundException;
import com.flipkart.validator.Authentication;
import org.json.JSONObject;

import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/user")

public class UserRESTAPIController {
    @GET
    @Path("/usersAll")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public User viewAllUsers() {
        User user=new User();
        user.setId("12");
        user.setName("qota");
        return user;
    }

//    @POST
//    @Path("/addNewUser")
//
//    @Consumes("application/json")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String createProductInJSON(@QueryParam("userId") String userId) {
//       //  User user=new User();
//       //  user.setId(userId);
//        String result = "Product created : " + userId;
//        return result;
//
//
//
//    }

    @POST
    @Path("/registration")
    @Consumes("application/json")
    @Produces(MediaType.TEXT_PLAIN)
    public javax.ws.rs.core.Response createProductInJSON(String data) throws ValidationException{
        //  User user=new User();
        //  user.setId(userId);
        JSONObject jsonObject = new JSONObject(data);
        String userId = jsonObject.getString("userId");
        String password = jsonObject.getString("password");
        String name = jsonObject.getString("name");
        String branch = jsonObject.getString("branch");
        try {
            new Authentication().register(userId , name , password , branch);
            return javax.ws.rs.core.Response.status(200).entity("Registration Success").build();
        }
        catch (UserAlreadyExistsException ex){
            return Response.status(400).entity(ex.getMessage()).build();
        }
    }




}
