package pl.quiz.quiz;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.quiz.quiz.database.entities.PlayerEntity;
import pl.quiz.quiz.database.repositories.PlayerRepository;
import pl.quiz.quiz.services.QuizDataService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/*
1. Anotacja @Component oznacza, że ta klasa będzie beanem Springowym.

2. Rozszerzanie interfejsu CommandLineRunner wymusza na nas dostarczenie implementacji metody run,
która zostanie uruchomiona automatycznie po załadowaniu kontekstu aplikacji Springowej,
zaraz przed zakończeniem wywołania metody run z maina (QuizApplication).

3. Adnotacja @Log z Lomboka dodaje nam do klasy statyczne pole log typu Logger,
którego możemy używać do wysyłania informacji na konsolę.
To bardziej elegancka alternatywa dla System.out.println(),
która zawiera dodatkowe informacje (jak np. data i godzina czy informacja z jakiej klasy pochodzi dana wiadomość).

4. Kod metody run jest bardzo prosty - tworzymy nowy obiekt klasy PlayerEntity, zapisujemy go w zmiennej player,
a następnie wyświetlamy ją na konsoli. Rezultat działania tej metody widzieliśmy powyżej.

5. @Autowired private EntityManager entityManager:
Adnotacja @Autowired oznacza, że Spring automatycznie w tym polu umieści referencję do odpowiedniego beana.
To umieszczenie nazywa się wstrzyknięciem (ang. inject).
@Autowired może być użyte tylko w klasie, która sama również jest beanem Springowym -
co w naszym przypadku zrealizowaliśmy przez zapisanie @Component obok class StartupRunner.
Tutaj chcemy wstrzyknąć beana klasy EntityManager, co zapisaliśmy przez nadanie odpowiedniego typu zmiennej.

6. @Transactional oznacza, że kod danej metody będzie wykonywany w transakcji bazodanowej.
Musimy to umieścić, ponieważ wymaga tego od nas Spring - i bez tego otrzymamy wyjątek.

7. entityManager.persist(player) powoduje skorzystanie z EntityManagera do zapisania obiektu player do bazy danych.
Od tego momentu nasz obiekt jest zarządzany przez JPA - i jego modyfikacje będą odzwierciedlone w bazie danych.

8. W klasie StartupRunner wstrzyknęliśmy (przez @Autowired) obiekt typu PlayerRepository.
W to pole Spring wstawi obiekt klasy wygenerowanej automatycznie, która implementuje stworzony przez nas interfejs PlayerRepository.
Zapisujemy rekordy do bazy, wywołując na naszym repozytorium metodę save() (odpowiednik persist z JPA),
a wszystkie rekordy z bazy odczytujemy używając findAll().

 */
@Component
@Log
public class StartRunner implements CommandLineRunner {

//  @Autowired
//  private PlayerRepository playerRepository;

  @Autowired
  QuizDataService quizDataService;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    log.info("Executing startup actions...");
//    playerRepository.save(new PlayerEntity("John"));
//    playerRepository.save(new PlayerEntity("Harry"));
//    playerRepository.save(new PlayerEntity("George"));
//
//    log.info("List of players from database: ");
//    List<PlayerEntity> playersFromDB = playerRepository.findAll();
//
//    for (PlayerEntity playerFromDB : playersFromDB) {
//      log.info("Player from DB: " + playerFromDB);
//    }
    quizDataService.getQuizCategories();
    quizDataService.getQuizQuestions();
  }
}
