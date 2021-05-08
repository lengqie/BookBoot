package cn.bookmanager.controller;

import cn.bookmanager.entity.Record;
import cn.bookmanager.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 记录查询
 * @author lengqie
 */
@Tag(name = "RecordController",description = "记录的一些操作")
@RestController
@RequestMapping
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * roles[admin] 获取全部的记录
     * @return record s
     */
    @Tag(name = "RecordController")
    @Operation(summary = "获取全部的记录",description = "获取全部的记录")
    @RequiresRoles({"admin"})
    @GetMapping("/records")
    public List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }

    /**
     * roles[admin] 修改某个记录
     * @param status    Record.Status
     * @param recordId  Record.Id
     * @param response  response
     */
    @Tag(name = "RecordController")
    @Operation(summary = "修改某个记录",description = "修改某个记录")
    @RequiresRoles({"admin"})
    @PutMapping("/records/{recordId}")
    public void upRecord(
            @Parameter(description = "记录的状态值") int status,
            @Parameter(description = "记录Id")@PathVariable String recordId, HttpServletResponse response){

        if (recordService.updateRecord(recordId,status) ) {
            response.setStatus(HttpStatus.OK.value());
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * roles[admin,user] 获取一个记录
     * @param recordId  Record.Id
     * @param response  response
     * @return record Record
     */
    @Tag(name = "RecordController")
    @Operation(summary = "获取一个记录",description = "获取一个记录")
    @RequiresUser
    @GetMapping("/records/{recordId}")
    public Record getRecordByRecordId(
            @Parameter(description = "记录Id") @PathVariable String recordId,HttpServletResponse response){

        final Record record = recordService.getRecordByRecordId(recordId);

        if (record != null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return null;
    }
}
