package br.com.zupacademy.mercadolivre.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {

    public List<String> envia(List<MultipartFile> files);
    
}
