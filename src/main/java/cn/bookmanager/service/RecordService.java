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
     * 通过id 获取记录
     * @param recordId
     * @return
     */
    Record getRecordByRecordId(String recordId);
    /**
     * 通过id 获取记录
     * @param userId
     * @return
     */
    List<Record> getRecordByUserId(String userId);
}
