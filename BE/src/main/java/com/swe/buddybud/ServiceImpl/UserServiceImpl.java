package com.swe.buddybud.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swe.buddybud.Service.UserService;
import com.swe.buddybud.Mapper.UserMapper;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean userLogin(Map<String, String> fields){
        String password = userMapper.userLogin(fields);

        return password.equals(fields.get("password"));
    }

    @Override
    public boolean userEmailDuplCheck(String email){
        int selected = userMapper.userEmailDuplCheck(email);

        return selected > 0;
    }

    @Override
    public boolean userIdDuplCheck(String id){
        int selected = userMapper.userIdDuplCheck(id);

        return selected > 0;
    }

    @Override
    public void insertUserInfo(Map<String, String> fields){
        userMapper.insertUserInfo(fields);
    }
}
