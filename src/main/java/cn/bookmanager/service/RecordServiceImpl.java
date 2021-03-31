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
    public Record getOneRecord(String recordId) {
        return recordMapper.getOneRecord(recordId);
    }
}
