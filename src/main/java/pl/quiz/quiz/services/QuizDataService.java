package pl.quiz.quiz.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.quiz.quiz.dto.CategoriesDto;
import pl.quiz.quiz.dto.QuestionsDto;
import pl.quiz.quiz.frontend.GameOptions;

import java.net.URI;
import java.util.List;

/*
1. Klasa RestTemplate pozwala na proste wysyłanie żądań HTTP do serwerów, najczęściej działających w Internecie.

2. Jest to dokładnie odwrotna sytuacja niż stworzony przez nas wcześniej @RestController,
 który udostępnia klientom z zewnątrz jakieś dane - tym razem to my (za pomocą RestTemplate) będziemy "konsumować" dane przygotowane i udostępnione przez kogoś innego.

3. Metoda getForObject pozwala nam pobrać dane z podanego adresu URL.
 Jako drugi parametr przekazujemy klasę, która reprezentuje typ danych, którego spodziewamy się w odpowiedzi od serwera.
 W naszym przypadku to String
*/

@Service
@Log
public class QuizDataService {

  public List<CategoriesDto.CategoryDto> getQuizCategories() {
    RestTemplate restTemplate = new RestTemplate();
    CategoriesDto result = restTemplate.getForObject("https://opentdb.com/api_category.php", CategoriesDto.class);
    log.info("Quiz categories: " + result.getCategories());
    return result.getCategories();
  }

  public List<QuestionsDto.QuestionDto> getQuizQuestions(GameOptions gameOptions) {
    RestTemplate restTemplate = new RestTemplate();

    //https://opentdb.com/api.php?amount=2&category=25&difficulty=medium
    URI uri = UriComponentsBuilder.fromHttpUrl("https://opentdb.com/api.php")
        .queryParam("amount", gameOptions.getNumberOfQuestions())
        .queryParam("category", gameOptions.getCategoryId())
        .queryParam("difficulty", gameOptions.getDifficulty())
        .build().toUri();
    log.info("Quiz question retrieve URL: " + uri);

    QuestionsDto result = restTemplate.getForObject(uri, QuestionsDto.class);
    log.info("Quiz questions: " + result.getResults());
    return result.getResults();
  }
}
