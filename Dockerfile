# AdminPanel
FROM openjdk:17
LABEL app=AdminPanel
WORKDIR /app/adminpael
COPY .src/main/java/app/telegramgptbot/adminpanel /app/adminpael
CMD ["java", "-jar", "database-service.jar"]

# ChatBot
FROM openjdk:17
LABEL app=ChatBot
WORKDIR /app/chatbot
COPY .src/main/java/app/telegramgptbot/telegrambot /app/chatbot
CMD ["java", "-jar", "telegram-bot-service.jar"]