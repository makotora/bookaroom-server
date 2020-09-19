package com.bookaroom.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordEncryptionService implements PasswordEncoder
{
    private static final BCryptPasswordEncoder bCryptPassowrdEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword)
    {
        return bCryptPassowrdEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword)
    {
        return bCryptPassowrdEncoder.matches(rawPassword, encodedPassword);
    }

}
