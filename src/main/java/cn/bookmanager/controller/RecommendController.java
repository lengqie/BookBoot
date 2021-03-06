package cn.bookmanager.controller;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.service.RecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "RecommendController",description = "书籍的一些操作")
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
    @Tag(name = "RecommendController")
    @Operation(summary = "添加一个推荐",description = "添加一个推荐")
    @RequiresRoles({"user"})
    @PostMapping("/recommends")
    public void recommend(
            @Parameter(description = "书名") String name,
            @Parameter(description = "Isbn") String isbn,
            @Parameter(description = "书籍类型") String type, HttpServletResponse response){

        Date date = new Date();
        if (recommendService.recommend(name,isbn,type,date)){
            response.setStatus(HttpStatus.OK.value());
        }
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

    }

    /**
     * roles[admin,user] 获取全部推荐记录
     * @return AllRecommend
     */
    @Tag(name = "RecommendController")
    @Operation(summary = "获取全部推荐记录",description = "获取全部推荐记录")
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @GetMapping("/recommends")
    public List<Recommend> getAllRecommend(HttpServletRequest request, HttpServletResponse response){

        for (Cookie cookie : request.getCookies()) {
            // 如果cookie 是user 并且 能获取到用户名 表示这是个用户
            if( !cookie.getValue().isEmpty() && CookieEnum.COOKIE_USER.value().equals(cookie.getName()) ){
                List<Recommend> recommend = new LinkedList<>();
                recommend.add( recommendService.getRecommendByUserId(cookie.getName()) );
                return recommend;
            }
        }
        final List<Recommend> allRecommend = recommendService.getAllRecommend();
        if (!allRecommend.isEmpty()){
            return allRecommend;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    /**
     * roles[admin] 获取记录的详情
     * @param id Recommend.Id
     * @param response response
     * @return recommend
     */
    @Tag(name = "RecommendController")
    @Operation(summary = "获取记录的详情",description = "获取记录的详情")
    @RequiresRoles({"admin"})
    @GetMapping("/recommends/{id}")
    public Recommend getRecommendById(@PathVariable String id, HttpServletResponse response){
        final Recommend recommend = recommendService.getRecommendById(id);
        if (recommend != null) {
            return recommend;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
}
