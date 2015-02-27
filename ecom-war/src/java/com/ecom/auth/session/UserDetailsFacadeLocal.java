package com.ecom.auth.session;

import com.ecom.auth.bean.User;

public interface UserDetailsFacadeLocal {

    User findByUsername(String username);
}
