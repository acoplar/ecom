package com.ecom.auth.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.auth.session.UserDetailsFacadeLocal;
import com.ecom.common.util.MsgBundleLoader;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsFacadeLocal userDetailsDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        com.ecom.auth.bean.User user = userDetailsDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(MsgBundleLoader.getMessage("error.login.username.not-found"));
        }

        @SuppressWarnings("unused")
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new GrantedAuthorityImpl(user.getAuthRole().getRoleName()));

        boolean enabled = String.valueOf(user.getActive()).equals("1") ? true : false;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        org.springframework.security.core.userdetails.User userDetails = new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return userDetails;
    }
}
