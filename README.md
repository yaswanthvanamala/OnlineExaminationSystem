# Online Examination System (OES)

## About the Project
The **Online Examination System (OES)** is a console-based Java application that allows students to take multiple-choice examinations and teachers to upload question papers and view student results.

The application uses object-oriented programming concepts and Java collections to manage student and teacher details, exam questions, answers, and result history during program execution.

## Features

### Student
- Register with a name, password, mobile number, and student ID.
- Log in using registered credentials.
- Select an available subject and take an exam.
- Answer multiple-choice questions using `A`, `B`, `C`, or `D`.
- Take each teacher's exam only once during the current program run.
- View exam scores, correct and wrong answers, grades, and pass/fail status.
- View profile details and log out.

### Teacher
- Register with a name, password, mobile number, teacher ID, and subject.
- Log in using registered credentials.
- Upload a question paper by entering the questions and typing `EXIT` on a new line when finished.
- Enter the correct answer for each question.
- View results for registered students.

### Examination and Results
- Five-minute exam time limit.
- Input validation for names, passwords, mobile numbers, IDs, and answers.
- Automatic score calculation using the teacher's answer key.
- Grade calculation:
  - **A:** 90% and above
  - **B:** 80%–89%
  - **C:** 70%–79%
  - **D:** 60%–69%
  - **F:** Below 60%
- Console messages displayed using ANSI colors.

## Technologies Used
- Java
- Core Java and Object-Oriented Programming (OOP)
- Java Collections Framework (`ArrayList`)
- `Scanner` for console input
- Regular expressions for input validation
- Java time measurement using `System.currentTimeMillis()`

## Project Structure

The current project is implemented in one Java source file:

```text
OES/
└── OES.java
```

The file contains the following classes:

| Class | Responsibility |
|---|---|
| `OES` | Main menu and application entry point |
| `Student` | Student profile, exam participation, and result history |
| `Teacher` | Teacher profile, question-paper upload, and result viewing |
| `RegisterService` | Student and teacher registration |
| `LoginService` | Student and teacher login |
| `Exam` | Calculates exam scores |
| `ExamTimer` | Tracks and displays exam duration |
| `ExamRecord` | Stores a student's subject, score, and answers |
| `Result` | Displays results and calculates grades |
| `Input` | Shared console input |
| `Colors` | ANSI color constants for console output |

## Requirements
- Java Development Kit (JDK) installed.
- A terminal or IDE such as Visual Studio Code, Eclipse, or IntelliJ IDEA.

Check your Java installation:

```bash
java -version
javac -version
```

## How to Run

1. Save the source code in a file named `OES.java`.
2. Open a terminal in the directory containing the file.
3. Compile the program:

   ```bash
   javac OES.java
   ```

4. Run the application:

   ```bash
   java OES
   ```

If your IDE is configured to compile and run Java programs, you can run the `main()` method in the `OES` class directly.

## How to Use

1. Start the application.
2. Choose **Register** from the main menu.
3. Register a student and/or teacher.
4. Log in as a teacher and upload a question paper for a subject.
5. Enter the correct option for every question.
6. Log in as a student and select an available subject.
7. Answer the questions before the five-minute time limit.
8. View the result from the student menu. The teacher can also view registered students' results.

## Current Limitations
- The application supports up to **two students and two teachers** per program run.
- Data is stored in memory and is lost when the application exits.
- Login credentials are checked against registered objects; there is no database or persistent user store.
- The exam time limit is five minutes.
- The application is console-based; it does not currently include a web interface.
- ANSI colors may not display in every terminal.

## Future Improvements
- Store student, teacher, question-paper, and result data in a database such as MySQL.
- Add a graphical or web interface.
- Support more students, teachers, and subjects.
- Improve exam timing so the time limit is enforced consistently while input is being entered.
- Add stronger authentication and secure password storage.
- Add question randomization and detailed answer review.

## Learning Outcomes
This project demonstrates the use of:
- Classes, objects, and constructors
- Encapsulation through class-based data organization
- Conditional statements and loops
- `ArrayList` and collections
- Input validation using regular expressions
- Login and registration workflows
- Basic exam evaluation and grade calculation

## Author
VANAMALA YASWANTH
