package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Сибирь II", "Квест");
    Game game3 = store.publishGame("Сибирь", "Квест");
    Game game4 = store.publishGame("Civilization V", "Стратегия");
    Game game5 = store.publishGame("Stronghold", "Стратегия");
    @Test
    public void shouldSumGenreIfOneGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGamesOneGenre() {

        Player player = new Player("Petya");
        player.installGame(game2);
        player.installGame(game3);

        player.play(game2, 3);
        player.play(game3, 6);

        int expected = 9;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGamesTwoGenre() {

        Player player = new Player("Petya");
        player.installGame(game2);
        player.installGame(game4);

        player.play(game2, 3);
        player.play(game4, 6);

        int expected = 3;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreOneGamePlayTwice() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 7;
        int actual = player.play(game1, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreNullHours() {

        Player player = new Player("Petya");
        player.installGame(game5);
        player.play(game5, 0);

        int expected = 0;
        int actual = player.sumGenre(game5.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowableExceptionIfNotInstall() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game5, 7);

        assertThrows(RuntimeException.class, () -> {
            player.play(game5, 7);
        });
    }

    @Test
    public void shouldCheckTimeIfReinstallGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 7);
        player.installGame(game1);

        int expected = 7;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldChooseGameWithLongTimeByGenre() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);
        player.play(game1, 17);
        player.play(game2, 1);
        player.play(game3, 3);
        player.play(game4, 15);
        player.play(game5, 2);

        Game expected = game4;
        Game actual = player.mostPlayerByGenre("Стратегия");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldChooseGameWithLongTimeNotPlayedGame() {
        Player player = new Player("Petya");
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game4, 15);
        player.play(game5, 2);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Квест");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldChooseGameWithLongTimeOneGame() {
        Player player = new Player("Petya");
        player.installGame(game2);
        player.play(game2, 10);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Квест");
        assertEquals(expected, actual);
    }

}
