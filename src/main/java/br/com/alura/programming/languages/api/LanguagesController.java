package br.com.alura.programming.languages.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguagesController {

	@Autowired
	private LanguageRepository languageRepository;

	@GetMapping(value = "/java")
	public String helloJava() {
		return "Oi, Java!";
	}

	@GetMapping("/languages")
	public List<Language> getLanguages() {
		List<Language> languages = languageRepository.findAll();
		return languages;
	}
	
	@GetMapping("/sortedLanguages")
	public List<Language> getSortedLanguages() {
		List<Language> languages = languageRepository.findByOrderByRanking();
		return languages;
	}

	@GetMapping("/languages/{id}")
	public Language getLanguageById(@PathVariable String id) {
		return languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/languages")
	public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
		Language savedLanguage = languageRepository.save(language);
		return new ResponseEntity<>(savedLanguage, HttpStatus.CREATED);
	}

	@PutMapping("/languages/{id}")
	public Language updateLanguage(@RequestBody Language language, @PathVariable String id) {
		// languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (!languageRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		language.setId(id);
		Language savedLanguage = languageRepository.save(language);
		return savedLanguage;
	}

	@DeleteMapping("/languages/{id}")
	public void deleteLanguage(@PathVariable String id) {
		if (!languageRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		languageRepository.deleteById(id);
	}
}
