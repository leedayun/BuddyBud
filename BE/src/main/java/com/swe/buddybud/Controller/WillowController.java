package com.swe.buddybud.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.swe.buddybud.Service.WillowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class WillowController {

    @Autowired
    WillowService willowService;

    @PostMapping("/willow")
    public String sendWillow(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        boolean result = false;

        try {
            willowService.sendWillow(fields);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }

        jsonObject.addProperty("sendWillowRequestResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/willow/{userId}/sent")
    public String sentWillow(@PathVariable Integer userId) {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = willowService.getSentWillowList(userId);

        for (Map<String, String> willow : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : willow.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/willow/{userId}/received")
    public String receivedWillow(@PathVariable Integer userId) {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = willowService.getReceivedWillowList(userId);

        for (Map<String, String> willow : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : willow.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @PostMapping("/willow/accept")
    public String acceptWillow(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        boolean result = false;

        try {
            willowService.acceptWillow(fields);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }

        jsonObject.addProperty("sendWillowRequestResult", result);

        return jsonObject.toString();
    }

    @PostMapping("/willow/delete")
    public String deleteWillow(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        boolean result = false;

        try {
            willowService.deleteWillow(fields);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }

        jsonObject.addProperty("deleteWillowResult", result);

        return jsonObject.toString();
    }

}
