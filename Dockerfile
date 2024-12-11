# Используем официальный образ OpenJDK с Alpine Linux для уменьшения размера образа
FROM openjdk:17-alpine

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем jar файл приложения в контейнер
COPY target/jira-1.0.jar /app/jira-1.0.jar

# Копируем файл properties в контейнер
COPY src/main/resources/application.yaml /app/application.yaml

# Копируем файл .env для передачи переменных окружения
COPY src/main/resources/.env /app/.env



# Даём права на выполнение
RUN chmod +x jira-1.0.jar

# Открываем порт
EXPOSE 8082

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "jira-1.0.jar"]









# Запуск приложения
#ENTRYPOINT ["java", "-jar", "/app/jira-1.0.jar"]
#ENTRYPOINT ["java", "-jar", "jira-1.0.jar"]
#CMD ["sh", "-c", "java -jar jira-1.0.jar"]
# Открываем порт приложения
#EXPOSE 8082
