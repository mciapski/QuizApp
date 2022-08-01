package pl.quiz.quiz.frontend;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Log
@Controller
public class FrontendController {

  @GetMapping("/")
  public String hello(Model model) {
    model.addAttribute("message", "some message");
    return "index";
  }

  @GetMapping("/select")
  public String select(Model model) {
    model.addAttribute("gameOptions", new GameOptions());
    return "select";
  }

  @PostMapping("/select")
  public String postSelectForm(Model model, @ModelAttribute GameOptions gameOptions) {
    log.info("Form submitted with data: " + gameOptions);
    return "index";

  }
}