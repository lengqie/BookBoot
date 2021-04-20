package cn.bookmanager.service;

import cn.bookmanager.entity.Record;

import java.util.List;

/**
 * @author lengqie
 */
public interface RecordService {
    /**
     * 获取全部的记录
     * @return Record
     */
    List<Record> getAllRecord();

    /**
     * 通过id 获取记录
     * @param recordId Record.Id
     * @return Record
     */
    Record getRecordByRecordId(String recordId);
    /**
     * 通过id 获取记录
     * @param userId User.Id
     * @return Record s
     */
    List<Record> getRecordByUserId(String userId);

    /**
     * 更新记录
     * @param recordId Record.Id
     * @param success status
     * @return 是否成功更新
     */
    Boolean updateRecord(String recordId, int success);

}
