package com.li.demo51moban.controller;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.google.zxing.qrcode.encoder.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

@Controller
public class VIPcontroller {
    //处理具体请求
    @RequestMapping("/tv")
    public String hello( Model model ){
        model.addAttribute("user","李四");
        return "test";
    }

    //处理具体请求
    @RequestMapping("/test5")
    public String toPlay( Model model ){
        model.addAttribute("tip","李四");
        return "play";
    }

    //处理播放功能
    @RequestMapping("/doPlay")
    public String doPlay( String url , boolean isQR, HttpServletResponse response ){
        //判断
        if ( isQR ){
            //扫码看
            //准备数据
            String playUrl= "https://jx.blbo.cc:4433/?url="+url;
            //创建图片
            BufferedImage image=QrCodeUtil.generate( playUrl,400,400);
            //输出(HttpServletResponse)
            try {
                ImageIO.write( image,"png", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
        //在线看
        {
            return "redirect:https://jx.blbo.cc:4433/?url="+url;
        }
        return "";
    }



}

