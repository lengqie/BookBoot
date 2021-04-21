package cn.bookmanager.service;

import cn.bookmanager.entity.Record;
import cn.bookmanager.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lengqie
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> getAllRecord() {
        return recordMapper.getAllRecord();
    }

    @Override
    public Record getRecordByRecordId(String recordId) {
        return recordMapper.getRecordById(recordId);
    }

    @Override
    public List<Record> getRecordByUserId(String userId) {
        return recordMapper.getRecordByUserId(userId);
    }

    @Override
    public Boolean updateRecord(String recordId, int success) {
        return recordMapper.updateRecord(recordId,success) == 1;
    }
}
