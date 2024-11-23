# Tasker Swingao

## How to Clone and Run the Project

### Prerequisites

- **Java 21**: Ensure you have Java 21 installed. You can download and install Java 21 from the [official Oracle website](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) or use a package manager suitable for your operating system.
- **Maven**: Ensure you have Maven installed. You can download and install Maven from the [official Maven website](https://maven.apache.org/download.cgi).

### Steps to Clone and Build the Project

1. **Clone the Repository**:
    - Open a terminal or command prompt.
    - Run the following command to clone the repository:
      ```sh
      git clone https://github.com/dofun12/tasker-swingao.git 
      ```
    - Navigate to the project directory:
      ```sh
      cd tasker-swingao
      ```

2. **Build the Project**:
    - Run the following Maven command to build the project and generate the executable JAR:
      ```sh
      mvn clean package
      ```
    - The executable JAR file will be generated in the `target` directory with the name `tasker-swingao-1.0-SNAPSHOT-jar-with-dependencies.jar`.

### How to Execute the JAR

1. **Navigate to the `target` folder**:
    - Change the directory to the `target` folder where the JAR file is located. For example:
      ```sh
      cd target
      ```
    - You can also run the jar on release folder
      ```sh
      cd release
      java -jar tasker-swingao.jar
      ```

2. **Run the JAR file**:
    - Use the following command to execute the JAR file:
      ```sh
      java -jar tasker-swingao-1.0-SNAPSHOT-jar-with-dependencies.jar
      ```

Make sure to replace `tasker-swingao-1.0-SNAPSHOT-jar-with-dependencies.jar` with the actual name of your JAR file if it differs.

## Requirements

- Java 21
- Maven
- The JAR file located in the `target` folder

## Example

```sh
cd target
java -jar tasker-swingao-1.0-SNAPSHOT-jar-with-dependencies.jar

