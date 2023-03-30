package br.com.alura.programming.languages.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface LanguageRepository extends MongoRepository<Language, String> {

}
