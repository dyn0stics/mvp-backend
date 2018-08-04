package io.dyno.mvp.controller;

import io.dyno.mvp.model.UserProfile;
import io.dyno.mvp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SystemController {

    private static final Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/register")
    @ResponseBody
    @CrossOrigin
    public UserProfile userRegister(
            @RequestParam(name = "username") final String username,
            @RequestParam(name = "age", required = false) final String age,
            @RequestParam(name = "weight", required = false) final String weight,
            @RequestParam(name = "gender", required = false) final String gender,
            @RequestParam(name = "country", required = false) final String country) {
        try {
            return userService.registerUser(username, age, weight, gender, country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/user/profile")
    @ResponseBody
    @CrossOrigin
    public UserProfile userLogin(
            @RequestParam(name = "pk") final String pk) {
        try {
            return userService.getUser(pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    @CrossOrigin
    public List<UserProfile> search() throws Exception {
        return userService.doSearch("test");
    }

}
