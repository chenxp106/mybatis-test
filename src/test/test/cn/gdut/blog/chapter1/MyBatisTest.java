package cn.gdut.blog.chapter1;

import cn.gdut.blog.chapter1.dao.ArticleDao;
import cn.gdut.blog.chapter1.model.Article;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    /**
     * 1、读取配置文件
     * 2、创建SQLSessionFactorBuilder对象
     * 3、通过SQLSessionFactorBuilder对象创建SQLSessionFactory
     * 4.通过SQLSessionFactory创建SQLSession
     * 5、为Dao接口生成代理类
     * 6、调用接口方法访问数据库
     */
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException{
        String resource = "chapter1/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void testMybatis() throws IOException{

        SqlSession session = sqlSessionFactory.openSession();
        try {

            ArticleDao dao = session.getMapper(ArticleDao.class);
            List<Article> articles = dao.findByAuthorAndCreateTime("coolblog.xyz", "2018-01-10");
            System.out.println(articles);

        }
        finally {
            session.commit();
            session.close();
        }
    }
}
