package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public interface BoardService {

    void createPost(Map<String, String> fields);
}
