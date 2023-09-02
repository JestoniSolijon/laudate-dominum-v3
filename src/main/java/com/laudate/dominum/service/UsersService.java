package com.laudate.dominum.service;

import com.laudate.dominum.entity.Users;
import com.laudate.dominum.repository.UsersRepository;
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
