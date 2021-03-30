package cn.bookmanager.mapper;

import cn.bookmanager.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lengqie
 */
@Mapper
@Repository
public interface BooksMapper {

    /**
     * 添加书籍的热度
     * @param isbn
     */
    void addHot(String isbn);

    /**
     * 获取一般书的详情
     * @param Isbn
     * @return
     */
    Books getOneBook(String Isbn);
}
