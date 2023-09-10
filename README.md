# Serianu Take Home
Take home test for the role of Sotware Engineer at [Serianu Limited](https://www.serianu.com/)

**Technology stack list**
* [x] [Springboot 3.1.3](https://spring.io/projects/spring-boot)
* [x] [Thymeleaf Template Engine](https://www.thymeleaf.org/)
* [x] [Bootstrap 5](https://getbootstrap.com/docs/5.0/getting-started/introduction/)
* [x] [Slack API](https://slack.com/)
* [x] [MySQL 8](https://www.mysql.com/)

## Getting started ###
### Setup MySQL configuration ###
1. Create a database. serianu is what is configured on our application.properties file. 
   For a different database name remember to also change the application.properties file.
       
   ``` CREATE DATABASE IF NOT EXISTS serianu;  ```
2. Create database users. 
   For a different username and password, remember to make changes on the application.properties file 
       ```CREATE USER 'serianuser'@'%' IDENTIFIED BY 'Pass123#'; ```
3. Grant permissions to this user
``` GRANT SELECT,INSERT,CREATE,ALTER,DROP,LOCK TABLES,CREATE TEMPORARY TABLES, DELETE,UPDATE,EXECUTE ON serianu.* TO 'serianuser'@'%'; ```
  
### Run the application ###
    
**Assumptions**
    
1. Have [Java version 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher installed
2. Have [Maven version 3.5 or higher](https://maven.apache.org/download.cgi) installed 
3. You have pulled the application to a local folder on your computer

Now Open a terminal window in the root directory of the application and run the command ``` mvn spring-boot:run```

On your favorite browser visit the link [http://localhost:9797](http://localhost:9797/) 

**Using the application**

1. Visit the [configuration link](http://localhost:9797/slack-configurations) and set up your channel ID and Slack URL
2. Go to the [notifications](http://localhost:9797/notifications) page and select channel and write down the message. Hit send message button to post a message to slack

**Other important links**

1. Posting to Slack channels using webhooks. Check out [Slack documentation](https://slack.dev/java-slack-sdk/guides/incoming-webhooks#:~:text=To%20enable%20this%20feature%2C%20visit,Webhook%20URL%20will%20be%20generated.)