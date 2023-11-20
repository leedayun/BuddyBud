package com.swe.buddybud.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.swe.buddybud.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accountant/{userId}")
    public String getUserInfo(@PathVariable Integer userId) {
        JsonObject jsonObject = new JsonObject();

        Map<String, String> result = accountService.getUserInfo(userId);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    @GetMapping("/accountant/{userId}/post")
    public String getUserPostsList(@PathVariable Integer userId) {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = accountService.getUserPostsList(userId);

        for (Map<String, String> notice : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : notice.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/accountant/{userId}/scrap")
    public String getUserScrapsList(@PathVariable Integer userId) {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = accountService.getUserScrapsList(userId);

        for (Map<String, String> notice : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : notice.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }
}
