package the.biagi.crawler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.biagi.crawler.model.Filme;
import the.biagi.crawler.service.FilmeScraping;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeScraping filmeScraping;

    @GetMapping
    public ResponseEntity<List<Filme>> getFilmes() throws IOException {
        List<Filme> filmes = filmeScraping.criandoObjeto();
        if(!filmes.isEmpty()){
            return ResponseEntity.ok(filmes);
        }
        return ResponseEntity.noContent().build();
    }
}
