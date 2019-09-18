package cn.gdut.blog.chapter1;

import cn.gdut.blog.chapter1.model.Article;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    @Test
    public void testJdbc(){
        String url = "jdbc:mysql://123.207.60.196:3306/myblog?user=root&password=chenxp106&useUnicode=true&characterEncoding=UTF8&useSSL=false";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url);

            String author = "coolblog.xyz";
            String date = "2018.01.10";
            String sql = "SELECT id, title, author, content, create_time FROM blog_article WHERE author = '" + author + "' AND create_time > '" + date + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Article> articles = new ArrayList<>(rs.getRow());
            while (rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setAuthor(rs.getString("author"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getDate("create_time"));
                articles.add(article);
            }
            System.out.println("SQL ==>" + sql);
            System.out.println("结果：");
            articles.forEach(System.out :: println);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
