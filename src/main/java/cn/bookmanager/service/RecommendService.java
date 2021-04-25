package cn.bookmanager.service;

import cn.bookmanager.entity.Recommend;

import java.util.Date;
import java.util.List;

/**
 * 对 Recommend 的操作
 * @author lengqie
 */
public interface RecommendService {

    /**
     * 获取全部的推荐
     * @return Recommend s
     */
    List<Recommend> getAllRecommend();

    /**
     * 通过Id 查询数据
     * @param id Recommend.Id
     * @return Recommend
     */
    Recommend getRecommendById(String id);
    /**
     * 通过Id 查询数据
     * @param userId Recommend.Id
     * @return Recommend
     */
    Recommend getRecommendByUserId(String userId);

    /**创建 一条新记录
     * @param name name
     * @param isbn isbn
     * @param type type
     * @param date date
     * @return
     */
    boolean recommend(String name, String isbn,String type, Date date);


}
