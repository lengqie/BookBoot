package cn.bookmanager.mapper;

import cn.bookmanager.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**记录的一些操作
 * @author lengqie
 */
@Mapper
@Repository
public interface RecordMapper {
    /**
     * 获取全部的记录
     * @return Record s
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
     * 修改记录
     * @param recordId Record.Id
     * @param  success Success
     * @return 操作的数量 一般是将状态码设置为 1
     */
    int updateRecord(String recordId, int success);
}
