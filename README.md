# module7
#####Default configuration:
  ```
# Web
server.port=8080
server.contextPath=/module7

# H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2

# Datasource
spring.datasource.url=jdbc:h2:file:~/test
spring.datasource.username=sa
spring.datasource.password=

# Mailing
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000

# LOG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate=INFO

# Other
borrow.check.intervals=300

  ```
  
You can change any of these in various ways:   
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

####Application Start
simply execute the following command in the roo of the project:
  ```
  gradlew bootRun
  ```
####Model relations
```
  ++++++++                ++++++++++++++++++++++++++                 ++++++++
  | USER |-- has many --> | BORROW &| SUBSCRIPTION | <-- has many -- | BOOK |
  ++++++++                ++++++++++++++++++++++++++                 ++++++++
  ```