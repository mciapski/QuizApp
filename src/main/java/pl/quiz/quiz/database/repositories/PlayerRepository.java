package pl.quiz.quiz.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.quiz.quiz.database.entities.PlayerEntity;

/*
1. Interfejs PlayerRepository dziedziczy po JpaRepository<PlayerEntity, Long>
JpaRepository to interfejs, którego klas dziedziczących szuka Spring.
W trakcie uruchomienia aplikacji tworzy klasy implementujące interfejs, które zawierają prawdziwy kod (opierający się na JPA).

2. Typy generyczne, które podajemy przy JpaRepostiory to:
Pierwszy, PlayerEntity - to klasa encji, z której będziemy korzystać w tym repozytorium.
Drugi, Long - to typ identyfikatora użytego w naszej encji.
Wpisaliśmy tutaj Long, ponieważ takiego typu jest pole id w klasie PlayerEntity i oznaczyliśmy je adnotacją @Id.
 */
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
