package com.bookaroom.services;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.ServiceException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotAuthorizedException;
import com.bookaroom.exceptions.UserNotFoundException;

public interface UserService extends UserDetailsService
{
    public UserDTO create(
        String username,
        String password,
        String name,
        String surname,
        String email,
        String phone,
        UserRole userRole,
        MultipartFile userImage)
        throws ProvisioningException;

    public UserDTO setListingId(Long userId, Long listingId)
        throws UserNotFoundException;

    public List<UserDTO> findAllUsers();

    public UserDTO findById(Long id)
        throws UserNotFoundException;

    public UserDTO findByUsername(String username)
        throws UserNotFoundException;

    UserDTO findAndAuthorizeById(Long id, Set<UserRole> acceptableRoles)
        throws UserNotFoundException, UserNotAuthorizedException;

    UserDTO findAndAuthorizeByPrincipal(Principal principal, Set<UserRole> acceptableRoles)
        throws UserNotAuthenticatedException, UserNotFoundException, UserNotAuthorizedException;

    public UserDTO changePassword(String username, String oldPassword, String newPassword)
        throws ServiceException;

    public UserDTO findByPrincipal(Principal principal)
        throws UserNotFoundException, UserNotAuthenticatedException;

}
