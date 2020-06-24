# computer-database

Content

This training material holds a sequence of steps and features to implement in a Computer Database webapp.
Here is the macro-planning and timeline of all milestones:

    t0 - Start of the project
    t0+3 - Base Architecture, CLI (Add / Edit features), Logging
    t0+11 - Web UI, Maven, Unit Tests, jQuery Validation, Backend Validation
    t0+15 - Search, OrderBy, Transactions, Connection-Pool
    t0+20 - Spring integration
    t0+24 - Spring MVC integration, JDBC Template, i18n
    t0+31 - Maven Multi-modules, Spring Security, Hibernate ORM (JPA, Criteria, QueryDSL, Spring Data JPA)
    t0+34 - Front (Angular JS, Angular or React)
    t0+44 - Web Services, end of project
    t0+48 - Project presentation to sales & tech audience

Installation
1. Database

Create a local MySQL server.
Execute scripts 1-SCHEMA.sql, 2-PRIVILEGES.sql and 3-ENTRIES.sql in config/db.
Schema created: computer-database-db Tables created: company, computer
User created: admincdb with password: qwerty1234
2. IDE
2.1. Eclipse

    Add your project to the current workspace: File -> Import -> Existing projects into workspace
    Create a new Tomcat 8.0 Server: Follow steps HERE
    In your project properties, select Project facets, convert your project to faceted form, and tick Dynamic Web Module (3.0) and Java (1.8)
    Select Runtime tab (in the previous project facets menu) and check the Tomcat 8.0 Server created above as your project runtime

2.2. IntelliJ IDEA

    Add your project to the current workspace: Import Project, select Create project from existing sources
    Create a new Tomcat 8.0 Server: Run -> Edit Configurations and point it to your local Tomcat directory (button Configure...)
    Set project structure: In File -> Project Structure, add an Artifact with default options (Artifact tab)

3. Git repository

    Create your own github account, and initialize a new git repository called "computer-database".
    After the initial commit, add and commit a meaningful .gitignore file.

You are ready to start coding.
4. Start coding
4.1. Layout

Your customer requested to build a computer database application. He owns about 500+ computers made by different manufacturers (companies such as Apple, Acer, Asus...).
Ideally, each computer would contain the following: a name, the date when it was introduced, eventually the date when it was discontinued, and the manufacturer. Obviously, for some reasons, the existing data is incomplete, and he requested that only the name should remain mandatory when adding a computer, the other fields being filled when possible. Furthermore, the date it was discontinued must be greater than the one he was introduced. The list of computers can be modified, meaning your customer should be able to list, add, delete, and update computers. The list of computers should also be pageable.
The list of companies should be exhaustive, and therefore will not require any update, deletion etc...
4.2. Command line interface client

The first iteration will be dedicated to implement a first working version of your computer database, with a CLI-UI.
The CLI will have the following features:

    List computers
    List companies
    Show computer details (the detailed information of only one computer)
    Create a computer
    Update a computer
    Delete a computer

4.2.1. Start

You will organize your project among several packages, such as model, persistence, service, ui, mapper...
Please use Singleton patterns where it makes sense, and implement your own Persistence management layer (for connections).
4.2.2. Pages

Now that your app's main features work, implement the pageable feature. We recommend the use of a Page class, containing your entities and the page information.
4.2.3. CODE REVIEW 1, logging (t0 + 3 days)

Important Points: Architecture (daos, mappers, services, models, exceptions etc...)? Singleton, IOC patterns? Validation (dirty checking?)? Date API? Secure inputs?
Javadoc? Comments? Use Slf4j-api logging library, with the most common implementation: logback.
4.3. CLI + Web interface client

Now that your backend skeleton is working, we want to add a second more user-friendly UI, such as a Web-UI.
As it will require more and more libraries (more JARs to include in the build path etc...), we should consider using a build manager. Moreover, testing is a very important aspect of QA, and testing libraries should be implemented before going any further, the same for logging.
Then, you can work on implementing all features on the provided static pages, using JSTL, Tags, Servlets, JSPs...
4.3.1. Maven, Logging & Unit testing

Refactor your project tree to match maven standards. (Tip: you should exit eclipse, move folders around, and reimport your project using File -> Import -> Existing maven projects).
Include necessary libraries such as mysql-connector, JUnit, Mockito, Slf4j, and create the test classes for the backend you have already developed (N.B.: This is against TDD best practices. You should always code your tests simulteanously while developing your features).
Creating test classes implies to take into account ALL possibilities: Illegal calls, legal calls with invalid data, and legal calls with valid data.
Add and configure the Maven checktyle plugin with the checkstyle.xml and suppressions.xml provided in config/checkstyle/
4.3.2. Implement listing and computer add features in the web-ui

Using the provided template https://github.com/excilys/training-java/tree/master/static, integrate the previous features using Servlets, JSPs, JSTL.
Use DTOs (Data Transfer Object) to transport only relevant data to the JSPs.
Implement Computer listing (paginated), and add features. Warning: All features will be implemented and tested using Selenium automated with maven.
4.3.3. Secure through validation

Implement both frontend (jQuery) and backend validation in the web-ui.
4.3.4. CODE REVIEW 2 (t0 + 11 days)

Important Points: Maven structure? Library scopes? Architecture (daos, mappers, services, models, dtos, controllers, exceptions, validators)? Validation? Unit test coverage? What about selenium integration into maven? JSTL Tags and HTML documents structure.
Prepare a point about Threading (Connections, concurrency), and Transactions.
