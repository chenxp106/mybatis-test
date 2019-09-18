package cn.gdut.blog.chapter1.dao;

import cn.gdut.blog.chapter1.model.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    List<Article> findByAuthorAndCreateTime(@Param("author") String author, @Param("createTime") String createTime);

}
