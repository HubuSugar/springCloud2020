package edu.hubu.springcloud.controller;

import edu.hubu.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Sugar  2020/12/6 20:46
 */
@RestController
public class StreamProviderController {


    @Resource
    private IMessageProvider messageProvider;
    
    
    @GetMapping("/sendMessage")
    public String messageSend(){
        String message = messageProvider.send();
        return message;
    }

}
