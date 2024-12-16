# Build stage
FROM bellsoft/liberica-openjdk-debian:21 AS builder
ENV WORK /workspace

# Gradle 설정 및 소스 복사
COPY build.gradle settings.gradle gradlew $WORK/
COPY gradle $WORK/gradle
COPY src $WORK/src

WORKDIR $WORK

# Gradle을 통한 JAR 빌드
RUN apt-get update && apt-get install -y dos2unix && dos2unix gradlew
RUN chmod +x gradlew
RUN ./gradlew clean bootJar --parallel --no-daemon

# Runtime stage
FROM bellsoft/liberica-openjdk-debian:21

# 빌드된 JAR 파일을 복사
ARG JAR_FILE=workspace/build/libs/*.jar
COPY --from=builder $JAR_FILE /app.jar

# entrypoint.sh 파일을 복사하고 실행 권한을 부여
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# ENTRYPOINT 변경: entrypoint.sh 파일을 실행하도록 설정
ENTRYPOINT ["/entrypoint.sh"]
