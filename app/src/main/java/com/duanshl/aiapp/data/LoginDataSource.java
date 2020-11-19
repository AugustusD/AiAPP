package com.duanshl.aiapp.data;

import com.duanshl.aiapp.Utils.LoginService;
import com.duanshl.aiapp.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication


           LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);

            /**
             * 使用自己开发的服务器，rest风格的登录验证
             */
            LoginService loginService = new LoginService();
            String res = loginService.login(username, password);
            if (res.matches("OK")){
                return new Result.Success<>(fakeUser);
            } else  {

            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }


        /**
         * 以下代码啥也不是
         */
        LoggedInUser fakeUser =
                new LoggedInUser(
                        java.util.UUID.randomUUID().toString(),
                        username);
        return new Result.Success<>(fakeUser);
    }

    public void logout() {
        // TODO: revoke authentication
    }
}