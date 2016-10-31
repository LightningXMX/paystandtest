import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by echo on 16/7/13.
 */
public class Test {
    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            PeopleMapper mapper = session.getMapper(PeopleMapper.class);
            People people = mapper.selectPeopleById(1L);
            System.out.println(people);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
}
