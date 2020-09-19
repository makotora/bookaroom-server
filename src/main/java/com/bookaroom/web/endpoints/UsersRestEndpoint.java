package com.bookaroom.web.endpoints;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.EntityNotFoundException;
import com.bookaroom.exceptions.OldPasswordNotMatchException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.ServiceException;
import com.bookaroom.services.UserService;
import com.bookaroom.web.dto.ActionResponse;
import com.bookaroom.web.dto.ChangePasswordRequest;

@RestController
@RequestMapping("users")
public class UsersRestEndpoint
{

    @Autowired
    private UserService userService;

    SecurityContextHolder securityContextHolder;

    @RequestMapping(
        value = "/register",
        method = RequestMethod.POST,
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ActionResponse register(
        @RequestPart("username") String username,
        @RequestPart("password") String password,
        @RequestPart("name") String name,
        @RequestPart("surname") String surname,
        @RequestPart("email") String email,
        @RequestPart("phone") String phone,
        @RequestPart("userRole") String userRoleStr,
        @RequestParam("userImage") MultipartFile userImage)
    {

        System.out.println("got request " + username);

        UserRole userRole = null;
        try {
            userRole = UserRole.valueOf(userRoleStr);
        }
        catch (Exception e) {
            return new ActionResponse(false, "Invalid user role");
        }

        try {
            userService.create(username, password, name, surname, email, phone, userRole, userImage);
        }
        catch (ProvisioningException e) {
            e.printStackTrace();
            return new ActionResponse(false, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ActionResponse(false, "Internal error");
        }

        return new ActionResponse(true, "User registration complete");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ActionResponse changePassword(Principal principal, @RequestBody ChangePasswordRequest request)
    {
        if (principal != null) {
            String username = principal.getName();

            if (username != null) {
                try {
                    userService.changePassword(username, request.getOldPassword(), request.getNewPassword());
                }
                catch (EntityNotFoundException e) {
                    new ActionResponse(false, "Not authenticated");
                    e.printStackTrace();
                }
                catch (OldPasswordNotMatchException e) {
                    new ActionResponse(false, "Old password not matching");
                    e.printStackTrace();
                }
                catch (ServiceException e) {
                    new ActionResponse(false, "Unknown exception");
                    e.printStackTrace();
                }
            }
        }

        return new ActionResponse(true, "Password changed successfully");
    }

}
