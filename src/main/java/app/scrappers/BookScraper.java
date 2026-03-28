package app.scrappers;

import app.dto.BookDTO;
import app.utils.Input;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public abstract class BookScraper {

    protected ArrayList<BookDTO> books;

    public BookScraper() {
        this.books = new ArrayList<>();
    }

    public ArrayList<BookDTO> getBooks(Input input) throws Exception {
        final int MAX_PAGES = 5;

        int page = 0;
        boolean booksRemaining;
        String authorFullName = this.formatAuthor(input.getAuthorName(), input.getAuthorSurname());

        do {
            String url = this.getUrl(++page, input);
            Response response = this.connectPage(url);
            Document document = Jsoup.parse(response.body());
            this.fetchBooks(response, document, authorFullName);
            booksRemaining = this.getFinishCondition(document);
        } while (booksRemaining && page != MAX_PAGES);

        return this.books;
    }

    private int delay() {
        final int MIN_DELAY = 1;
        final int MAX_DELAY = 3;
        return ThreadLocalRandom.current().nextInt(MIN_DELAY, MAX_DELAY);
    }

    protected Response connectPage(String url) throws IOException, InterruptedException {
        Response result = Jsoup.connect(url).method(Method.GET).followRedirects(true).execute();
        TimeUnit.SECONDS.sleep(this.delay());
        return result;
    }

    protected Document getPage(String url) throws IOException, InterruptedException {
        return Jsoup.parse(this.connectPage(url).body());
    }

    protected String urlParameters(String parameters) {
        return parameters.replaceAll(" ", "+");
    }

    protected abstract void fetchBooks(Response response, Document document, String authorFullName) throws Exception;

    protected abstract boolean getFinishCondition(Document document);

    protected abstract String getUrl(int page, Input input);

    protected abstract String getScraperName();

    protected abstract String formatAuthor(String name, String surname);

    protected abstract BookDTO createBook(Element element, String author, String link);
}