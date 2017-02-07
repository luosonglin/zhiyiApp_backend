package cn.luosonglin.test.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.domain.User;
import cn.luosonglin.test.domain.UserMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by luosonglin on 24/11/2016.
 *
 * 本地controller
 */
@RestController
@RequestMapping(value="/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value="/", method= RequestMethod.GET)
    public ResultDate getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("users", userMapper.findAll());
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResultDate postUser(@ModelAttribute User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
//        users.put(user.getId(), user);

        ResultDate resultDate = new ResultDate();

        userMapper.insertByUser(user);
        resultDate.setCode(200);
        resultDate.setData("success");

        return resultDate;
    }

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")//如不添加paramType="path"，所有的参数类型都会是body，获取不到请求参数。参考swagger的api
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResultDate getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
//        return users.get(id);

        ResultDate resultDate = new ResultDate();
        resultDate.setCode(200);
        resultDate.setData(userMapper.findById(id));
        return resultDate;
    }

    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResultDate putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
//        User u = users.get(id);
//        u.setName(user.getName());
//        u.setAge(user.getAge());
//        users.put(id, u);

        User u = userMapper.findById(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        userMapper.update(u);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        responseMap.put("user", userMapper.findById(id));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResultDate deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
//        users.remove(id);
//        return "success";

        userMapper.delete(id);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }
}
