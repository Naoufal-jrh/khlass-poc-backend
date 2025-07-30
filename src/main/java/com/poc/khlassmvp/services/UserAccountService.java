package com.poc.khlassmvp.services;

import com.poc.khlassmvp.dtos.UserAccountDto;

public interface UserAccountService {
    UserAccountDto getUserAccount(Long id);
    UserAccountDto addUserAccount(UserAccountDto userAccountDto);
    UserAccountDto updateUserAccount(UserAccountDto userAccountDto);
    void deleteUserAccount(Long id);
}
