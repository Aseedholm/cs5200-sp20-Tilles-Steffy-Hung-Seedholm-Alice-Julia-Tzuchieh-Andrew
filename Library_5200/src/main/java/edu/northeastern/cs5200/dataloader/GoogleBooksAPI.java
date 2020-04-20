package edu.northeastern.cs5200.dataloader;


import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.*;
import javafx.util.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This class queries the GoogleBooks API and converts it into our data model, and saves the data.
 */
@Component
public class GoogleBooksAPI {

    LibraryDao libraryDao;

    public GoogleBooksAPI(LibraryDao libraryDao){
        this.libraryDao = libraryDao;
    }

    /**
     * Loads into the database 0 to 6 copies of a book to the library. Up to 3 each of audiobook vs hard copy.
     * Randomly chooses a condition for hard copy.
     * Seeds the library!
     * @param book The book to make copies of
     */
    private void generateBookCopiesRandomly(Book book) {

        int max = 3;
        int min = 0;

        // Hard copies
        int numCopies = (int)(Math.random()*((max-min)+1))+min;
        for (int i = 0; i < numCopies; i++) {

            HardCopyBook newCopy = new HardCopyBook();
            newCopy.setBook(book);
            newCopy.setAvailable(true);
            newCopy.setCurrentCondition(CurrentCondition.getRandomCondition());
            libraryDao.createHardCopyBook(newCopy);
        }

        // Audio books
        numCopies = (int)(Math.random()*((max-min)+1))+min;
        for (int i = 0; i < numCopies; i++) {

            AudioBook newCopy = new AudioBook();
            newCopy.setBook(book);
            newCopy.setAvailable(true);
            libraryDao.createAudioBook(newCopy);

        }


    }

    /**
     * Makes a connection w/ Google Books API based on input string. Extracts data and puts it in our library database.
     * @param url the URI to query
     * @throws IOException
     * @throws ParseException
     */
    public void loadFromAPI(String url) throws IOException, ParseException {

        // Cast API call into URL object
        URL myURL = new URL(url);

        // Cast into HttpURLConnection object, specify GET request, start connection
        HttpURLConnection conn = (HttpURLConnection)myURL.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        // Make sure connection works
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
        }


        // Retrieve JSON data
        else {
            String result = "";

            Scanner sc = new Scanner(myURL.openStream());
            while (sc.hasNext()) {
                result+=sc.nextLine();
            }

            sc.close();
            conn.disconnect();

            // This tool will help us parse the unstructured data
            JSONParser parse = new JSONParser();

            // Convert string object into JSON objects
            JSONObject jObj = (JSONObject)parse.parse(result);

            // Convert JSON object into JSONArray object:
            JSONArray jsonarr_1 = (JSONArray) jObj.get("items");

            // Get data in that array
            for (int i = 0; i < jsonarr_1.size(); i++) {
                JSONObject book = (JSONObject) jsonarr_1.get(i);
                Pair<Book, Author> bookAndAuthor = JSONtoBook(book);
                Book newBook = bookAndAuthor.getKey();
                Author newAuthor = bookAndAuthor.getValue();

                // Save it to database
                libraryDao.createAuthor(newAuthor);
                libraryDao.createBook(newBook);

                // Create book copies
                generateBookCopiesRandomly(newBook);

            }


        }


    }

    /**
     * This method takes as input a string date from Google Books API in one of these formats:
     *      Null
     *      "2019-08-27",
     *      "2020-03",
     *      "2020-03",
     * Then it converts it to Java.SQL format. Defaults are Y 2000, M 1, D 1.
     * @param input Google Book API date string
     * @return SQL date string
     */
    private static Timestamp StringToDate(String input) {

        // Default values
        Integer year = 2000;
        Integer month = 1;
        Integer day = 1;

        // Extract as much info as we can from the date
        if (input!= null) {
            year = Integer.valueOf(input.substring(0,4));

            if (input.length() > 4) {
                month = Integer.valueOf(input.substring(5,6));
            }

            if (input.length() > 7) {
                day = Integer.valueOf(input.substring(8,9));
            }

        }

        // Convert to SQL
        Calendar cal = Calendar.getInstance();
        cal.set( cal.YEAR, year );
        cal.set( cal.MONTH, month );
        cal.set( cal.DATE, day );
        return new java.sql.Timestamp( cal.getTime().getTime() );
    }

    /**
     * This method converts a JSONObject book from the Google books API to a Book and Author.
     * @param inputBook from google books API
     * @return the same info in our data model
     */
    private static Pair<Book,Author> JSONtoBook(JSONObject inputBook) {

        // Extracting all the info out of the JSON object //TODO extract book_copy info such as # pages
        JSONObject volumeInfo = (JSONObject) inputBook.get("volumeInfo");
        String id = (String) inputBook.get("id");
        JSONArray industryIdentifiers = (JSONArray) volumeInfo.get("industryIdentifiers");
        JSONObject identifierOne = (JSONObject) industryIdentifiers.get(0);
        String isbn = (String) identifierOne.get("identifier");
        JSONArray subjects = (JSONArray) volumeInfo.get("categories");
        Timestamp publishedDate = StringToDate((String)volumeInfo.get("publishedDate"));
        String title = (String) volumeInfo.get("title");
        String subject = "";
        try {
            subject = (String) subjects.get(0);
        } catch (NullPointerException e) {
            subject = null;
        }
        JSONArray authors = (JSONArray) volumeInfo.get("authors");
        String author = "";
        try {
            author = (String) authors.get(0);
        } catch (NullPointerException e) {
            author = null;
        }

        // Create a new book with that info
        Book newBook = new Book();
        newBook.setId(id);
        newBook.setYearPublished(publishedDate);
        newBook.setGenre(subject);
        newBook.setISBN(isbn);
        newBook.setTitle(title);

        // And a new author
        Author newAuthor = new Author();
        newAuthor.setFirstName(author); //TODO separate first and last name
        newBook.setAuthor(newAuthor);

        // Return the two new objects
        return new Pair<>(newBook,newAuthor);
    }


}
