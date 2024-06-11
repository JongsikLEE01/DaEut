package com.daeut.daeut.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.daeut.daeut.auth.service.LoginSuccessHandler;
import com.daeut.daeut.auth.service.OAuthService;
import com.daeut.daeut.auth.service.UserDetailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private OAuthService oAuthService;

    // 스프링 시큐리티 설정 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // ✅ 인가 설정
        http.authorizeRequests(requests -> requests
                                            .antMatchers("/admin/join").permitAll() // 관리자 회원가입 페이지는 누구나 접근 가능
                                            // .antMatchers("/admin/**").hasRole("ADMIN")
                                            .antMatchers("/partner/**").hasAnyRole("PARTNER", "ADMIN")
                                            .antMatchers("/user/**").hasAnyRole("USER", "PARTNER", "ADMIN")
                                            .antMatchers("/auth/**", "/").permitAll()
                                            .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                                            .antMatchers("/**").permitAll()
                                            .anyRequest().authenticated()
                                    );

        // 🔐 폼 로그인 설정
        // ✅ 커스텀 로그인 페이지
        http.formLogin(login -> login
                                    .loginPage("/auth/login")
                                    .loginProcessingUrl("/login")
                                    .usernameParameter("userId")
                                    .passwordParameter("userPassword")
                                    .failureUrl("/auth/login?error=true")
                                    .successHandler( authenticationSuccessHandler() )
                                    .permitAll()
                            );

       // 👩‍💻🔐 OAuth2 로그인 
        // ✅ userInfoEndpoint()            : 사용자 정보 설정 객체 가져오기
        // ✅ userService(oAuthService)     : 사용자 정보 설정 객체로, 로그인 후 처리할 구현 클래스 등록
        // ✅ loginPage(경로)               : 커스텀 로그인 페이지 경로 지정
        http.oauth2Login(login -> login
                                    .successHandler(authenticationSuccessHandler())
                                    .loginPage("/login")
                                    .userInfoEndpoint() 
                                    .userService(oAuthService)
                                    );

        // ✅ 사용자 정의 인증 설정
        http.userDetailsService(userDetailServiceImpl);

        // 🔄 자동 로그인 설정
        http.rememberMe(me -> me.key("aloha")
                                .tokenRepository(tokenRepository())
                                .tokenValiditySeconds(60 * 60 * 24 * 7)
                                .authenticationSuccessHandler(authenticationSuccessHandler())
                                );

        http.logout(logout -> logout.invalidateHttpSession(true));

        return http.build();
    }

    /**
    * 🍃 자동 로그인 저장소 빈 등록
    * ✅ 데이터 소스
    * ⭐ persistent_logins 테이블 생성
            create table persistent_logins (
                username varchar(64) not null
                , series varchar(64) primary key
                , token varchar(64) not null
                , last_used timestamp not null
            );
    * 🔄 자동 로그인 프로세스
    * ✅ 로그인 시 
    *     ➡ 👩‍💼(ID, 시리즈, 토큰) 저장
    * ✅ 로그아웃 시, 
    *     ➡ 👩‍💼(ID, 시리즈, 토큰) 삭제
    * @return
    */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        // JdbcTokenRepositoryImpl : 토큰 저장 데이터 베이스를 등록하는 객체
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        // ✅ 토큰 저장소를 사용하는 데이터 소스 지정
        // - 시큐리티가 자동 로그인 프로세스를 처리하기 위한 DB를 지정합니다.
        repositoryImpl.setDataSource(dataSource);   
        // persistent_logins 테이블 생성
        try {
            repositoryImpl.getJdbcTemplate().execute(JdbcTokenRepositoryImpl.CREATE_TABLE_SQL);
        } 
        catch (BadSqlGrammarException e) {
            log.error("persistent_logins 테이블이 이미 존재합니다.");   
        }
        catch (Exception e) {
            log.error("자동 로그인 테이블 생성 중 , 예외 발생");
        }
        return repositoryImpl;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    } 
    
}