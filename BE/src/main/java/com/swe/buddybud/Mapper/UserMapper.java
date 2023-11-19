package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    String userLogin(Map<String, String> fields);

    Integer userEmailDuplCheck(String email);

    Integer userIdDuplCheck(String email);

    void insertUserInfo(Map<String, String> fields);
}
