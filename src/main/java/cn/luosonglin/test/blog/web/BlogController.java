package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.BlogCollectionMapper;
import cn.luosonglin.test.blog.dao.BlogMapper;
import cn.luosonglin.test.blog.dao.LikeMapper;
import cn.luosonglin.test.blog.entity.Blog;
import cn.luosonglin.test.blog.entity.UserAndBlog;
import cn.luosonglin.test.exception.CustomizedException;
import cn.luosonglin.test.member.dao.UserInfoMapper;
import cn.luosonglin.test.member.entity.UserInfo;
import cn.luosonglin.test.relationship.dao.UsersRelationshipMapper;
import cn.luosonglin.test.relationship.entity.UsersRelationship;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by luosonglin on 28/11/2016.
 */

@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/blogs")
// 通过这里配置使下面的映射都在/blogs，可去除
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UsersRelationshipMapper usersRelationshipMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private BlogCollectionMapper blogCollectionMapper;


/*    @ApiOperation(value = "获取博客列表", notes = "")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultDate getBlogList() {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", blogMapper.findAllBlog());
        resultDate.setData(responseMap);

        return resultDate;
    }*/
    @ApiOperation(value = "获取博客列表 分页", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getBlogList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", new PageInfo<>(blogMapper.findAllBlog()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "推荐博客列表 分页", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/recommend/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getRecommendBlogList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", new PageInfo<>(blogMapper.getRecommendBlog()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "大咖说博客列表 分页", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/vip/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getVipBlogList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", new PageInfo<>(blogMapper.getVipBlog()));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "某一条博客的详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blog_id", value = "博客的ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "from_uid", value = "谁去看这篇微博", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{blog_id}/{from_uid}/detail", method = RequestMethod.GET)
    public ResultDate getBlogDetail(@PathVariable Integer blog_id, @PathVariable Integer from_uid) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        UserAndBlog userAndBlog = blogMapper.getBlogDetail(blog_id);

        if (from_uid != null) {
            UsersRelationship usersRelationship = new UsersRelationship();
            usersRelationship.setFromuid(from_uid);
            usersRelationship.setTouid(userAndBlog.getUserId());

            responseMap.put("isfollowed", usersRelationshipMapper.isFollowed(usersRelationship));
        } else {
            responseMap.put("isfollowed", null); //实际为null
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", userAndBlog);
        responseMap.put("islike", likeMapper.isLiked(from_uid, blog_id) == 0? "false": "true");
        responseMap.put("iscollected", blogCollectionMapper.isCollected(from_uid, blog_id) == 0? "false": "true");

        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "发微博", notes = "创建微博")
    @ApiImplicitParam(name = "blog", value = "Blog实体", required = true, dataType = "Blog")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postMicroBlog(@ModelAttribute Blog blog) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

//        blogMapper.insertByBlog(blog);

//        blogMapper.writeBlog(blog);
//        blogMapper.insertBlog(blog);

        Map<String, Object> blogMap = new HashMap<>();
        blogMap.put("user_id", blog.getUserId());
        blogMap.put("title", blog.getTitle());
        blogMap.put("content", blog.getContent());
        blogMap.put("images", blog.getImages());
        blogMap.put("create_at", blog.getCreatedAt());
        blogMap.put("tag_id", blog.getTagId());
        blogMap.put("comment_count", 0);
        blogMap.put("like_count", 0);
        blogMapper.insertBlogInfoByMap(blogMap);

        resultDate.setCode(200);
//        responseMap.put("msg", "success");
//        responseMap.put("blog", blogMapper.findBlogById(blog.getUserId()));
        resultDate.setData("success");

        return resultDate;
    }

    /*
    测试，向兵帮忙解决中文乱码问题
    springboot默认就是utf-8，而且自带编码过滤器
    intellij默认也是utf-8,
    启动方式是war，那就往tomcat里面找问题，然后问题定位在apache-tomcat-7.0.73/conf/server.xml

    <!-- A "Connector" represents an endpoint by which requests are received
    and responses are returned. Documentation at :
    Java HTTP Connector: /docs/config/http.html (blocking & non-blocking)
    Java AJP  Connector: /docs/config/ajp.html
    APR (HTTP/AJP) Connector: /docs/apr.html
    Define a non-SSL HTTP/1.1 Connector on port 8080
            -->
    <Connector port="8080" protocol="HTTP/1.1"
    connectionTimeout="20000"
    redirectPort="8443"
    URIEncoding="UTF-8"/>
    */
//    @ApiOperation(value="发微博test", notes="创建微博")
//    @ApiImplicitParam(name = "blog", value = "Blog实体", required = true, dataType = "Blog")
//    @RequestMapping(value="/test", method=RequestMethod.POST)
//    private ResultDate postMicroBlogByMap(@ModelAttribute Blog blog) {
//
//        ResultDate resultDate = new ResultDate();
//        Map<String, Object> responseMap = new HashMap<>();
//
//        resultDate.setCode(200);
//        responseMap.put("msg","success");
//        responseMap.put("blog", blog);
//        resultDate.setData(responseMap);
//
//        System.out.print("TAG "+blog.getContent());
//
//        return resultDate;
//    }

    @ApiOperation(value = "获取某个用户的全部微博信息 分页", notes = "根据url的user_id来获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户自己的ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{user_id}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getBlogByUserId(@PathVariable Integer user_id,
                                      @PathVariable Integer pageNum,
                                      @PathVariable Integer pageSize) {

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("blog", new PageInfo<>(blogMapper.findBlogById(user_id)));
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value = "获取我关注的人的全部微博信息，按时间降序 分页", notes = "根据url的user_id来获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
//            @ApiImplicitParam(name = "header", value = "用户token", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "/{user_id}/follows/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getMyFollowsBlog(@PathVariable Integer user_id,
                                       @PathVariable Integer pageNum,
                                       @PathVariable Integer pageSize) throws CustomizedException {//, @RequestHeader String header @RequestHeader String Authorization

        if (user_id == null)
            throw new CustomizedException("user_id不可为空");

//        if (!userInfoMapper.getTokenId(user_id).equals(Authorization))
//            throw new CustomizedException("您无权限访问");
//        Authorization = "Bearer " + Authorization;

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        List<Integer> followIds = usersRelationshipMapper.getMyFollowIds(user_id);


        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("follows", followIds);
        responseMap.put("followsBlog", followIds.size() != 0? new PageInfo<>(blogMapper.getFollowsBlogByListId(followIds)) : followIds);
        resultDate.setData(responseMap);
        return resultDate;
    }

    @ApiOperation(value = "获取宝友圈（我自己、我关注的人）的全部微博信息，按时间降序 分页", notes = "根据url的user_id来获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{user_id}/friends/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getMyFriendsBlog(@PathVariable Integer user_id,
                                       @PathVariable Integer pageNum,
                                       @PathVariable Integer pageSize) throws CustomizedException {

        if (user_id == null)
            throw new CustomizedException("user_id不可为空");

        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        List<Integer> followIds = usersRelationshipMapper.getMyFollowIds(user_id);
        followIds.add(user_id);

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        responseMap.put("follows", followIds);
        responseMap.put("followsBlog", new PageInfo<>(blogMapper.getFollowsBlogByListId(followIds))); //
        resultDate.setData(responseMap);
        return resultDate;
    }


}
