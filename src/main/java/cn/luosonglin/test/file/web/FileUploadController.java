package cn.luosonglin.test.file.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luosonglin on 19/12/2016.
 * 参考 http://blog.csdn.net/daniel7443/article/details/51620308
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/file")
public class FileUploadController {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${cn.luosonglin.test.file.path}")
    private String filePath;

    /**
     * 文件上传具体实现方法;
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "form")
    })
    @RequestMapping(value = "/singleFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultDate handleFileUpload(@RequestParam("file") MultipartFile file) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        String fileName = null;

        if (!file.isEmpty()) {
            try {
              /*
               * 3、文件格式;
               */
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
                out.write(bytes);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败, " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败, " + e.getMessage());
            }
            resultDate.setCode(200);
            resultDate.setData("上传成功");
            return resultDate;
        } else {
            throw new CustomizedException("上传失败，因为文件是空的.");
        }
    }

    /**
     * 多文件上传具体实现方法;
     *
     * @param files
     * @return
     */
    @ApiOperation(value = "批量上传文件(该API不用swagger2测试，swagger不支持多文件上传)", notes = "批量上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "form")
    })
    @RequestMapping(value = "/multipartFile", method = RequestMethod.POST)
    @ResponseBody
    public ResultDate handleMoreFileUpload(@RequestParam("file") MultipartFile[] files) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        String fileName = null;
        if (files != null & files.length > 0) {
            for (MultipartFile i : files) {
                try {
                    fileName = i.getOriginalFilename();
                    byte[] bytes = i.getBytes();

                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath + fileName)));
                    out.write(bytes);
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    throw new CustomizedException("上传失败," + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new CustomizedException("上传失败," + e.getMessage());
                } catch (Exception e) {
                    throw new CustomizedException("上传" + fileName +  "失败," + e.getMessage());
                }
            }
        } else{
            throw new CustomizedException("上传失败，因为文件是空的.");
        }
        resultDate.setCode(200);
        resultDate.setData("上传成功");
        return resultDate;
    }

    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @ApiOperation(value = "读取上传文件中得所有文件并返回", notes = " ")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultDate list() {
        ResultDate resultDate = new ResultDate();
        Map<Object, Object> response = new HashMap<>();

        File uploadDest = new File(filePath);
        String[] fileNames = uploadDest.list();

        response.put("files", fileNames);
        response.put("path", filePath);
        resultDate.setCode(200);
        resultDate.setData(response);

        return resultDate;
    }

    private  ResourceLoader resourceLoader;
    public static final String ROOT = "upload-dir";

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
