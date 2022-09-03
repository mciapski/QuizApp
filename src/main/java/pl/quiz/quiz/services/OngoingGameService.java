package pl.quiz.quiz.services;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.quiz.quiz.dto.QuestionsDto;
import pl.quiz.quiz.frontend.GameOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
Jak najczęściej robic injection za pomoca konstruktora i żeby było private final
Klasa OngoingGameService przechowuje bieżący stan gry na podstawie zdefiniowanego wcześniej przez gracza GameOption
Zadania:
Numer biężacego pytania
Liczba wszystkich pytań
Dwa powyższe, aby wyświetlić tekst "Pytanie ⅖"
Treść bieżącego pytania
Wszystkie możliwe odpowiedzi
Sprawdzenie, czy wybrana przez użytkownika odpowiedź jest poprawna i naliczenie punktów
Wyświetlenie aktualnej punktacji
Przejście do kolejnego pytania
 */

@Service
@Log
public class OngoingGameService {

  private GameOptions gameOptions;
  private int currentQuestionIndex;
  @Getter
  private int points;

  private List<QuestionsDto.QuestionDto> questions;

  @Autowired
  private QuizDataService quizDataService;

  public void init(GameOptions gameOptions){
    this.gameOptions = gameOptions;
    this.currentQuestionIndex =0;
    this.points = 0;

    this.questions=quizDataService.getQuizQuestions(gameOptions);
  }

  public int getCurrentQuestionIndex(){

    return currentQuestionIndex + 1;
  }

  public int getTotalQuestionNumber(){

    return questions.size();
  }

  public String getCurrentQuestion(){
    QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);
    return dto.getQuestion();
  }

  public List<String> getCurrentQuestionAnswersInRandomOrder(){
    QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);

    List<String> answers = new ArrayList<>();
    answers.add(dto.getCorrectAnswer());
    answers.addAll(dto.getIncorrectAnswers());

    Collections.shuffle(answers);
    return answers;
  }

  public boolean checkAnswerForCurrentQuestionAndUpdatePoints(String userAnswer){
    QuestionsDto.QuestionDto dto = questions.get(currentQuestionIndex);
    boolean correct = dto.getCorrectAnswer().equals(userAnswer);
    if(correct){
      points++;
    }
    return correct;
  }

  public boolean proceedToNextQuestion(){
    currentQuestionIndex++;
    return currentQuestionIndex<questions.size();
  }
}
