package br.com.zupacademy.mercadolivre.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


//Gerador de link fake
@Component
@Primary // essa será a primeira opcao quando executado, para trocar para outros ambientes podemos usar o @Profile
public class UploaderFake implements Uploader{

    @Override
    public List<String> envia(List<MultipartFile> files) {
        return files.stream()
                    .map(file -> "Http://storageFake.io/"
                     + file.getOriginalFilename())
                     .collect(Collectors.toList());
    
    }

}

   