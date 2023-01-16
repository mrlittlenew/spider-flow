package org.spiderflow.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的UserDetailsService实现
 */
@Service
@ConditionalOnMissingBean(UserDetailsService.class)
public class DefaultUserService implements UserDetailsService {

    /**
     * 临时使用 Map 替代
     */
    private static final Map<String, String> DATES;

    static {
        DATES = new HashMap<>();
        // username, password
        DATES.put("admin", "admin");
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String rawPassword = DATES.get(username);

        // 这里模拟去数据库查询
        if (rawPassword == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 匹配成功则用其生成加密后的密文传入 UserDetails
        String password = passwordEncoder.encode(rawPassword);
        // 这个 commaSeparatedStringToAuthorityList 就是把输入的字符串根据逗号分割成 List<GrantedAuthority>
        return new User(username, password, AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin,normal"));
    }
}
