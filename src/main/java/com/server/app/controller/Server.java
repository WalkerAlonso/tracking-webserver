package com.server.app.controller;

import com.server.app.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
public class Server {
    final private String pathFile = "tmp/";
    final private String fileName = "ok";
    final private String imagePath = "resource/image.gif";


    //Like a healthcheck utility
    @GetMapping("/ping")
    public ResponseEntity<?> ping(){
        try{
            if (!Utils.checkFileExist(pathFile,fileName)){
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable");
            }
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while checking for the file");
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


    @RequestMapping(path = "/img", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response) throws IOException {
        File file = new File(imagePath);
        if(file.exists()) {
            String contentType = "application/octet-stream";
            response.setContentType(contentType);
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            // copy from in to out
            IOUtils.copy(in, out);
            out.close();
            in.close();
            log.info("[/img] Image Endpoint Called");
        }else {
            throw new FileNotFoundException();
        }
    }
}
