package com.swe.buddybud.Controller;

import com.swe.buddybud.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired
    BoardService boardService;


}
