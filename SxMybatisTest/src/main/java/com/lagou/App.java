package com.lagou;

import com.dao.AnimalDao;
import com.dao.AnimalDaoImpl_old;
import com.lagou.io.Resources;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import com.pojo.Animal;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DocumentException, PropertyVetoException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {

       /*
       问题：代码冗余，硬编码
       AnimalDao dao = new AnimalDaoImpl_old();
       Animal animal = new Animal();
        animal.setId(2);
        animal.setName("zhangan2");
        Animal animal1 = dao.queryOneByIdAndName(animal);
        System.out.println(animal1);*/

       /* List<Animal> animals = dao.queryAll();
        for (Animal animal : animals) {
            System.out.println(animal);
        }
        解决：直接删除AnimalDaoImpl ，使用动态代理实现
        */

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        System.out.println(resourceAsStream);
        //3.获取会话工厂对象sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取会话对象
        SqlSession session = sqlSessionFactory.openSession();

        AnimalDao dao = session.getMapper(AnimalDao.class);

        List<Animal> animals = dao.queryAll();
        for (Animal animal : animals) {
            System.out.println(animal);
        }
        System.out.println("------------");
        Animal animal = new Animal();
        animal.setId(2);
        animal.setName("zhangan2");
        Animal animal1 = dao.queryOneByIdAndName(animal);
        System.out.println(animal1);
    }
}
