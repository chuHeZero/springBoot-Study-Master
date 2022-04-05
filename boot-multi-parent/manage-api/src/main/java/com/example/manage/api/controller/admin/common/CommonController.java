package com.example.manage.api.controller.admin.common;

import cn.hutool.core.map.MapUtil;
import com.example.manage.common.ICommonBizService;
import com.example.manage.domain.ResultData;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.example.manage.constant.Constant.CAPTCHA_KEY;

/**
 * @author zzm
 * @date 2022年04月04日 21:10
 */
@Api(value = "系统接口", tags = "系统接口")
@Controller
@RequiredArgsConstructor
public class CommonController extends BaseController {

    private final Producer producer;

    private final ICommonBizService commonBizService;

    @GetMapping("/captcha")
    public ResultData Captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        // 随机码作为key，验证码作为value，用hset存入redis的HASH数据结构中，有效期为120s
        commonBizService.cacheSet(CAPTCHA_KEY + key, code, 120);
        return success(MapUtil.builder().put("userKey", key).put("captcherImg", base64Img).build());
    }

    @GetMapping("/adminLogin")
    public String login() {
        return "login/adminLogin";
    }

}
