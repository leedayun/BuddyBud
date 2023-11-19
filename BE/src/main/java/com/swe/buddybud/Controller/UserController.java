package com.swe.buddybud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.*;

import com.swe.buddybud.Service.UserService;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user/login")
    public String userLogin(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        boolean result = userService.userLogin(fields);

        jsonObject.addProperty("userLoginResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/user/emailDuplCheck")
    public String userEmailDuplCheck(String email){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("userEmailDuplCheck", userService.userEmailDuplCheck(email));

        return jsonObject.toString();
    }

    @GetMapping("/user/idDuplCheck")
    public String userIdDuplCheck(String id){
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("userIdDuplCheck", userService.userIdDuplCheck(id));

        return jsonObject.toString();
    }

    @PostMapping("/user/insertUserInfo")
    public String insertUserInfo(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try{
            userService.insertUserInfo(fields);
            result = "succeed";
        }
        catch(Exception e){
            result = "fail";
        }

        jsonObject.addProperty("inserUserInfoResult", result);

        return jsonObject.toString();
    }
}
