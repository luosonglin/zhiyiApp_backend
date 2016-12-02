package cn.luosonglin.test.blog.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.blog.dao.BlogCollectionMapper;
import cn.luosonglin.test.blog.entity.BlogCollection;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luosonglin on 02/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/collection")
public class BlogCollectionController {

    @Autowired
    private BlogCollectionMapper collectionMapper;

    @ApiOperation(value = "收藏该微博", notes = "收藏微博")
    @ApiImplicitParam(name = "collection", value = "BlogCollection实体", required = true, dataType = "BlogCollection")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postCollectMicroBlog(@ModelAttribute BlogCollection collection) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> collectionMap = new HashMap<>();
        collectionMap.put("user_id", collection.getUserId());
        collectionMap.put("blog_id", collection.getBlogId());
        collectionMap.put("created_at", new Date());
        collectionMapper.insertCollectionByMap(collectionMap);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="取消收藏", notes="根据blog_like的id来指定更新收藏记录")
    @ApiImplicitParam(name = "collection", value = "BlogCollection实体", required = true, dataType = "BlogCollection")
    @RequestMapping(value="/", method=RequestMethod.PUT)
    public ResultDate putCollection(@ModelAttribute BlogCollection collection) {
        Map<String, Object> collectionMap = new HashMap<>();
        collectionMap.put("user_id", collection.getUserId());
        collectionMap.put("blog_id", collection.getBlogId());
        collectionMapper.deleteCollectionByMap(collectionMap);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="我收藏的微博列表", notes="")
    @ApiImplicitParam(name = "userId", value = "我的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public ResultDate getMyCollectionList(@PathVariable Integer userId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("collection", collectionMapper.findMyCollectionBlog(userId));
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value="我收藏的微博数量", notes="")
    @ApiImplicitParam(name = "userId", value = "我的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value="/{userId}/count", method=RequestMethod.GET)
    public ResultDate getMyCollectionCount(@PathVariable Integer userId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("likeCount", collectionMapper.getMyCollectionCount(userId));
        resultDate.setData(responseMap);

        return resultDate;
    }
}
