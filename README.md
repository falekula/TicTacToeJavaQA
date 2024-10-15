```markdown
# 🎮 Tic Tac Toe Automation Testing Project

## 📜 Описание

Данный проект предназначен для автоматизированного тестирования классической игры "Крестики-нолики" в режиме "играть с другом". Цель проекта — продемонстрировать и проверить различные сценарии завершения игры: победа одного из игроков или ничья. Тесты разработаны с использованием современных инструментов для автоматизации тестирования, обеспечивая высокую надежность и удобство при выполнении.

Проект может быть полезен как для изучения принципов автоматизированного тестирования, так и для демонстрации работы с такими технологиями, как Selenium, JUnit и Allure.

## 🛠️ Технологии

- **Java 11**: Основной язык программирования проекта.
- **Maven**: Система управления проектами и зависимостями.
- **Selenium Java**: Библиотека для автоматизации браузеров.
- **JUnit Jupiter**: Фреймворк для написания и выполнения тестов.
- **Log4j**: Библиотека для логирования, которая помогает отслеживать выполнение тестов и выводить результаты.
- **Allure JUnit**: Плагин для генерации отчетов о тестировании с наглядным представлением результатов.
- **Chrome и ChromeDriver**: Используются для автоматизации тестирования в браузере Google Chrome.

## 📦 Установка

1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/falekula/TicTacToeJavaQA.git
   ```

2. Перейдите в директорию проекта:
   ```bash
   cd TicTacToeJavaQA
   ```

3. Убедитесь, что у вас установлен Maven, и выполните команду для сборки проекта:
   ```bash
   mvn clean install
   ```

## 🚀 Запуск тестов

Для запуска тестов выполните команду:
```bash
mvn test
```

## 📊 Генерация отчетов

Для генерации детализированных отчетов с использованием Allure, выполните следующую команду:
```bash
mvn allure:serve
```

## 🧪 Описание тестов

1. **TestError**:
   - **Описание**: Тест, предназначенный для искусственного создания ошибки. Тест симулирует ситуацию, когда система не может найти элемент на странице (ожидается `TimeoutException`).
   - **Цель**: Проверка корректной обработки таймаутов и механизма логирования при возникновении ошибок в процессе выполнения тестов. Также тестирует механизм захвата скриншотов при сбое.
   - **Сценарий**: Происходит попытка найти несуществующий элемент на странице, после чего тест завершается с ошибкой.
   - **AllureId**: `002`

2. **TestWinX**:
   - **Описание**: Тестирует сценарий победы игрока, который играет крестиками (X).
   - **Цель**: Убедиться, что логика определения победы крестика работает корректно, и игра завершается с правильным результатом.
   - **Сценарий**: Игрок X заполняет диагональ игрового поля, что приводит к его победе. Проверяется правильность завершения игры и объявление победителя.
   - **AllureId**: `003`

3. **TestWin0**:
   - **Описание**: Тестирует сценарий победы игрока, который играет ноликами (O).
   - **Цель**: Проверить корректную работу механизма определения победы ноликов в игре.
   - **Сценарий**: Игрок O заполняет вертикальную линию на игровом поле, что приводит к его победе. В конце проверяется объявление победы и корректное завершение игры.
   - **AllureId**: `004`

4. **TestDraw**:
   - **Описание**: Тест проверяет сценарий, при котором игра заканчивается ничьей.
   - **Цель**: Убедиться, что система правильно обрабатывает случаи, когда все ячейки заполнены, и ни один игрок не выигрывает.
   - **Сценарий**: Игровое поле полностью заполняется без победителя, и проверяется логика, которая должна идентифицировать ничью.
   - **AllureId**: `005`

5. **MainTest**:
   - **Описание**: Основной тест, который симулирует серию игр с рандомными ходами. В каждом запуске ходы генерируются случайным образом, и игра завершается победой X, победой O или ничьей.
   - **Цель**: Проверить стабильность и корректность работы игры при множественных последовательных запусках с рандомными ходами. Тест собирает статистику по победам X, O и ничьям.
   - **Сценарий**: В каждом тесте случайно выбирается игрок, который начинает игру, затем случайно генерируются ходы, приводящие к одному из трех возможных исходов (победа X, победа O, ничья). В конце теста выводится сводная статистика по всем сыгранным играм.
   - **AllureId**: `001`

Спасибо за интерес к этому проекту!🚀
``` 
