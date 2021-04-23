package cn.bookmanager.controller;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author lengqie
 */
@RestController
@RequestMapping
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * roles[user] 添加一个推荐
     * @param name     name
     * @param isbn     isbn
     * @param type     type
     * @param response response
     */
    @PostMapping("/recommend")
    public void recommend(String name, String isbn,String type, HttpServletResponse response){
        Date date = new Date();
        if (!recommendService.recommend(name,isbn,type,date)){
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        }
    }

    /**
     * roles[admin] 获取全部推荐记录
     * @return getAllRecommend
     */
    @GetMapping("/recommend")
    public List<Recommend> getAllRecommend(){
        return recommendService.getAllRecommend();
    }

    /**
     * roles[admin] 获取记录的详情
     * @param id Recommend.Id
     * @param response response
     * @return
     */
    @GetMapping("/recommend/{id}")
    public Recommend getRecommendById(@PathVariable String id, HttpServletResponse response){
        final Recommend recommend = recommendService.getRecommendById(id);
        if (recommend == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return recommend;
    }

    /**
     * 有问题
     * roles[user] 获取记录的详情
     * @param response response
     * @return
     */
    @GetMapping("/recommend")
    public Recommend getMyRecommend(HttpServletResponse response, HttpSession session){
        User user =  (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        /**
         * 待补充 Service
         */
        return null;
    }
}
