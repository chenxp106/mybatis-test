package cn.gdut.blog.chapter1;

import cn.gdut.blog.chapter1.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:chapter1/application.xml")
public class SpringJdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSpringJdbc(){
        String author = "cxp";
        String date = "2018.1.1";
        String sql = "SELECT id,title,author, content, create_time"
                +" from blog_article" + " where author = '" + author +"'";
        List<Article> articles = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setAuthor(rs.getString("author"));
            article.setContent(rs.getString("content"));
            article.setCreateTime(rs.getDate("create_time"));
            return article;
        });

        System.out.println("SQL ==>" + sql);
        System.out.println("Result: ");
        articles.forEach(System.out::println);
    }
}
