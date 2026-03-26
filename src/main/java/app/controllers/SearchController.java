package app.controllers;

import app.dto.BookDTO;
import app.scrappers.BookScraper;
import app.scrappers.AteneoBookScraper;
import app.scrappers.CuspideBookScraper;
import app.utils.Input;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@RestController
public class SearchController {

    /**
     * Metodo GET que devuelve todos los libros dados los parametros nombre, apellido y titulo de libro de un
     * autor.
     * @param authorName nombre del autor, utilizado para un filtro mas riguroso.
     * @param authorSurname apellido del autor, utilizado para un filtro mas riguroso.
     * @param fullTitle titulo del libro.
     * @throws Exception
     */
    @GetMapping("/search")
    public ResponseEntity<ArrayList<BookDTO>> searchPage(@RequestParam("name") String authorName,
                                             @RequestParam("surname") String authorSurname,
                                             @RequestParam("title") String fullTitle)
    throws Exception {
        String searchQuery = String
                .format("%s %s %s",fullTitle, authorName, authorSurname)
                .trim()
                .replaceAll("\\s+", " ");

        if (searchQuery.isEmpty()) {
            throw new Exception("Query is empty");
        }

        Input newSearch = new Input(authorName, authorSurname, fullTitle);
        BookScraper ateneoScraper = new AteneoBookScraper();
        BookScraper cuspideScraper = new CuspideBookScraper();
        ArrayList<BookDTO> books = new ArrayList<>(ateneoScraper.getBooks(newSearch));
        books.addAll(cuspideScraper.getBooks(newSearch));
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
