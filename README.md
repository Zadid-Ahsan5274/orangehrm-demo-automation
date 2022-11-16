# orangehrm-demo-automation

## Tools and technologies used
- Java
- Intellij IDEA
- Gradle(Build Automation Tool)
- TestNG(Test framework)
- Selenium(Web automation tool)
- Allure(Reporting tool)

## Scenario of this project
- Admin will login to dashboard
- Admin will create 2 employees
- Employee username and password will be saved
- Employee will login to his/her account
- Employee wil update/edit some of his/her info

## How to run this project
- clone this project
- open intellij idea -> open the cloned project in intellij idea
- open terminal to the project location -> hit the following command: ```gradle clean test```
- for generating allure reports: hit the following commads: 
  ```allure generate allure-results --clean -o allure-report```
  ```allure serve allure-results```
  
  ## Prerequisites
  - JDK(preferably LTS versions for example: 8 or 11), gradle, allure must be installed and set path in environment variable in your system
  
  ## Test Cases Documentation
  https://docs.google.com/spreadsheets/d/1EXpNtswu2bgzyrHEncNHhBOcQohnxXoANPj5yAKvi6Y/edit?usp=sharing
  
  ## Automation Recording
  https://user-images.githubusercontent.com/82231014/202165012-b164e33a-dd4f-43ed-82b8-cd3a9a9a81d8.mp4

  ## Reports Screenshots
  ![ss1](https://user-images.githubusercontent.com/82231014/202162317-317a13ee-c6da-417d-bb73-bd5ccd36a72c.png)
  ![ss2](https://user-images.githubusercontent.com/82231014/202162336-2dc577f6-bda5-4f92-8cf4-d518eb1c29eb.png)

  
  
