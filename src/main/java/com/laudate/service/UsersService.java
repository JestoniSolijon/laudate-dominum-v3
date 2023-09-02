package com.laudate.service;

import com.laudate.entity.Users;
import com.laudate.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void save(Users users) {
        usersRepository.save(users);
    }

}
