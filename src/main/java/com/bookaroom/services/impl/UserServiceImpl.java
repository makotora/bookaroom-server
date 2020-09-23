package com.bookaroom.services.impl;

import static java.util.Collections.emptyList;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bookaroom.entities.FileUploadDTO;
import com.bookaroom.entities.UserDTO;
import com.bookaroom.enums.UserRole;
import com.bookaroom.exceptions.EntityNotFoundException;
import com.bookaroom.exceptions.OldPasswordNotMatchException;
import com.bookaroom.exceptions.ProvisioningException;
import com.bookaroom.exceptions.ServiceException;
import com.bookaroom.exceptions.UserNotAuthenticatedException;
import com.bookaroom.exceptions.UserNotAuthorizedException;
import com.bookaroom.exceptions.UserNotFoundException;
import com.bookaroom.repositories.UserDAO;
import com.bookaroom.services.FileUploadService;
import com.bookaroom.services.UserService;
import com.bookaroom.util.Constants;

@Service("Users")
public class UserServiceImpl implements UserService
{

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPasswordEncryptionService userPasswordEncryptionService;

    @Autowired
    private FileUploadService fileUploads;

    @Override
    @Transactional
    public UserDTO create(
        String username,
        String password,
        String name,
        String surname,
        String email,
        String phone,
        UserRole userRole,
        MultipartFile userImage)
        throws ProvisioningException
    {
        validateUsername(username);

        FileUploadDTO userImageFileUpload = fileUploads.uploadFile(userImage,
                                                                   Constants.USER_PICTURES_DIRECTORY,
                                                                   username);

        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(userPasswordEncryptionService.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUserRole(userRole);
        user.setPictureFileUploadId(userImageFileUpload.getId());

        userDAO.save(user);

        return user;
    }

    private void validateUsername(String username)
        throws ProvisioningException
    {
        if (usernameExists(username)) {
            throw new ProvisioningException("Username is in use by another user");
        }

        if (username.length() > 20) {
            throw new ProvisioningException("Username must be at most 20 characters long");
        }
    }

    private boolean usernameExists(String username)
    {
        return userDAO.findByUsername(username) != null;
    }

    @Override
    public List<UserDTO> findAllUsers()
    {
        log.debug("START findAllUsers");
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public UserDTO findById(Long id)
        throws UserNotFoundException
    {
        UserDTO user = userDAO.findById(id);
        if (user == null)
            throw new UserNotFoundException();
        else
            return user;

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException
    {
        UserDTO myUser = this.userDAO.findByUsername(username);

        if (myUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(myUser.getUsername(), myUser.getPassword(), emptyList());
    }

    @Override
    @Transactional
    public UserDTO changePassword(String username, String oldPassword, String newPassword)
        throws ServiceException
    {
        log.info("START changePassword");
        UserDTO user = userDAO.findByUsername(username);
        if (user == null)
            throw new EntityNotFoundException();

        if (!userPasswordEncryptionService.matches(oldPassword, user.getPassword())) {
            throw new OldPasswordNotMatchException();
        }
        user.setPassword(userPasswordEncryptionService.encode(newPassword));
        return user;
    }

    @Override
    public UserDTO findByUsername(String username)
        throws UserNotFoundException
    {
        UserDTO user = userDAO.findByUsername(username);

        if (user == null)
            throw new UserNotFoundException();

        return user;
    }

    @Override
    public UserDTO findAndAuthorizeById(Long id, Set<UserRole> acceptableRoles)
        throws UserNotFoundException, UserNotAuthorizedException
    {
        UserDTO user = findById(id);

        authorizeUser(user, acceptableRoles);

        return user;
    }

    @Override
    public UserDTO findAndAuthorizeByPrincipal(Principal principal, Set<UserRole> acceptableRoles)
        throws UserNotAuthenticatedException, UserNotFoundException, UserNotAuthorizedException
    {
        UserDTO user = findByPrincipal(principal);

        authorizeUser(user, acceptableRoles);

        return user;
    }

    private void authorizeUser(UserDTO user, Set<UserRole> acceptableRoles)
        throws UserNotAuthorizedException
    {
        if (!acceptableRoles.contains(user.getUserRole())) {
            throw new UserNotAuthorizedException();
        }
    }

    @Override
    @Transactional
    public UserDTO setListingId(Long userId, Long listingId)
        throws UserNotFoundException
    {
        UserDTO user = findById(userId);

        user.setListingId(listingId);
        userDAO.saveAndFlush(user);

        return user;
    }

    @Override
    public UserDTO findByPrincipal(Principal principal)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        if (principal == null) {
            throw new UserNotAuthenticatedException();
        }

        return findByUsername(principal.getName());
    }

    @Override
    public boolean userHasListing(Principal principal)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = findByPrincipal(principal);

        return user.getListingId() != null;
    }

    @Override
    public boolean userIsHost(Principal principal)
        throws UserNotFoundException, UserNotAuthenticatedException
    {
        UserDTO user = findByPrincipal(principal);

        return user.getUserRole() == UserRole.Host;
    }

}
