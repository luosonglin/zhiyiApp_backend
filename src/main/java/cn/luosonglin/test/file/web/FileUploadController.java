package cn.luosonglin.test.file.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/file2")
public class FileUploadController {


    //访问路径为：http://127.0.0.1:8080/file
    @RequestMapping("/file")
    public String file(){
        return"/file";
    }

    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件2", notes = "上传文件2")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "图片name", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "form")
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultDate handleFileUpload(@RequestParam("file")MultipartFile file) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        if(!file.isEmpty()){
            try {
              /*
               * 这段代码执行完毕之后，图片上传到了工程的跟路径；
               * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
               * 等等;
               * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
               * 1、文件路径；
               * 2、文件名；
               * 3、文件格式;
               * 4、文件大小的限制;
               */
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败, "+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败, "+e.getMessage());
            }
            resultDate.setCode(200);
            resultDate.setData("上传成功");
            return resultDate;
        }else{
            throw new CustomizedException("上传失败，因为文件是空的.");
        }
    }

    /**
     * 多文件上传具体实现方法;
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件22", notes = "上传文件2")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "图片name", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "files", value = "file", required = true, dataType = "file", paramType = "form")
    })
    @RequestMapping(value = "/uploadmore", method = RequestMethod.POST)
    @ResponseBody
    public ResultDate handleMoreFileUpload(@RequestParam("files")MultipartFile[] file) throws CustomizedException {
        ResultDate resultDate = new ResultDate();
        if(!file[0].isEmpty()){
            try {
              /*
               * 这段代码执行完毕之后，图片上传到了工程的跟路径；
               * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
               * 等等;
               * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
               * 1、文件路径；
               * 2、文件名；
               * 3、文件格式;
               * 4、文件大小的限制;
               */
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file[0].getOriginalFilename())));
                out.write(file[0].getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败,"+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomizedException("上传失败,"+e.getMessage());
            }
            resultDate.setCode(200);
            resultDate.setData("上传成功");
            return resultDate;
        }else{
            throw new CustomizedException("上传失败，因为文件是空的.");
        }
    }
}
