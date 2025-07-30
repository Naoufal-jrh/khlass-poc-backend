package com.poc.khlassmvp.services.impl;

import com.poc.khlassmvp.dtos.UserAccountDto;
import com.poc.khlassmvp.mapper.impl.UserAccountMapper;
import com.poc.khlassmvp.repositories.UserAccountRepository;
import com.poc.khlassmvp.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UserAccountDto getUserAccount(Long id) {
        return userAccountRepository.findById(id).map(userAccountMapper::toDto).orElse(null);
    }

    @Override
    public UserAccountDto addUserAccount(UserAccountDto userAccountDto) {
        return userAccountMapper.toDto(userAccountRepository.save(userAccountMapper.toEntity(userAccountDto)));
    }

    @Override
    public UserAccountDto updateUserAccount(UserAccountDto userAccountDto) {
        return userAccountMapper.toDto(userAccountRepository.save(userAccountMapper.toEntity(userAccountDto)));
    }

    @Override
    public void deleteUserAccount(Long id) {
        userAccountRepository.deleteById(id);
    }
}
