package com.panda.weatheralerts.weatherforecastreader.webcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panda.weatheralerts.weatherforecastreader.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UpdateAlertCriteria {

    private final UserService userService;

    @PostMapping("/updateAlertCriteria")
    public ResponseEntity<Object> updateAlertCriteria(@RequestBody ApplyRequest applyRequest) {
        userService.updateAlertCriteria(applyRequest.getEmail(), applyRequest.getWindMin(),
                applyRequest.getWindMax(), applyRequest.getTempMin(), applyRequest.getTempMax());
        return ResponseEntity.ok().build();
    }

    @GetMapping("user")
    public String helloUser() {
        return "Hello User";
    }

}
