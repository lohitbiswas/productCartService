package com.indusnet.ums.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.indusnet.ums.util.ObjectIdTypeAdapter;
import com.indusnet.ums.common.MessageTypeConst;
import com.indusnet.ums.common.LoggingResponseModel;
import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indusnet.ums.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ums/")
@Slf4j
public class UserController {

	
	@Autowired
    Gson gson;

    @Autowired
    IUserService service;

    Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> register(@RequestBody String request){

        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.add(customGson.fromJson(request, UserModel.class));
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End Add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));

        return new ResponseEntity<ResponseModel>(response, status);
    }
    
    
}
