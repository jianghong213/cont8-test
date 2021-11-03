FROM java:8
ADD ./server/target/*.jar /root/docker/app.jar
ENTRYPOINT ["nohup","java","-jar","/root/docker/app.jar","&"]


