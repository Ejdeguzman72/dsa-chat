package com.dsa.chat.controller;

import com.dsa.chat.service.DsaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DsaUserController {
    @Autowired
    private DsaUserService dsaUserService;
}
