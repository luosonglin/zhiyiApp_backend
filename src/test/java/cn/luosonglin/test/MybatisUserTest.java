package cn.luosonglin.test;

import cn.luosonglin.test.domain.User;
import cn.luosonglin.test.domain.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luosonglin on 24/11/2016.
 *
 * Mybatis user 单元测试类
 *
 * 测试逻辑：插入一条name=AAA，age=20的记录，然后根据name=AAA查询，并判断age是否为20
 * 测试结束回滚数据，保证测试单元每次运行的数据环境独立
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MybatisApplication.class)
@Transactional
public class MybatisUserTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findByName() throws Exception {
        // insert一条数据，并select出来验证
        userMapper.insert("AAA", 20);
        User u = userMapper.findByName("AAA");
        Assert.assertEquals(20, u.getAge().intValue());
        // update一条数据，并select出来验证
        u.setAge(30);
        userMapper.update(u);
        u = userMapper.findByName("AAA");
        Assert.assertEquals(30, u.getAge().intValue());
        // 删除这条数据，并select验证
        userMapper.delete(u.getId());
        u = userMapper.findByName("AAA");
        Assert.assertEquals(null, u);

        u = new User("BBB", 30);
        userMapper.insertByUser(u);
        Assert.assertEquals(30, userMapper.findByName("BBB").getAge().intValue());

        Map<String, Object> map = new HashMap<>();
        map.put("name", "CCC");
        map.put("age", 40);
        userMapper.insertByMap(map);
        Assert.assertEquals(40, userMapper.findByName("CCC").getAge().intValue());

//        List<User> userList = userMapper.findAll();
//        for(User user : userList) {
//            Assert.assertEquals(null, user.getId());
//            Assert.assertNotEquals(null, user.getName());
//        }
    }
}
