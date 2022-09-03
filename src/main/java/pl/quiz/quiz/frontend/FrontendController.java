package pl.quiz.quiz.frontend;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.quiz.quiz.services.OngoingGameService;
import pl.quiz.quiz.services.QuizDataService;

@Log
@Controller
public class FrontendController {
  @Autowired
  private QuizDataService quizDataService;

  @Autowired
  private OngoingGameService ongoingGameService;

  @GetMapping("/select")
  public String select(Model model) {

    model.addAttribute("gameOptions", new GameOptions());
    /*
    <th:block th:each="category : ${categories}">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="categoryRadioOptions"
                        th:id="'category_' + ${category.getId()}" th:value="${category.getId()}" th:field="*{categoryId}">
                        <label class="form-check-label" th:for="'difficulty_' + ${category.getId()}"
                               th:text="${category.getName()}">
                        </label>
                    </div>
                </th:block>
     */
    model.addAttribute("categories", quizDataService.getQuizCategories());
    return "select";
  }

  @PostMapping("/select")
  public String postSelectForm(Model model, @ModelAttribute GameOptions gameOptions) {
    log.info("Form submitted with data: " + gameOptions);
    ongoingGameService.init(gameOptions);
    return "redirect:game";
  }
  @GetMapping("/game")
  public String game(Model model){
    model.addAttribute("userAnswer", new UserAnswer());
    model.addAttribute("currentQuestionIndex", ongoingGameService.getCurrentQuestionIndex());
    model.addAttribute("totalQuestionNumber", ongoingGameService.getTotalQuestionNumber());
    model.addAttribute("currentQuestion", ongoingGameService.getCurrentQuestion());
    model.addAttribute("currentQuestionAnswers", ongoingGameService.getCurrentQuestionAnswersInRandomOrder());
    return "game";
  }
  @PostMapping("/game")
  public String postSelectForm(Model model, @ModelAttribute UserAnswer userAnswer){
    ongoingGameService.checkAnswerForCurrentQuestionAndUpdatePoints(userAnswer.getAnswer());
    boolean hasNextQuestion = ongoingGameService.proceedToNextQuestion();
    if(hasNextQuestion){
      return "redirect:game";
    }else {
      return "redirect:summary";
    }
  }
  @GetMapping("/summary")
  public String summary(Model model){
    model.addAttribute("difficulty", ongoingGameService.getDifficulty());
    model.addAttribute("categoryName", ongoingGameService.getCategoryName());
    model.addAttribute("points", ongoingGameService.getPoints());
    model.addAttribute("maxPoints", ongoingGameService.getTotalQuestionNumber());

    return "summary";
  }
}