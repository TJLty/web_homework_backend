package lty.web2021.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lty.web2021.backend.model.Friend;
import lty.web2021.backend.model.Message;
import lty.web2021.backend.service.FriendService;
import lty.web2021.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("friends")
public class FriendController {
    @Autowired
    FriendService friendService;
    @Autowired
    MessageService messageService;

    @RequestMapping()
    public SaResult getFriends(@RequestParam(required = false) String filter){
        Integer uid=StpUtil.getLoginIdAsInt();
        List<Friend> friends=friendService.getFriends(filter,uid);
        return SaResult.ok().setData(friends);
    }
    @CrossOrigin(originPatterns = "*",allowCredentials = "true")
    @RequestMapping("/{fid}/messages")
    public SaResult getMessages(@PathVariable("fid") Integer fid){
        Integer uid= StpUtil.getLoginIdAsInt();
        List<Message> messages=messageService.getFriendMessages(uid,fid);
        return SaResult.ok().setData(messages);
    }
    @RequestMapping(method = RequestMethod.POST)
    public SaResult addFriend(@RequestParam("fid") Integer fid){
        Integer uid= StpUtil.getLoginIdAsInt();
        try{
            friendService.addFriends(uid,fid);
            return SaResult.ok("You are friends now!");
        }catch (Exception e){
            if(Objects.equals(e.getMessage(), "No such user")) {
                return SaResult.error("No such user!");
            }else{
                return SaResult.error("Already friends!");
            }
        }
    }
    @RequestMapping(method=RequestMethod.DELETE)
    public SaResult deleteFriend(@RequestParam("fid") Integer fid){
        Integer uid= StpUtil.getLoginIdAsInt();
        friendService.deleteFriends(uid,fid);
        return SaResult.ok();
    }



}
