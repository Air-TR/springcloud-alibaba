package com.tr.customer.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 在服务提供者里配置这个类，在 Sentinel Dashboard 中配置的"授权规则"才会生效。
 *
 * @author TR
 * @date 2022/8/20 下午3:42
 */
@Component
public class HeaderOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        // 1.获取请求头
        String origin = request.getHeader("origin");
        // 2.非空判断
        if (!StringUtils.hasText(origin)) {
            origin = "blank";
        }
        return origin;
    }

}
