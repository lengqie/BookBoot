package cn.bookmanager.controller;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.service.RecommendService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedList;
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
    @RequiresRoles({"user"})
    @PostMapping("/recommends")
    public void recommend(String name, String isbn,String type, HttpServletResponse response){
        Date date = new Date();
        if (!recommendService.recommend(name,isbn,type,date)){
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        }
    }

    /**
     * roles[admin,user] 获取全部推荐记录
     * @return getAllRecommend
     */
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @GetMapping("/recommends")
    public List<Recommend> getAllRecommend(HttpServletRequest request){

        for (Cookie cookie : request.getCookies()) {
            // 如果cookie 是user 并且 能获取到用户名 表示这是个用户
            if( !cookie.getValue().isEmpty() && CookieEnum.COOKIE_USER.value().equals(cookie.getName()) ){
                List<Recommend> recommend = new LinkedList<>();
                recommend.add( recommendService.getRecommendByUserId(cookie.getName()) );
                return recommend;
            }
        }
        return recommendService.getAllRecommend();
    }

    /**
     * roles[admin] 获取记录的详情
     * @param id Recommend.Id
     * @param response response
     * @return
     */
    @RequiresRoles({"admin"})
    @GetMapping("/recommends/{id}")
    public Recommend getRecommendById(@PathVariable String id, HttpServletResponse response){
        final Recommend recommend = recommendService.getRecommendById(id);
        if (recommend == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return recommend;
    }
}
