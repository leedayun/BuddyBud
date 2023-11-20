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

    @PostMapping("/sns")
    public String insertSNS(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            homeService.insertSNS(fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("createSNSResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/sns")
    public String getSNSsList() {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = homeService.getSNSsList();

        for (Map<String, String> sns : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : sns.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/sns/{snsId}")
    public String getSNS(@PathVariable Integer snsId) {
        JsonObject jsonObject = new JsonObject();

        Map<String, String> result = homeService.getSNS(snsId);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    @PutMapping("/sns/{snsId}")
    public String updateSNS(@PathVariable Integer snsId, @RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            homeService.updateSNS(snsId, fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("updateSNSResult", result);

        return jsonObject.toString();
    }

    @DeleteMapping("/sns/{snsId}")
    public String deleteSNS(@PathVariable Integer snsId) {
        JsonObject jsonObject = new JsonObject();
        boolean result = homeService.deleteSNS(snsId);

        jsonObject.addProperty("deleteSNSResult", result);

        return jsonObject.toString();
    }

}
