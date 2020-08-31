package com.ilong.wehrservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilong.wehrservice.model.Hr;
import com.ilong.wehrservice.model.RespBean;
import com.ilong.wehrservice.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TOOD
 *
 * @author long
 * @date 2020-06-14 17:17
 */
@Configuration
public class SevurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(
                        new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
                            throws IOException, ServletException {
                            //登陆成功返回json
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out=resp.getWriter();
                        //将authentication 里的用户强转成hr对象
                        Hr hr = (Hr)authentication.getPrincipal();
                        hr.setPassword(null);
                        //拼接信息 。
                        RespBean ok = RespBean.ok("登录成功", hr);
                        //将hr对象转换成字符串
                        String s = new ObjectMapper().writeValueAsString(ok);
                        //将hr字符串写出去
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e)
                            throws IOException, ServletException {
                        //登陆失败返回json
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespBean respBean = RespBean.error("登录失败！");
                        if (e instanceof LockedException){
                            respBean.setMsg("账户被锁定，请联系管理员");
                        }else  if (e instanceof CredentialsExpiredException){
                            respBean.setMsg("密码过期，请联系管理员");
                        }else  if (e instanceof AccountExpiredException){
                            respBean.setMsg("账户过期，请联系管理员");
                        }else  if (e instanceof DisabledException){
                            respBean.setMsg("账户被禁用，请联系管理员");
                        }else  if (e instanceof BadCredentialsException){
                            respBean.setMsg("用户名或密码输入错误，请重新输入");
                        }

                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest rep, HttpServletResponse resp, Authentication authentication)
                            throws IOException, ServletException {
                         resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                //没有认证时，在这里处理结果，不重定向 。
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest rep, HttpServletResponse resp, AuthenticationException e) throws
                    IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                resp.setStatus(401);
                PrintWriter out = resp.getWriter();
                RespBean respBean = RespBean.error("访问失败！");
                if (e instanceof InsufficientAuthenticationException){
                    respBean.setMsg("请求失败，请联系管理员");
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });

    }
}
