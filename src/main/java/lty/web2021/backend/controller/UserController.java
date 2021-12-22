package lty.web2021.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lty.web2021.backend.model.Friend;
import lty.web2021.backend.model.User;
import lty.web2021.backend.service.FriendService;
import lty.web2021.backend.service.OSSService;
import lty.web2021.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FriendService friendService;
    @Autowired
    OSSService ossService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public SaResult login(@RequestBody User user){
        System.out.println(user.getUId());
        System.out.println(user.getPassword());
        if(userService.checkPassword(user.getUId(), user.getPassword())){
            StpUtil.login(user.getUId());
            return SaResult.ok("登陆成功").setData(StpUtil.getTokenInfo());
        }else{
            return SaResult.error("登陆失败");
        }
    }

    @RequestMapping()
    public SaResult getInfo(){
        User user=userService.getInformation(StpUtil.getLoginIdAsInt());
        if(user==null)
            return SaResult.error();
        else
            return SaResult.ok().setData(user);
    }


    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public SaResult uploadImage(@RequestBody MultipartFile file){
        String filename=ossService.uploadImage(file,"img");
        String path=ossService.getBaseUrl()+filename;
        userService.changeImage(path,StpUtil.getLoginIdAsInt());
        return SaResult.ok();
    }

    @RequestMapping(method = RequestMethod.POST)
    public SaResult addUser(@RequestBody User user){
        userService.addUser(user);
        return SaResult.ok("注册成功").setData(user.getUId());
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public SaResult updateUser(@RequestBody User user){
        user.setUId(StpUtil.getLoginIdAsInt());
        userService.updateUser(user);
        return SaResult.ok("修改成功");
    }

    @RequestMapping(value = "/groups",method = RequestMethod.POST)
    public SaResult addGroup(@RequestParam("name") String name) throws Exception {
        Integer uid=userService.addGroup(name);
        friendService.addFriends(StpUtil.getLoginIdAsInt(),uid);
        return SaResult.ok();
    }

}
