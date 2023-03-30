package br.com.alura.programming.languages.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

	@GetMapping("/languages/{id}")
	public Language getLanguageById(@PathVariable String id) {
		return languageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/languages")
	public Language createLanguage(@RequestBody Language language) {
		return languageRepository.save(language);
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
