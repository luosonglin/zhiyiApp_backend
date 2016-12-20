package cn.luosonglin.test.file.web;

import cn.luosonglin.test.base.entity.ResultDate;
import cn.luosonglin.test.exception.CustomizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by luosonglin on 16/12/2016.
 */
@RestController
@RequestMapping(value = "/${cn.luosonglin.test.project.type}/${cn.luosonglin.test.project.version}/file")
public class FileController {

    private static final Logger logger = LogManager.getLogger(FileController.class);

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "图片name", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "file", value = "file", required = true, dataType = "file", paramType = "form")
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultDate handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws CustomizedException {
        if (file.isEmpty())
            throw new CustomizedException("the file was empty");

        ResultDate resultDate = new ResultDate();
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
            stream.write(bytes);
            stream.close();

            resultDate.setCode(200);
            resultDate.setData("You successfully uploaded " + name + " into " + name + "-uploaded !");


        } catch (Exception e) {
            throw new CustomizedException("You failed to upload " + name + " => " + e.getMessage());

        }

        return resultDate;
    }

   /* @RequestMapping(value="upload",method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam("file")CommonsMultipartFile file){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setContentType(file.getContentType());
        fileInfo.setCreateTime(new Date());
        fileInfo.setFilename(file.getOriginalFilename());
        fileInfo.setFilesize(file.getSize());
        if(!file.isEmpty()){
            try{
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
                String dir = simpleDateFormat.format(date);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMHHmmss");
                String disk_filename = dateFormat.format(date) + "_" + file.getOriginalFilename();

                fileInfo.setDiskDirectory(dir);
                fileInfo.setDiskFileanme(disk_filename);

                String savePath = path + "/" + dir + "/" +disk_filename;
                File saveFile = new File(savePath);
                if(!saveFile.exists()){
                    FileUtils.touch(saveFile);
                }

                file.transferTo(saveFile);
            }catch (IOException e){
                logger.error(e.getMessage(),e);
            }
            fileInfoService.insert(fileInfo);
            OperateResult result = new OperateResult(ErrorCodes.ERR_CODE_SUCCESS,"上传成功");
            Map<String,Object> data = new HashMap<>();
            data.put("fileId",fileInfo.getId());
            result.setData(data);

            return result;
        }else{
            return  new OperateResult(ErrorCodes.ERR_CODE_SYSTEM,"文件不能为空");
        }

    }*/

}
