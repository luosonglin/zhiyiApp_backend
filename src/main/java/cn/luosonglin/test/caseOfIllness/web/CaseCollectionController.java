package cn.luosonglin.test.caseOfIllness.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.caseOfIllness.dao.CaseCollectionMapper;
import cn.luosonglin.test.caseOfIllness.dao.CaseMapper;
import cn.luosonglin.test.caseOfIllness.entity.CaseCollection;
import cn.luosonglin.test.caseOfIllness.entity.UserAndCase;
import cn.luosonglin.test.exception.CustomizedException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by luosonglin on 07/01/2017.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/caseOfIllness/collection")
public class CaseCollectionController {

    @Autowired
    private CaseCollectionMapper caseCollectionMapper;

    @Autowired
    private CaseMapper caseMapper;

    @ApiOperation(value = "收藏该病例", notes = "收藏病例")
    @ApiImplicitParam(name = "caseCollection", value = "CaseCollection实体", required = true, dataType = "CaseCollection")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResultDate postCollectCaseOfIllness(@ModelAttribute CaseCollection caseCollection) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        if (caseCollectionMapper.isCaseCollected(caseCollection.getUserId(), caseCollection.getCaseId()) != 0)
            throw new CustomizedException("已收藏过该病例");

        Map<String, Object> collectionMap = new HashMap<>();
        collectionMap.put("user_id", caseCollection.getUserId());
        collectionMap.put("case_id", caseCollection.getCaseId());
        collectionMap.put("created_at", new Date());
        caseCollectionMapper.insertCaseCollectionByMap(collectionMap);

        resultDate.setCode(200);
        responseMap.put("msg", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "取消收藏", notes = "根据case_like的id来指定更新收藏记录")
    @ApiImplicitParam(name = "caseCollection", value = "CaseCollection实体", required = true, dataType = "CaseCollection")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResultDate putCollection(@ModelAttribute CaseCollection caseCollection) {
        Map<String, Object> collectionMap = new HashMap<>();
        collectionMap.put("user_id", caseCollection.getUserId());
        collectionMap.put("case_id", caseCollection.getCaseId());
        caseCollectionMapper.deleteCaseCollectionByMap(collectionMap);

        ResultDate resultDate = new ResultDate();
        Map<Object, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("mag", "success");
        resultDate.setData(responseMap);

        return resultDate;
    }

    @ApiOperation(value = "我收藏的病例列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "我的ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页的数目", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/{userId}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public ResultDate getMyCollectionList(@PathVariable Integer userId,
                                          @PathVariable Integer pageNum,
                                          @PathVariable Integer pageSize) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        List<Integer> collectionIds = caseCollectionMapper.getMyCollectionCaseIds(userId);

        resultDate.setCode(200);
//        responseMap.put("collectionIds", collectionIds);
//        responseMap.put("collection", collectionMapper.findMyCollectionBlog(userId));

        if (!collectionIds.isEmpty()) {
            if (pageNum != null && pageSize != null) {
                PageHelper.startPage(pageNum, pageSize);
            }
            responseMap.put("case", new PageInfo<>(caseMapper.getCaseListByCaseId(collectionIds)));
            resultDate.setData(responseMap);
        } else {
            responseMap.put("case", new ArrayList<UserAndCase>());
            resultDate.setData(responseMap);
        }

        return resultDate;
    }

    @ApiOperation(value = "我收藏的病例数量", notes = "")
    @ApiImplicitParam(name = "userId", value = "我的ID", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/{userId}/count", method = RequestMethod.GET)
    public ResultDate getMyCollectionCount(@PathVariable Integer userId) {
        ResultDate resultDate = new ResultDate();
        Map<String, Object> responseMap = new HashMap<>();

        resultDate.setCode(200);
        responseMap.put("likeCount", caseCollectionMapper.getMyCollectionCaseCount(userId));
        resultDate.setData(responseMap);

        return resultDate;
    }
}
