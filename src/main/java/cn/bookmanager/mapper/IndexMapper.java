package cn.bookmanager.mapper;

import cn.bookmanager.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**首页
 * @author lengqie
 */
@Mapper
@Repository
public interface IndexMapper {

    /**
     * 查询全部书籍
     * @return
     */
    List<Book> getAllBook();

    /**
     * 查询全部书籍 并排序 Top5
     * @return
     */
    List<Book> getAllBookOrderByHot();

    /**
     * 通过书籍查找
     * @param name
     * @return
     */
    List<Book> getBookByName(String name);

    /**
     * 获取书籍的全部类型 根据热度 排序
     * @return
     */
    List<String> geAllType();

    /**
     * 用过类型查找 书籍
     * @param type
     * @return
     */
    List<Book> getBookByType(String type);

}
