package io.dyno.mvp.controller;

import io.dyno.mvp.model.UserProfile;
import io.dyno.mvp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class SystemController {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register")
    @ResponseBody
    public UserProfile userRegister(
            @RequestParam(name = "username") final String username) {
        try {
            return userService.registerUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/login")
    @ResponseBody
    public UserProfile userLogin(
            @RequestParam(name = "pk") final String pk) {
        try {
            //return userService.registerUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<UserProfile> search() throws Exception {
        return userService.doSearch("test");
    }

}
