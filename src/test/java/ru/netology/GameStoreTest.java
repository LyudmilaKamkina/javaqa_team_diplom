package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameStoreTest {
    GameStore store = new GameStore();
    GameStore store1 = new GameStore();

    @Test
    public void shouldAddOneGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldCheckContainsGame() {
        Game[] gamesTest = new Game[5];
        boolean[] assertContainGame = new boolean[5];
        gamesTest[0] = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        gamesTest[1] = store.publishGame("Герои меча и магии", "Стратегия");
        gamesTest[2] = store.publishGame("Веселая ферма", "Казуальные");
        gamesTest[3] = store.publishGame("Пазлы", "Головоломки");
        gamesTest[4] = store.publishGame("Хроники Хаоса", "РПГ");
        for (int i = 0; i < gamesTest.length; i++) {
            assertContainGame[i] = store.containsGame(gamesTest[i]);
        }
        boolean[] expected = new boolean[]{true, true, true, true, true};
        assertArrayEquals(expected, assertContainGame);
    }

    @Test
    public void shouldCheckContainsGameNull() {
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);
        assertFalse(store.containsGame(game));
    }

    @ParameterizedTest
    @CsvSource({"0, 0, 0, 0",
            "1, 2, 3, 6"})
    public void shoulAddPlayedTime(int hours1, int hours2, int hours3, int expected) {
        Player player1 = new Player("Vasya");
        store.addPlayTime("Vasya", hours1);
        store.addPlayTime("Vasya", hours2);
        store.addPlayTime("Vasya", hours3);
        assertEquals(expected, store.getSumPlayedTime());
    }

    @Test
    public void shouldShowMostPlayedPlayerNoExistPlayers() {
        assertEquals(null, store.getMostPlayer());
    }

    @Test
    public void shouldShowMostPlayedPlayerOnePlayer() {
        Player player1 = new Player("Vasya");
        store.addPlayTime("Vasya", 3);
        assertEquals("Vasya", store.getMostPlayer());
    }

    @Test
    public void shouldShowMostPlayedPlayerTwoPlayers() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 3);
        store.addPlayTime("Petya", 4);
        assertEquals("Petya", store.getMostPlayer());
    }

    @Test
    public void shouldShowMostPlayedPlayerTwoPlayersEqualTime() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 4);
        store.addPlayTime("Petya", 4);
        assertEquals("Petya", store.getMostPlayer());
    }

    @Test
    public void shouldShowSumTimeNoExistPlayers() {
        assertEquals(0, store.getSumPlayedTime());
    }

    @Test
    public void shouldShowSumTimeOnePlayer() {
        Player player1 = new Player("Vasya");
        store.addPlayTime("Vasya", 3);
        store.addPlayTime("Vasya", 4);
        store.addPlayTime("Vasya", 0);
        assertEquals(7, store.getSumPlayedTime());
    }


    @Test
    public void shouldShowSumTimeTwoPlayersOnePlayerPlayedNullTime() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 3);
        store.addPlayTime("Petya", 0);
        assertEquals(3, store.getSumPlayedTime());
    }

    @Test
    public void shouldShowSumTimeTwoPlayersNotNullTime() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 3);
        store.addPlayTime("Petya", 0);
        assertEquals(3, store.getSumPlayedTime());
    }
    @Test
    public void shouldShowSumTimeTwoPlayersTwoCatalogs() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 3);
        store1.addPlayTime("Petya", 4);
        assertEquals(3, store.getSumPlayedTime());
        assertEquals(4, store1.getSumPlayedTime());
    }
}
