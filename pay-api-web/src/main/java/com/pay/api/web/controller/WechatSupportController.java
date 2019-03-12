package com.pay.api.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author chenwei
 * @date 2019/3/12 14:52
 */
@Controller
@RequestMapping("/wechat/support")
public class WechatSupportController {

    /**
     * 微信文件下载认证
     * (注：主要是用于公众号的网页授权域名验证，可参见简付宝公众号中的：【设置】-【公众号设置】-【功能设置】-网页授权域名)
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/{fileName:.+}", method = RequestMethod.GET)
    public void weixinTxt(HttpServletResponse response, @PathVariable String fileName) throws Exception {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("/").getPath() + "/wx/" + fileName);
        if (file.exists()) {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] by = new byte[fileInputStream.available()];
            fileInputStream.read(by);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(by);
            fileInputStream.close();
            outputStream.close();
        }
    }




}
