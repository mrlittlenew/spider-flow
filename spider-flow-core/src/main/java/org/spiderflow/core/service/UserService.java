package org.spiderflow.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spiderflow.core.mapper.UserMapper;
import org.spiderflow.core.model.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService {

    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.baseMapper.selectOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        String password = user.getPassword();

        // 匹配成功则用其生成加密后的密文传入 UserDetails
        // 这个 commaSeparatedStringToAuthorityList 就是把输入的字符串根据逗号分割成 List<GrantedAuthority>
        return new org.springframework.security.core.userdetails.User(username, password, AuthorityUtils
                .commaSeparatedStringToAuthorityList("default"));
    }
}
