package ch.bzz.produktliste.service;

import ch.bzz.produktliste.data.UserData;
import ch.bzz.produktliste.model.User;
import ch.bzz.produktliste.util.AES256;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/*
 * services for authentication and authorization of users
 */
@Path("user")
public class UserService {
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login (
            @FormParam("username") String username,
            @FormParam("password") String password
    ){
        int httpStatus;
        User user = UserData.findUser(username, password);
        if(user == null || user.getRole() == null || user.getRole().equals("guest")) {
        httpStatus = 404;
        } else{
        httpStatus = 200;
    }
        NewCookie cookie = new NewCookie(
                "userRole",
                AES256.encrypt(user.getRole()),
                "/",
                "",             // zb gwhalin.ch
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return  response;

    }

    /*
     * logout current user
     *
     * @return Response object with guest-cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout () {
        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Logout-Cookie",
                0,
                false
        );
        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }
}