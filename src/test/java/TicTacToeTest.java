
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriverException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.net.NetworkInterface;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToeTest {
    private static Logger logger = LogManager.getLogger(TicTacToeTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    boolean testFailed = false;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    private int numberOfTests = 5;
    String testName = null;

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        //Проект выполнен на Linux, дистрибутив Ubuntu
        //Версия Chrome: Google Chrome 129.0.6668.58
        //Версия ChromeDriver: ChromeDriver 129.0.6668.58 (81a06fb873a9b386848719cf9f93e59579fb5d4b-refs/branch-heads/6668@{#1318})

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        testName = testInfo.getDisplayName().replaceAll("[^a-zA-Z0-9_\\-]", "_");
        ThreadContext.put("testName", "TestNameValue");
        ThreadContext.put("testName", testName);
        logger.info("Запуск теста: " + testName);


        driver.get("https://g.co/kgs/NKFefHW");
        logger.info("Перешли на страницу игры \"Крестики-нолики\".");
    }








    @Test
    @DisplayName("TestError")
    @Description("Падение теста, создание искусственной ошибки.")
    @AllureId("002")
    public void testTimeoutWaitingForElement() {
        try {

            WebElement nonExistentElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("nonExistentId")));
            nonExistentElement.click();
        } catch (TimeoutException e) {
            testFailed = true;
            logger.error("Таймаут ожидания элемента: " + e.getMessage());
            throw new RuntimeException("Тест успешно завершился с ошибкой: таймаут ожидания.");
        }
    }



    @AfterEach
    public void tearDown(TestInfo testInfo) {
        String testName = testInfo.getDisplayName().replaceAll(" ", "_");
        ThreadContext.put("testName", testName);
        logger.info("Завершение теста: " + testName);
        if (driver != null) {
            try {
                if (testFailed) {
                    String screenshotFileName = String.format("screenshot_%s_%s.png",
                            testName,
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")));
                    takeScreenshot(testName, screenshotFileName);
                    logger.info("Скриншот сохранен: " + screenshotFileName);
                }
            } catch (WebDriverException e) {
                logger.error("Ошибка при захвате скриншота: " + e.getMessage());
            } finally {
                driver.quit();
                logger.info("Закрыли браузер.");
            }
        }
        ThreadContext.clearAll();
    }


    @Test
    @DisplayName("testWinX")
    @Description("Сценарий победы Х")
    @AllureId("003")
    public void testWinX() throws InterruptedException {
        Thread.sleep(500);
        selectGameMode();
        Thread.sleep(2000);


        playMove(0, 2, "X");
        Thread.sleep(400);


        playMove(1, 0, "0");
        Thread.sleep(400);


        playMove(1, 1, "X");
        Thread.sleep(400);


        playMove(2, 2, "0");
        Thread.sleep(400);


        playMove(2, 0, "X");


        String[][] board = new String[3][3];

        board[0][2] = "X";
        board[1][0] = "O";
        board[1][1] = "X";
        board[2][2] = "O";
        board[2][0] = "X";

        boolean isXWinner = checkWinCondition(board, "X");
        Assertions.assertTrue(isXWinner, "Крестик не выиграл, хотя должен был!");


        logger.info("Крестик победил");
    }
    @Test
    @DisplayName("testWin0")
    @Description("Сценарий победы 0")
    @AllureId("004")
    public void testWin0() throws InterruptedException {
        Thread.sleep(500);
        selectGameMode();
        Thread.sleep(2000);


        playMove(0, 2, "X");
        Thread.sleep(400);


        playMove(0, 1, "0");
        Thread.sleep(400);


        playMove(1, 2, "X");
        Thread.sleep(400);


        playMove(1, 1, "0");
        Thread.sleep(400);


        playMove(2, 0, "X");

        Thread.sleep(400);
        playMove(2, 1, "0");


        String[][] board = new String[3][3];
        board[0][2] = "X";
        board[0][1] = "0";
        board[1][2] = "X";
        board[1][1] = "0";
        board[2][0] = "X";
        board[2][1] = "0";


        boolean is0Winner = checkWinCondition(board, "0");
        Assertions.assertTrue(is0Winner, "Нолик не выиграл, хотя должен был!");


        logger.info("Нолик победил");
    }


    @Test
    @DisplayName("testDraw")
    @Description("Сценарий ничьи")
    @AllureId("005")
    public void testDraw() throws InterruptedException {
        Thread.sleep(500);
        selectGameMode();
        Thread.sleep(2000);


        playMove(0, 0, "X");
        Thread.sleep(400);
        playMove(0, 1, "0");
        Thread.sleep(400);
        playMove(1, 1, "X");
        Thread.sleep(400);
        playMove(0, 2, "0");
        Thread.sleep(400);
        playMove(1, 2, "X");
        Thread.sleep(400);
        playMove(2, 2, "0");
        Thread.sleep(400);
        playMove(2, 0, "X");
        Thread.sleep(400);
        playMove(1, 0, "0");
        Thread.sleep(400);
        playMove(2, 1, "X");



        String[][] board = new String[3][3];

        board[0][0] = "X";
        board[0][1] = "0";
        board[1][1] = "X";
        board[0][2] = "0";
        board[1][2] = "X";
        board[2][2] = "0";
        board[2][0] = "X";
        board[1][0] = "0";
        board[2][1] = "X";


        boolean isDraw = checkDrawCondition(board);
        Assertions.assertTrue(isDraw, "Игра не завершилась вничью, хотя должна была!");

        logger.info("Игра завершилась вничью");
    }


    private boolean checkDrawCondition(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }

        return !checkWinCondition(board, "X") && !checkWinCondition(board, "0");
    }

    @Test
    @DisplayName("MainTest")
    @Description("Валидный тест игры. Победа/Проигрыш/Ничья - генерируется на рандом")
    @AllureId("001")
    public void testRandomMoves() throws InterruptedException {
        for (int i = 0; i < numberOfTests; i++) {
            logger.info("Запуск игры № " + (i + 1));


            if (!selectGameMode()) {
                logger.error("Пропускаем игру № " + (i + 1) + " из-за ошибки при выборе режима.");
                continue;
            }


            Thread.sleep(500);


            String firstPlayer = (i % 2 == 0) ? "X" : "O";
            logger.info("Первый ход делает игрок: " + firstPlayer);
            String result = playGame(i + 1, firstPlayer);


            if (result.equals("X")) {
                xWins++;
            } else if (result.equals("O")) {
                oWins++;
            } else {
                draws++;
            }


            restartGame();
        }


        logger.info(String.format("Итоги игр: \nX побед: %d, \nO побед: %d, \nНичьи: %d%n", xWins, oWins, draws));
    }


    private String playGame(int gameNumber, String firstPlayer) throws InterruptedException {
        List<int[]> cells = new ArrayList<>(Arrays.asList(
                new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2},
                new int[]{1, 0}, new int[]{1, 1}, new int[]{1, 2},
                new int[]{2, 0}, new int[]{2, 1}, new int[]{2, 2}
        ));
        logger.debug("Текущий список доступных ячеек: " + cells);
        String currentPlayer = firstPlayer;
        String[][] board = new String[3][3];

        Thread.sleep(400);

        for (int i = 0; i < 9; i++) {
            if (cells.isEmpty()) {
                logger.info("Игра завершилась ничьей.");
                return "D";
            }

            logger.debug("Текущий список доступных ячеек: " + cells);
            int randomIndex = new Random().nextInt(cells.size());
            int[] cell = cells.remove(randomIndex);

            if (!playMove(cell[0], cell[1], currentPlayer)) {
                i--;
                continue;
            }

            board[cell[0]][cell[1]] = currentPlayer;


            printBoardState();


            if (checkWinCondition(board, currentPlayer)) {
                logger.info(String.format("Игрок %s победил в игре № %d!%n", currentPlayer, gameNumber));
                return currentPlayer;
            }


            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }


        logger.info("Игра № " + gameNumber + " завершилась ничьей.");
        return "D";
    }






    private boolean selectGameMode() {
        try {
            logger.info("Выбор режима игры с другом...");
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("byDAwb")));
            button.click();

            WebElement playWithFriendOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//g-menu-item[@data-difficulty='pvp']")));
            playWithFriendOption.click();
            logger.info("Режим 'Играть с другом' успешно выбран.");
            return true;
        } catch (TimeoutException e) {
            testFailed = true;
            logger.error("Ошибка при выборе режима игры: " + e.getMessage());
            return false;
        }
    }
    @Step("Проверка условий победы для {player}")
    private boolean checkWinCondition(String[][] board, String player) {

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != null && board[i][0].equals(player) &&
                    board[i][1] != null && board[i][1].equals(player) &&
                    board[i][2] != null && board[i][2].equals(player)) ||
                    (board[0][i] != null && board[0][i].equals(player) &&
                            board[1][i] != null && board[1][i].equals(player) &&
                            board[2][i] != null && board[2][i].equals(player))) {
                return true;
            }
        }


        return (board[0][0] != null && board[0][0].equals(player) &&
                board[1][1] != null && board[1][1].equals(player) &&
                board[2][2] != null && board[2][2].equals(player)) ||
                (board[0][2] != null && board[0][2].equals(player) &&
                        board[1][1] != null && board[1][1].equals(player) &&
                        board[2][0] != null && board[2][0].equals(player));
    }






    public void takeScreenshot(String testName, String fileName) {
        try {

            File screenshotDir = new File("screenshots/" + testName);
            if (!screenshotDir.exists()) {
                if (screenshotDir.mkdirs()) {
                    logger.info("Создана директория для скриншотов: " + screenshotDir.getAbsolutePath());
                } else {
                    logger.error("Не удалось создать директорию для скриншотов");
                }
            }


            Robot robot = new Robot();
            java.awt.Rectangle screenRect = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenImage = robot.createScreenCapture(screenRect);


            File screenshotFile = new File(screenshotDir, fileName);
            ImageIO.write(screenImage, "png", screenshotFile);
            logger.info("Скриншот сохранен: " + screenshotFile.getAbsolutePath());

        } catch (AWTException e) {
            logger.error("Ошибка при создании скриншота: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
    private boolean playMove(int row, int col, String player) throws InterruptedException {
        String cellXpath = String.format("//table[@class='KD6xlc']//tr[%d]//td[%d]", row + 1, col + 1);
        try {
            WebElement cell = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cellXpath)));

            logger.info(String.format("Игрок %s делает ход в ячейку [%d, %d] в %s",
                    player, row, col, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            cell.click();
            Thread.sleep(289);
            return true;
        } catch (ElementClickInterceptedException | TimeoutException e) {
            testFailed = true;
            logger.error(String.format("Не удалось кликнуть по ячейке [%d, %d] для игрока %s: %s%n", row, col, player, e.getMessage()));
            return false;
        }
    }
    @Step("Вывод состояния игрового поля")
    public void printBoardState() {
        List<WebElement> cells = driver.findElements(By.cssSelector("table[jsname='z2IlVe'] td[jsname='WfZakb']"));
        String[][] board = new String[3][3];

        for (WebElement cell : cells) {
            int row = Integer.parseInt(cell.getAttribute("data-row"));
            int col = Integer.parseInt(cell.getAttribute("data-col"));


            String state;
            if (cell.findElement(By.cssSelector("svg[jsname='uECznc']")).isDisplayed()) {
                state = "X";
            } else if (cell.findElement(By.cssSelector("svg[jsname='D7yUae']")).isDisplayed()) {
                state = "O";
            } else {
                state = " ";
            }

            board[row][col] = state;
        }


        System.out.println("Текущее состояние поля:");
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }


    private void restartGame() throws InterruptedException {
        try {
            WebElement restartButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'Начать заново')]")));
            restartButton.click();
            Thread.sleep(268);
            logger.info("Игра перезапущена.");
        } catch (TimeoutException e) {
            testFailed = true;
            logger.error("Не удалось перезапустить игру: " + e.getMessage());
        }
    }
}