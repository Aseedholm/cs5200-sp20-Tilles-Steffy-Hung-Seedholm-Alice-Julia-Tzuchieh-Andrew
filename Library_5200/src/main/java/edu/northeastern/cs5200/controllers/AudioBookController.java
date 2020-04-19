package edu.northeastern.cs5200.controllers;

import java.util.List;

import edu.northeastern.cs5200.models.HardCopyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


	@GetMapping("/api/audio-books")
	public List<AudioBook> findAllAudioBooks() {
		return libraryDao.findAllAudioBooks();
	}
}
