package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.mapper.AvatarMapper;
import com.am.adastra.service.UserService;
import com.am.adastra.util.POJOUtils;
import com.am.adastra.util.wx.ConstantPropertiesUtil;
import com.am.adastra.util.wx.HttpClientUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author 马强
 * @Description
 * @create 2022-07-04 10:47
 */
@Slf4j
@Api(tags = "微信登录模块")
@Controller
@RequestMapping("/api/ucenter/wx")
public class WXController {

    @Resource
    private AvatarMapper avatarMapper;

    @Resource
    private UserService userService;


    @ApiOperation("微信登录回调")
    @GetMapping("callback")
    public String callback(String code, String state) {

        //得到授权临时票据code
        System.out.println(code);
        System.out.println(state);

        //从redis中将state获取出来，和当前传入的state作比较
        //如果一致则放行，如果不一致则抛出异常：非法访问

        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                ConstantPropertiesUtil.WX_OPEN_APP_SECRET,
                code);

        String result = null;
        try {
            result = HttpClientUtils.get(accessTokenUrl);
            System.out.println("accessToken=============" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取token失败");
        }

        //解析json字符串
        Gson gson = new Gson();
        HashMap map = gson.fromJson(result, HashMap.class);
        String accessToken = (String)map.get("access_token");
        String openid = (String)map.get("openid");
        try {
            //查询数据库当前用用户是否曾经使用过微信登录
            Long userId = userService.getUserDaoByID(openid);
            UserDBO member = userService.getDBOById(userId);
            if(member == null){
                System.out.println("新用户注册");

                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
                String resultUserInfo = null;
                try {
                    resultUserInfo = HttpClientUtils.get(userInfoUrl);
                    System.out.println("resultUserInfo==========" + resultUserInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("获取用户信息错误");
                }

                //解析json
                HashMap<String, Object> mapUserInfo = gson.fromJson(resultUserInfo, HashMap.class);
                String nickname = (String)mapUserInfo.get("nickname");  //昵称
                String headimgurl = (String)mapUserInfo.get("headimgurl"); //对象

                //向数据库中插入一条记录
                 User user = new User();
                user.setUsername(nickname);
                user.setPassword(DigestUtils.md5Hex("12345678"));
                user.setAccount("微信");
                user.setState("normal");
                System.out.println(user.toString());
                User wxuser = userService.register(user);
                avatarMapper.addAvatar(wxuser.getId(),headimgurl);
            }

            return "redirect:http://localhost:8080";
        } catch ( JsonSyntaxException e) {
            e.printStackTrace();
            System.out.println("登陆失败");
        }
        return "错误";
    }

    @ApiOperation("微信登录")
    @GetMapping("/wxLogin")
    public String getWxCode() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 回调地址
        String redirectUrl = ConstantPropertiesUtil.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("登陆错误");
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "imhelen";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        System.out.println("state = " + state);

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟
        //请求微信地址

        String qrcodeUrl = String.format(
                baseUrl,
                ConstantPropertiesUtil.WX_OPEN_APP_ID,
                redirectUrl,
                state);

        return "redirect:" + qrcodeUrl;
    }

}
