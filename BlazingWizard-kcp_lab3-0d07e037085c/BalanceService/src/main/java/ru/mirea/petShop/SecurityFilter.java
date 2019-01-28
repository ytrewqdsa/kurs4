package ru.mirea.petShop;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AdminFilter.CheckUserToken(servletRequest, servletResponse, filterChain);
    }

    @Override
    public void destroy() {

    }
}
