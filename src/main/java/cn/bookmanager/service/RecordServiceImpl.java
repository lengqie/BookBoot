package cn.bookmanager.service;

import cn.bookmanager.entity.Record;
import cn.bookmanager.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lengqie
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Record> getAllRecord() {
        return recordMapper.getAllRecord();
    }

    @Override
    public Record getRecordByRecordId(String recordId) {
        return recordMapper.getRecordByRecordId(recordId);
    }

    @Override
    public List<Record> getRecordByUserId(String userId) {
        return recordMapper.getRecordByUserId(userId);
    }
}
