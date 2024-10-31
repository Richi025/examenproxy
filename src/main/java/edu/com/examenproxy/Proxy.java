package edu.com.examenproxy;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Proxy {

    @Autowired
    private ServiceProxy serviceProxy;

    @GetMapping("/proxy/linearSearch")
    private String linearSearch(@RequestParam String list, @RequestParam String value) throws IOException{
        return serviceProxy.conection("linearSearch", list, value);
    }

    @GetMapping("/proxy/binarySearch")
    private String binarySearch(@RequestParam String list, @RequestParam String value) throws IOException{
        return serviceProxy.conection("binarySearch", list, value);
    }


}
