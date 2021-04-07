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

    /**
     * 修改记录
     * @param recordId
     * @param  success
     * @return 操作的 数量 一般是 将状态码设置为 1
     */
    int updateRecord(String recordId, int success);
}
