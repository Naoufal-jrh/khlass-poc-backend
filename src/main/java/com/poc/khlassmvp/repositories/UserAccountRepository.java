package com.poc.khlassmvp.repositories;

import com.poc.khlassmvp.entities.CategoryEntity;
import com.poc.khlassmvp.entities.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {
}
