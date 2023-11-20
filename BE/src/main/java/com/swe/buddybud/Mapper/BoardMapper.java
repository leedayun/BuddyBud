package com.swe.buddybud.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    void insertNotice(Map<String, String> fields);

    List<Map<String, String>> getNoticesList();

    Map<String, String> getNotice(Integer noticeId);

    void updateNotice(Integer noticeId, Map<String, String> fields);

    int deleteNotice(Integer noticeId);
}
