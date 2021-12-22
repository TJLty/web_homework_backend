package lty.web2021.backend.controller;

import cn.dev33.satoken.util.SaResult;
import lty.web2021.backend.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("files")
public class FileController {
    @Autowired
    OSSService ossService;
    @RequestMapping(method= RequestMethod.POST)
    public SaResult uploadFile(@RequestBody MultipartFile file){
        String filename=ossService.uploadFile(file,"file");
        String path=ossService.getBaseUrl()+filename;
        return SaResult.ok().setData(path);
    }
}
