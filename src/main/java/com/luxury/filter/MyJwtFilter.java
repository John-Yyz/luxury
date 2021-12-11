package com.luxury.filter;

import javax.servlet.annotation.WebFilter;

/**
 * 描述：验签处理，只负责Spring注入，具体实现在JwtFilter处理
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/12/4 2:22
 */
@WebFilter(filterName = "jwtFilter")
public class MyJwtFilter extends JwtFilter {
}
