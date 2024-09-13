package com.xxyy.quandaiban1.application.service;

import com.xxyy.quandaiban1.application.dtos.UserLoginInput;
import com.xxyy.quandaiban1.application.dtos.UserLoginOutput;



/**
 * @author xy
 * @date 2023-09-25 15:42
 */

public interface UserService {

    public UserLoginOutput login(UserLoginInput input);

    Boolean joinToGroupBy(String invited);
}
