FROM openjdk:11
EXPOSE 8080
ADD target/UserBloggerAngularProject.jar UserBloggerAngularProject.jar
ENTRYPOINT ["java","-jar","/UserBloggerAngularProject.jar"]