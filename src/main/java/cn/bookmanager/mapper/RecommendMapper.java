package cn.bookmanager.mapper;

import cn.bookmanager.entity.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 用户推荐书籍的一些操作
 * @author lengqie
 */
@Mapper
@Repository
public interface RecommendMapper {
    /**
     * 获取全部的推荐信息
     * @return Recommend s
     */
    List<Recommend> getAllRecommend();

    /**
     * 获取指定的推荐
     * @param id Recommend.Id
     * @return Recommend s
     */
    Recommend getRecommendById(String id);

    /**
     * 将推荐书籍添加到 书籍库中
     * @param id Recommend.Id
     * @param date Date
     */
    void addBookFromRecommend(int id, Date date);
}
