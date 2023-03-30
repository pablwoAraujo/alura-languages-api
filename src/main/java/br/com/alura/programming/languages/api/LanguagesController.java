package br.com.alura.programming.languages.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/languages")
	public Language createLanguage(@RequestBody Language language) {
		return languageRepository.save(language);
	}
}
