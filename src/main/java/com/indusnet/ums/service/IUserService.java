package com.indusnet.ums.service;


import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;

import jakarta.validation.Valid;

public interface IUserService {

	ResponseModel add(@Valid UserModel model);
}
