package app.controllers;

import app.dto.BookDTO;
import app.scrappers.BookScraper;
import app.scrappers.AteneoBookScraper;
import app.scrappers.CuspideBookScraper;
import app.services.SearchService;
import app.utils.Input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@RestController
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    /**
     * Metodo GET que devuelve todos los libros dados los parametros nombre, apellido y titulo de libro de un
     * autor.
     * @param authorName nombre del autor, utilizado para un filtro mas riguroso.
     * @param authorSurname apellido del autor, utilizado para un filtro mas riguroso.
     * @param fullTitle titulo del libro.
     * @throws Exception
     */
    @GetMapping("/search")
    public ResponseEntity<ArrayList<BookDTO>> searchPage(
            @RequestParam(name = "name", defaultValue = "", required = false) String authorName,
            @RequestParam(name = "surname", defaultValue = "", required = false) String authorSurname,
            @RequestParam(name = "title", required = true) String fullTitle
    ) throws Exception {
        return new ResponseEntity<>(searchService.searchBooks(authorName, authorSurname, fullTitle), HttpStatus.OK);
    }
}
