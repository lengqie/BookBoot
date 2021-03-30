package cn.bookmanager.mapper;

import cn.bookmanager.entity.Books;
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
    List<Books> getAllBooks();

    /**
     * 查询全部书籍 并排序
     * @return
     */
    List<Books> getAllBooksOrderByHot();

    /**
     * 通过书籍查找
     * @param name
     * @return
     */
    List<Books> getBookByName(String name);

}
