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

    @GetMapping("/willow/getAllChat")
    public String getAllChat(Integer sender_no, Integer receiver_no){
        JsonArray jsonArray = new JsonArray();
        List<Map<String, Object>> chatLog = willowService.getAllChat(sender_no, receiver_no);
        for (Map<String, Object> chat: chatLog) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("sender_no",chat.get("sender").toString());
            jsonObject.addProperty("content",chat.get("content").toString());
            jsonObject.addProperty("time",chat.get("time").toString());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/willow/getMyWillows")
    public String getMyWillows(Integer user_no){
        JsonArray jsonArray = new JsonArray();
        List<Map<String, Object>> willows = willowService.getMyWillows(user_no);
        for (Map<String, Object> willow: willows) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("uid",willow.get("uid").toString());
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @PostMapping("/willow/sendChat")
    public String sendChat(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        boolean result = false;

        try{
            willowService.insertChat(fields);
            result = true;
        }
        catch(Exception e){
            result = false;
        }
        jsonObject.addProperty("sendChatResult", result);

        return jsonObject.toString();
    }

    @PostMapping("/willow/acceptWillow")
    public String acceptWillow(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        boolean result = false;

        try{
            willowService.acceptWillow(fields);
            result = true;
        }
        catch(Exception e){
            result = false;
        }

        if(result){
            try{
                willowService.addChatRoom(fields);
                result = true;
            }catch(Exception e){
                result = false;
            }
        }

        jsonObject.addProperty("acceptWillowResult", result);

        return jsonObject.toString();
    }

    @PostMapping("/willow/rejectWillow")
    public String rejectWillow(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        boolean result = willowService.deleteWillowRequest(fields);
        jsonObject.addProperty("rejectWillowResult", result);
        return jsonObject.toString();
    }

    @PostMapping("/willow/cancelWillowRequest")
    public String cancelWillowRequest(@RequestBody Map<String, String> fields){
        JsonObject jsonObject = new JsonObject();
        boolean result = willowService.deleteWillowRequest(fields);
        jsonObject.addProperty("cancelWillowRequestResult", result);
        return jsonObject.toString();
    }

}
