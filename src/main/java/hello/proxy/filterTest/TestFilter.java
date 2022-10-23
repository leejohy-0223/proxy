package hello.proxy.filterTest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException,
        ServletException {
        log.info("filter 호출!");
        request.getInputStream();

        HttpServletRequest request1 = (HttpServletRequest)request;

        log.info(request1.getRequestURI());

        ((HttpServletResponse)response).sendRedirect("/custom-error");
    }
}
