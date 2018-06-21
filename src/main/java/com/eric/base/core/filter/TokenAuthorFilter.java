package com.eric.base.core.filter;

import com.alibaba.fastjson.JSON;
import com.eric.base.core.JsonResult;
import com.eric.base.core.token.TokenManagerImpl;
import com.eric.base.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 描述: token验证
 *
 * @author eric
 * @create 2018-06-21 上午11:27
 */
@Slf4j
@Component
public class TokenAuthorFilter implements Filter {

    @Resource
    private TokenManagerImpl tokenManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = CookieUtil.getCookieValue("api-token", req);
        JsonResult result;
        boolean isFilter = false;
        if (StringUtils.isBlank(token)) {
            result = new JsonResult().fail("token验证失败：客户端请求参数中无token信息");
        } else {
            // 验证token是否合法
            boolean checkToken = tokenManager.checkToken(token);
            if (checkToken) {
                isFilter = true;
                result = new JsonResult().success();
            } else {
                result = new JsonResult().fail("token验证失败：token信息无效！");
            }
        }
        if (!result.getResult().isSuccess()) {
            PrintWriter writer = null;
            OutputStreamWriter osw = null;
            try {
                osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = JSON.toJSONString(result);
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
            } catch (UnsupportedEncodingException e) {
                log.error("过滤器返回信息失败:" + e.getMessage(), e);
            } finally {
                if (null != writer) {
                    writer.close();
                }
                if (null != osw) {
                    osw.close();
                }
            }
            return;
        }
        if (isFilter) {
            log.info("token filter过滤ok!");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}


