package com.swe.buddybud.Controller;

import com.swe.buddybud.Service.WillowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WillowController {

    @Autowired
    WillowService willowService;


}
