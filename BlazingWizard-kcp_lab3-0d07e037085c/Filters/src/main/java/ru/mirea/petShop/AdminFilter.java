package ru.mirea.petShop;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminFilter {
    static void CheckUserToken(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Token token = TokenValidator.Validate(servletRequest);

        if (token != null){
            if(token.getRole().equals("admin")){
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

    }
}
