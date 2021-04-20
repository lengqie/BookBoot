package cn.bookmanager.service;

import cn.bookmanager.entity.Recommend;

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

}
