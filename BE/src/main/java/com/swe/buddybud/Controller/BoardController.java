package com.swe.buddybud.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("/notice")
    public String insertNotice(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            boardService.insertNotice(fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("createNoticeResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/notice")
    public String getNoticesList() {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = boardService.getNoticesList();

        for (Map<String, String> notice : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : notice.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/notice/{noticeId}")
    public String getNotice(@PathVariable Integer noticeId) {
        JsonObject jsonObject = new JsonObject();

        Map<String, String> result = boardService.getNotice(noticeId);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    @PutMapping("/notice/{noticeId}")
    public String updateNotice(@PathVariable Integer noticeId, @RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            boardService.updateNotice(noticeId, fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("updateNoticeResult", result);

        return jsonObject.toString();
    }

    @DeleteMapping("/notice/{noticeId}")
    public String deleteNotice(@PathVariable Integer noticeId) {
        JsonObject jsonObject = new JsonObject();
        boolean result = boardService.deleteNotice(noticeId);

        jsonObject.addProperty("deleteNoticeResult", result);

        return jsonObject.toString();
    }
}
