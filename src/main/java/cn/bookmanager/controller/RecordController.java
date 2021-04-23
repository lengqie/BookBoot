package cn.bookmanager.controller;

import cn.bookmanager.entity.Record;
import cn.bookmanager.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * roles[admin] 获取全部的记录
     * @return record s
     */
    @GetMapping("/record")
    public List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }

    /**
     * roles[admin] 修改某个记录
     * @param status    Record.Status
     * @param recordId  Record.Id
     * @param response  response
     */
    @PutMapping("/record/{recordId}")
    public void upRecord(int status, @PathVariable String recordId, HttpServletResponse response){
        if ( !recordService.updateRecord(recordId,status) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            // return ReturnMapUtils.getMap("200","ok");
        }
        // return ReturnMapUtils.getMap("500","update record failed");
    }

    /**
     * roles[admin,user] 获取一个记录
     * @param recordId  Record.Id
     * @param response  response
     * @return record Record
     */
    @GetMapping("/record/{recordId}")
    public Record getRecordByRecordId(@PathVariable String recordId,HttpServletResponse response){
        final Record record = recordService.getRecordByRecordId(recordId);

        if (record == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return record;
    }
}
