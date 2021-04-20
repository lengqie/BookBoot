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

    @GetMapping("/record")
    public List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }

    @PutMapping("/record/{recordId}")
    public void upRecord(int success, HttpServletResponse response, @PathVariable String recordId){
        if ( !recordService.updateRecord(recordId,success) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            // return ReturnMapUtils.getMap("200","ok");
        }
        // return ReturnMapUtils.getMap("500","update record failed");
    }

    @GetMapping("/record/{recordId}")
    public Record getRecordByRecordId(@PathVariable String recordId,HttpServletResponse response){
        final Record record = recordService.getRecordByRecordId(recordId);

        if (record == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return record;
    }
}
