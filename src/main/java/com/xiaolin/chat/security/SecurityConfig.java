package com.xiaolin.chat.security;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaolin.chat.model.Response;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;

/**
 * UserDetailsService循环依赖问题，实现UserDetailsService接口即可解决该问题
 * @author xlxing
 */
@Configuration
@AllArgsConstructor
public class SecurityConfig{
    private final ObjectMapper mapper;
    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆页面: /index.html
     * 登陆请求: POST /login {username, password}
     * 登陆请求:
     * 使用Bean的方式实现http协议安全保护
      */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 开启登陆配置
                .authorizeHttpRequests()
                // 对于登陆和js资源放行
                .requestMatchers("/api/users", "/js/**").permitAll()
                // 表示所有接口，登陆之后就能访问
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/index.html")
                // 登陆处理接口
                .loginProcessingUrl("/login")
                // 定义登陆时，用户名的key，默认为username
                .usernameParameter("username")
                // 定义登陆时，用户密码的key，默认为password
                .passwordParameter("password")
                // 登陆成功处理器
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(mapper.writeValueAsString(Response.ok()));
                    out.flush();
                })
                // 登陆失败的处理器
                .failureHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(mapper.writeValueAsString(Response.failed("登陆失败" + this.getClass().getName())));
                    out.flush();
                })
                // 和表单相关的接口可以不受限制直接登陆
                .permitAll().and()
                .logout()
                .logoutUrl("/logout")
                // 登出成功的处理程序
                .logoutSuccessHandler(((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(mapper.writeValueAsString(Response.ok()));
                    out.flush();
                }))
                .permitAll().and()
                .httpBasic().disable()
                .csrf().disable()
                .userDetailsService(userDetailsService);
        return http.build();
    }

    /**
     * 忽略URL请求
     * 该请求并不安全
     * @return 默认返回值
     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/users", "/js/**");
//    }

}
