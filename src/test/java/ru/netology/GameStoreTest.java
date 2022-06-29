package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {
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
    public void shoulShowSumTime() {
        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");
        store.addPlayTime("Vasya", 3);
        store.addPlayTime("Petya", 4);
        assertEquals(7, store.getSumPlayedTime());
    }
}
