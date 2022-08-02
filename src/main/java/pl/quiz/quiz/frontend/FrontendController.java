package pl.quiz.quiz.frontend;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.quiz.quiz.services.QuizDataService;

@Log
@Controller
public class FrontendController {
  @Autowired
  private QuizDataService quizDataService;

  @GetMapping("/")
  public String hello(Model model) {
    model.addAttribute("message", "some message");
    return "index";
  }

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
  public String postSelectForm(Model model, @ModelAttribute GameOptions gameOptions){
    log.info("Form submitted with data: " + gameOptions);
    return "index";

  }
}