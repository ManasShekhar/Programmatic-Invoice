## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## How to Setup the Project

# DATABASE RELATED INITIALIZATION

- You should DOWNLOAD(if not) the `JCONNECTOR.JAR` file and configure it in your library directory.
- The database connectivity commands should be changed in each package. For example
  String username = "admin";
  String dpassword = "123123";
  to
  String username = `USERNAME`;
  String dpassword = `PASSWORD`;

- The File named `SQL_COMMANDS.sql` should be runned in your MySQL database workspace.
- The `DEMO_DATA.sql` should be runned too for demo data for testing purposes. (OPTIONAL).

# PROGRAM RELATED INITIALIZATION

- Directly Run the `App.JAVA` to run the project.
