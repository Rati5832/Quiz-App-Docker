# Simple Quiz Application with docker

if you have downloaded docker in your machine you can simply build and run application through docker.


To build and run the application using Docker, follow these steps:

*CORE*
Ensure that your preferred build tool is installed on your system and properly configured to generate JAR files. 
You have to generate jar files while building an application.

Windows and Linux 
1. You need to open  Terminal or Command Prompt
2. cd <"Project Path"> ( Navigate to the project directory )
3. docker build -t image-name .
4. docker run -p 8080:8080 image-name

What is Docker?
Docker allows developers to package their applications and dependencies into containers, providing consistency across different machines and environments. 
Containers are lightweight and portable, making it easy to deploy applications across development, testing, and production environments.
   

