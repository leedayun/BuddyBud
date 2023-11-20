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

    @PostMapping("/board")
    public String insertBoard(@RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            boardService.insertBoard(fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("createBoardResult", result);

        return jsonObject.toString();
    }

    @GetMapping("/board")
    public String getBoardsList() {
        JsonArray jsonArray = new JsonArray();
        List<Map<String, String>> result = boardService.getBoardsList();

        for (Map<String, String> board : result) {
            JsonObject jsonObject = new JsonObject();

            for (Map.Entry<String, String> entry : board.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping("/board/{boardId}")
    public String getBoard(@PathVariable Integer boardId) {
        JsonObject jsonObject = new JsonObject();

        Map<String, String> result = boardService.getBoard(boardId);

        for (Map.Entry<String, String> entry : result.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    @PutMapping("/board/{boardId}")
    public String updateBoard(@PathVariable Integer boardId, @RequestBody Map<String, String> fields) {
        JsonObject jsonObject = new JsonObject();
        String result = "fail";

        try {
            boardService.updateBoard(boardId, fields);
            result = "succeed";
        }
        catch (Exception e) {
            result = "fail";
        }

        jsonObject.addProperty("updateBoardResult", result);

        return jsonObject.toString();
    }

    @DeleteMapping("/board/{boardId}")
    public String deleteBoard(@PathVariable Integer boardId) {
        JsonObject jsonObject = new JsonObject();
        boolean result = boardService.deleteBoard(boardId);

        jsonObject.addProperty("deleteBoardResult", result);

        return jsonObject.toString();
    }
}
