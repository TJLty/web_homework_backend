package lty.web2021.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.Data;
import lty.web2021.backend.model.Message;
import lty.web2021.backend.service.MessageService;
import lty.web2021.backend.service.OSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    OSSService ossService;
    @RequestMapping("/{fid}")
    public SaResult getMessages(@PathVariable("fid") Integer fid){
        Integer uid= StpUtil.getLoginIdAsInt();
        List<Message> messages=messageService.getFriendMessages(uid,fid);
        return SaResult.ok().setData(messages);
    }
    @RequestMapping(method = RequestMethod.POST)
    public SaResult addTextMessage(@RequestBody TextMessage message){
        System.out.println(message.content);
        System.out.println(message.to);
        Integer uid=StpUtil.getLoginIdAsInt();
        messageService.addTextMessage(message.content,uid, message.to);
        return SaResult.ok();
    }
    @RequestMapping(value = "files",method = RequestMethod.POST)
    public SaResult addFileMessage(@RequestBody TextMessage message){
        System.out.println(message.content);
        System.out.println(message.to);
        Integer uid=StpUtil.getLoginIdAsInt();
        messageService.addFileMessage(message.content,uid, message.to);
        return SaResult.ok();
    }
    @RequestMapping(path = "{id}",method = RequestMethod.DELETE)
    public SaResult deleteMessage(@PathVariable("id") Integer id){
        messageService.deleteMessage(id);
        return SaResult.ok();
    }

}
@Data
class TextMessage implements Serializable {
    String content;
    Integer to;
}
