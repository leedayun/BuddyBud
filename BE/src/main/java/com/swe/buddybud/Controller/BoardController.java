package com.swe.buddybud.Controller;

import com.google.gson.JsonObject;
import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/post")
    public String createPost(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            boardService.createPost(fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("createPostResult", result);

        return jsonObject.toString();
    }

}
