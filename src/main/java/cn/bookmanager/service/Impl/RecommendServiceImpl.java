package cn.bookmanager.service.Impl;

import cn.bookmanager.entity.Recommend;
import cn.bookmanager.mapper.RecommendMapper;
import cn.bookmanager.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Recommend的实现类
 * @author lengqie
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendMapper recommendMapper;

    @Override
    public List<Recommend> getAllRecommend() {
        return recommendMapper.getAllRecommend();
    }


    @Override
    public Recommend getRecommendById(String id) {
        return recommendMapper.getRecommendById(id);
    }

    @Override
    public Recommend getRecommendByUserId(String userId) {
        return recommendMapper.getRecommendByUserId(userId);
    }


    @Override
    public boolean recommend(String name, String isbn, String type, Date date) {
        final int i = recommendMapper.recommend(name, isbn ,type, date);
        return i == 1;
    }
}
