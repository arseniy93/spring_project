﻿# spring_project
1 Разобраться со структурой проекта (onboarding). <h1>+</h1></p>
2 Удалить социальные сети: vk, yandex. Easy task  <h1>+</h1> </p>
3 Вынести чувствительную информацию в отдельный проперти файл: логин пароль БД идентификаторы для OAuth регистрации/авторизации настройки почты Значения этих проперти должны считываться при старте сервера из переменных окружения машины. Easy task <h1>+</h1> </p>
4 Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL. Для этого нужно определить 2 бина, и выборка какой из них использовать должно определяться активным профилем Spring. H2 не поддерживает все фичи, которые есть у PostgreSQL, поэтому тебе прийдется немного упростить скрипты с тестовыми данными.<h1>+</h1> </p>
5 Написать тесты для всех публичных методов контроллера ProfileRestController. Хоть методов только 2, но тестовых методов должно быть больше, т.к. нужно проверить success and unsuccess path. <h1>+</h1> </p>
6 Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload чтоб он использовал современный подход для работы с файловой системмой. Easy task <h1>+</h1> </p>
7 Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе). Фронт делать необязательно. Таблица task_tag уже создана. Добавить подсчет времени сколько задача находилась в работе и тестировании. Написать 2 метода на уровне сервиса, которые параметром принимают задачу и возвращают затраченное время: Сколько задача находилась в работе  (ready_for_review минус in_progress ). Сколько задача находилась на тестировании (done минус ready_for_review).  <h1>+</h1> </p> ![image](https://github.com/user-attachments/assets/92e40b9c-8867-435a-9ecf-92813d1de5f5) ![image](https://github.com/user-attachments/assets/d0aad21d-1f7a-411e-9c49-4fde45c0849d)


8 Написать Dockerfile для основного сервера   <h1>+</h1> </p>
9 Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx. Для nginx используй конфиг-файл config/nginx.conf. При необходимости файл конфига можно редактировать. Hard task   <h1>-</h1> </p>
10 Добавить локализацию минимум на двух языках для шаблонов писем (mails) и стартовой страницы index.html.   <h1>+</h1> </p> ![image](https://github.com/user-attachments/assets/529bf5b2-644b-4253-8bc1-c50de65080d9)

11 Переделать механизм распознавания «свой-чужой» между фронтом и беком с JSESSIONID на JWT. Из сложностей – тебе придётся переделать отправку форм с фронта, чтоб добавлять хедер аутентификации. Extra-hard task  <h1>-</h1> </p>
