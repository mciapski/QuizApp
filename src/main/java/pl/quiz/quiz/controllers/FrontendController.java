package pl.quiz.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

  @GetMapping("/")
  public String hello(Model model){
    model.addAttribute("message","some message");
    return "index";
  }

  @GetMapping("/select")
  public String select(Model model){
    return "select";
  }
}
