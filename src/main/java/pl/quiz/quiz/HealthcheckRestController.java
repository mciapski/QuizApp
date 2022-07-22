package pl.quiz.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.quiz.quiz.dto.HealthcheckDto;

@RestController
@RequestMapping("/api/health")
public class HealthcheckRestController {

  @GetMapping
  public HealthcheckDto healthcheck(){
    HealthcheckDto dto = new HealthcheckDto(true, "It's working");
    return dto;
  }
}
