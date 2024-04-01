package apiemailsender.controllers;

import apiemailsender.model.Email;
import apiemailsender.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService service;

    @PostMapping
    public ResponseEntity<Email> sendEmail(@RequestBody Email email){

        service.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
