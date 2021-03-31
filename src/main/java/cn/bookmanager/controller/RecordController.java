package cn.bookmanager.controller;

import cn.bookmanager.entity.Record;
import cn.bookmanager.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**查询记录
 * @author lengqie
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    RecordService  recordService;

    @GetMapping("/all")
    public List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param recordId
     * @return
     */
    @GetMapping("/{recordId}")
    public Record getOneRecord(@PathVariable String recordId){
        return recordService.getOneRecord(recordId);
    }

}
