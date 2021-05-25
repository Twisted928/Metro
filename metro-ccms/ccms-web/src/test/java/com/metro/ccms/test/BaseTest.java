package com.metro.ccms.test;

import com.metro.ccms.common.core.domain.entity.SysUser;
import com.metro.ccms.common.core.domain.model.LoginUser;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 11:19 2020/12/1
 * @Modified By:
 */
@SpringBootTest
@Rollback
@AutoConfigureMockMvc
public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        LoginUser loginUser = new LoginUser();
        SysUser sysUser = new SysUser();
        sysUser.setNickName("admin");
        sysUser.setUserName("admin");
        sysUser.setUserId(1L);
        loginUser.setUser(sysUser);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
