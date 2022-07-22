package pl.quiz.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
{
   "trivia_categories":[
      {
         "id":9,
         "name":"General Knowledge"
      },
      {
         "id":10,
         "name":"Entertainment: Books"
      }
    ]
}
Najbardziej zewnętrzny obiekt posiada tylko jedno pole trivia_categories,
które jest tablicą (ma wiele takich samych obiektów w środku, oddzielonych przecinkami).
Każdy taki mały obiekt w środku ma dwa pola - id, które jest liczbą całkowitą i name, które jest tekstem.
Nasza klasa musi dokładnie odzwierciedlać tę strukturę, zachowując te same nazwy
 */
@NoArgsConstructor
@Data
public class CategoriesDto {
  @JsonProperty("trivia_categories")
  private List<CategoryDto> categories;

  @NoArgsConstructor
  @Data
  private static class CategoryDto {
    private int id;
    private String name;
  }
}
