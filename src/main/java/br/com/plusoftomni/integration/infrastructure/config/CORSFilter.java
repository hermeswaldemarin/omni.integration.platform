package br.com.plusoftomni.integration.infrastructure.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CORSFilter extends OncePerRequestFilter {

    private static Pattern pattern = Pattern.compile("http:\\/\\/(.[^/]+)");
    private static Pattern pattern2 = Pattern.compile("https:\\/\\/(.[^/]+)");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        if(referer == null){
            referer = request.getHeader("origin");
        }
        if(referer!=null){
            Matcher matcher = pattern.matcher(referer);
            matcher.find();
            String tmpGroup = null;
            try{
                tmpGroup = matcher.group(0);
            }catch (IllegalStateException e){
                matcher = pattern2.matcher(referer);
                matcher.find();
            }
            response.addHeader("Access-Control-Allow-Origin", matcher.group(0));
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1");
        }

        filterChain.doFilter(request, response);
    }

}


