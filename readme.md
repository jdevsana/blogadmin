# Getting Started

### Runing the project
* From any IDE: open the project and run the project as usual.

#### Maven commands to run the project
* Go to the project directory and run the following commands:
    * mvn compile
    * mvn install
    * java -jar target/blogadmin.jar (windows: java -jar target\blogadmin.jar)
* To get all the users:
    * http://localhost:8085/api/v1/blog/users
* To get all the posts:
    * http://localhost:8085/api/v1/blog/posts
* To get all the posts Mapped to relavent user:
    * http://localhost:8085/api/v1/blog/postbyuser

**dependency:**  Java Version 11 

**Limitations**: First request could be failed after runing the application.  