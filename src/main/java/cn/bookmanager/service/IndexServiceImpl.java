package cn.bookmanager.service;

import cn.bookmanager.entity.Book;
import cn.bookmanager.mapper.BookMapper;
import cn.bookmanager.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页
 * @author lengqie
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndexServiceImpl implements IndexService {

}
