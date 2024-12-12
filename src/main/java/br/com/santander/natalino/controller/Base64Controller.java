package br.com.santander.natalino.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api")
public class Base64Controller {

    @PostMapping("/decode")
    public String decodeBase64(@RequestBody String base64) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            return "Erro: Texto inválido em Base64!";
        }
    }
}