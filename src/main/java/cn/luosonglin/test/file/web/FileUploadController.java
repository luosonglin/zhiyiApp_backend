package cn.luosonglin.test.file.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luosonglin on 19/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/file")
public class FileUploadController {

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
               * 4、文件大小的限制;
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
    @ApiOperation(value = "批量上传文件", notes = "批量上传文件")
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

}
