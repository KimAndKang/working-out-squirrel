#!/bin/bash

# Gradle 빌드 및 리로드 실행
start_server() {
  (sleep 30; ./gradlew buildAndReload --continuous -PskipDownload=true -x Test) &
  java -Xms1400m -Xmx1400m -jar -Dspring.profiles.active=local /app.jar
}

start_server
