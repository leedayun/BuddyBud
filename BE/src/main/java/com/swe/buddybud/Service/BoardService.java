package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface BoardService {

    void insertNotice(Map<String, String> fields);

    List<Map<String, String>> getNoticesList();

    Map<String, String> getNotice(Integer noticeId);

    void updateNotice(Integer noticeId, Map<String, String> fields);

    boolean deleteNotice(Integer noticeId);
}

