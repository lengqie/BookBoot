package cn.bookmanager.controller;

import cn.bookmanager.entity.Recommend;
import cn.bookmanager.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lengqie
 */
@RestController
@RequestMapping
public class RecommendController {

    @Autowired
    private RecommendService recommendService;


    @GetMapping("/recommend/all")
    public List<Recommend> getAllRecommend(){
        return recommendService.getAllRecommend();
    }

    @GetMapping("/recommend/{id}")
    public Recommend getRecommendById(@PathVariable String id, HttpServletResponse response){
        final Recommend recommend = recommendService.getRecommendById(id);
        if (recommend == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return recommend;
    }
}
