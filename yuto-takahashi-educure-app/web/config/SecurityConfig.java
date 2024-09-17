package jp.co.hoge.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import jp.co.hoge.web.service.UserServiceImpl;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/register", "/home","/Password_Reset","/Budget_settings","/customization","/export_conimation","/export","/import_confirmation","/import","/input","/management","/registration_confirmation","/search_results","/search","/Side_Menu","/css/style.css","/images/**","/deleteAccount","/edit_account","/update","/static/**", "/js/**", "/css/**", "/api/summary","/edit-delete").permitAll()  // 認証不要のページ
                .anyRequest().authenticated()  // その他は認証が必要
            )
            .formLogin(login -> login
            	    .loginPage("/login")
            	    .permitAll()
            	    .defaultSuccessUrl("/home", true)  // ログイン成功時にホームにリダイレクト
            	    .failureUrl("/login?error=true")   // ログイン失敗時にエラーメッセージを表示
            	    .usernameParameter("email")        // email を使ってログイン
            	    .passwordParameter("password")
            		)

            .logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll()
            )
            .sessionManagement(session -> session
                .sessionFixation().migrateSession()  // セッション固定攻撃を防止
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserServiceImpl userService) {
        return userService;  // UserServiceImplをUserDetailsServiceとして使用
    }
}
