package app.services;

import app.dto.BookDTO;
import app.scrappers.AteneoBookScraper;
import app.scrappers.BookScraper;
import app.scrappers.CuspideBookScraper;
import app.utils.Input;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchService {
    public ArrayList<BookDTO> searchBooks(String authorName, String authorSurname, String fullTitle) throws Exception {
        /**
         * Elimina la cantidad de caracteres blancos en bordes y entre las palabras.
         */
        String searchQuery = this.formatQuery(authorName, authorSurname, fullTitle);
        Input newSearch = new Input(authorName, authorSurname, searchQuery);
        BookScraper cuspideScraper = new CuspideBookScraper();
        ArrayList<BookDTO> books = new ArrayList<>(cuspideScraper.getBooks(newSearch));
        return books;
    }

    private String formatQuery(String authorName, String authorSurname, String fullTitle) throws Exception {
        String searchQuery = String
                .format("%s %s %s",fullTitle, authorName, authorSurname)
                .trim()
                .replaceAll("\\s+", " ");

        if (searchQuery.isEmpty()) {
            throw new Exception("Query is empty");
        }
        return searchQuery;
    }
}
