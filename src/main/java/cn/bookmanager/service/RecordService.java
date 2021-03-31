package cn.bookmanager.service;

import cn.bookmanager.entity.Record;

import java.util.List;

/**
 * @author lengqie
 */
public interface RecordService {
    /**
     * 获取全部的记录
     * @return
     */
    List<Record> getAllRecord();

    /**
     * 查询记录
     * @param recordId
     * @return
     */
    Record getOneRecord(String recordId);
}
