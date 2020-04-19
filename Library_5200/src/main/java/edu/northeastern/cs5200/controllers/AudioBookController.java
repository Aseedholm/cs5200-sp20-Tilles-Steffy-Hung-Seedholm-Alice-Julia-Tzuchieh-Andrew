package edu.northeastern.cs5200.controllers;

import java.util.List;
import java.util.Set;

import edu.northeastern.cs5200.models.HardCopyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.AudioBook;

@RestController
@CrossOrigin(origins = "*")
public class AudioBookController {

	@Autowired
	LibraryDao libraryDao;

	@PostMapping("api/audio-books")
	public AudioBook createAudioBook(@RequestBody AudioBook book) {
		return libraryDao.createAudioBook(book);
	}

	@PostMapping("api/audio-books/{id}")
	public AudioBook createAudioBookById(@PathVariable Integer id) {
		return libraryDao.addAudiobook(id);
	}

	@GetMapping("/api/audio-books")
	public List<AudioBook> findAllAudioBooks() {
		return libraryDao.findAllAudioBooks();
	}

	@GetMapping("/api/audio-books/{bookId}")
	public Set<AudioBook> findAudioBookByBookId(@PathVariable Integer bookId) {
		return libraryDao.findAudioBooksByBookId(bookId);
	}

}
