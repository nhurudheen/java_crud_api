FROM openjdk:8-jdk-alpine
RUN apk add --no-cache curl
RUN apk add --update curl
RUN apk add --no-cache tzdata
VOLUME /tmp
ENV TZ=Africa/Lagos
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
#HEALTHCHECK --interval=1m --timeout=10s --retries=3 --start-period=1m CMD curl --fail http://localhost:8080/actuator/health 2>&1 | grep UP || exit 1
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]