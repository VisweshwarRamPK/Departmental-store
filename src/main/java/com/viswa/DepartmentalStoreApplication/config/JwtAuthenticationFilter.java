package com.viswa.DepartmentalStoreApplication.config;

import com.viswa.DepartmentalStoreApplication.model.User;
import com.viswa.DepartmentalStoreApplication.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;


    @Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        @Autowired
        private JwtService jwtService;

        @Autowired
        private UserService userService;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String header=request.getHeader("Authorization");
            if(header==null || !header.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }
            String jwt=header.split(" ")[1];
            String userName=jwtService.ExtractUsername(jwt);
//            HERE WE CHECK WEATHER THE USER IS PRESENT OR NOT
            Optional<User> oUser= userService.findByUserName(userName);
            if(oUser.isPresent()){
                User user =oUser.get();
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userName,null, user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            else {
//      THIS PART IS TO HANDLE THE USER WHICH IS NOT FOUND
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            filterChain.doFilter(request, response);
        }
    }

