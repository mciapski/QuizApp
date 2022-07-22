package pl.quiz.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@Data
public class QuestionsDto {

  @JsonProperty("response_code")
  private int responseCode;
  private List<QuestionDto> results;

  @NoArgsConstructor
  @Data
  public static class QuestionDto{

    private String category;
    private String type;
    private String difficulty;
    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;
  }
}
