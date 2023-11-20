package com.swe.buddybud.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.swe.buddybud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/home")
    public String insertHome(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            homeService.insertHome(fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("createHomeResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/home")
    public String getHomesList() {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = homeService.getHomesList();

        for (Map<String, String> home : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : home.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/home/{homeId}")
    public String getHome(@PathVariable Integer homeId) {
        JsonObject jsonObject = new JsonObject();

        Map<String, String> result = homeService.getHome(homeId);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    @PutMapping("/home/{homeId}")
    public String updateHome(@PathVariable Integer homeId, @RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            homeService.updateHome(homeId, fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("updateHomeResult", result);

        return jsonObject.toString();
    }

    @DeleteMapping("/home/{homeId}")
    public String deleteHome(@PathVariable Integer homeId) {
        JsonObject jsonObject = new JsonObject();
        boolean result = homeService.deleteHome(homeId);

        jsonObject.addProperty("deleteHomeResult", result);

        return jsonObject.toString();
    }

}
