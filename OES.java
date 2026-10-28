import java.util.*;

class Colors {

    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN = "\u001B[36m";
    static final String WHITE = "\u001B[37m";
    static final String BOLD = "\u001B[1m";

}

class Input {

    static Scanner sc = new Scanner(System.in);
}
class ExamRecord {

    String subject;
    int score;
    ArrayList<Character> studentAnswers = new ArrayList<>();

    ExamRecord(String subject, int score, ArrayList<Character> answers) {
        this.subject = subject;
        this.score = score;
        this.studentAnswers.addAll(answers);
    }
}

class Student {

    String name;
    String password;
    long mobileNo;
    int studentId;
  int score;

boolean teacher1ExamCompleted = false;
boolean teacher2ExamCompleted = false;

ArrayList<Character> studentAnswers = new ArrayList<>();
ArrayList<ExamRecord> examHistory = new ArrayList<>();

    Student(String name, String password, long mobileNo, int studentId) {
        this.name = name;
        this.password = password;
        this.mobileNo = mobileNo;
        this.studentId = studentId;
    }

    void displayProfile() {
        System.out.println(Colors.BLUE + "Student Name : " + Colors.RESET + name);
        System.out.println(Colors.BLUE + "Student Id : " + Colors.RESET + studentId);
        System.out.println(Colors.BLUE + "Mobile : " + Colors.RESET + mobileNo);
    }

    void studentMenu(Teacher teacher) {
        while (true) {
            System.out.println(Colors.YELLOW + "\n******** STUDENT MENU ********" + Colors.RESET);
            System.out.println(Colors.BLUE + "1. Take Exam" + Colors.RESET);
            System.out.println(Colors.BLUE + "2. View Result" + Colors.RESET);
            System.out.println(Colors.RED + "3. Back" + Colors.RESET);

            int choice = Input.sc.nextInt();

            switch (choice) {
                case 1:
                    takeExam(teacher);
                    break;

                case 2:
                    viewResult(teacher);
                    break;
                case 3:
                    return;
                default:
                    System.out.println(Colors.RED + "Invalid Choice!!" + Colors.RESET);
            }
        }
    }

    void login() {
        System.out.println(Colors.GREEN + "Student Login Successful" + Colors.RESET);
    }

    void takeExam(Teacher teacher) {
        if (teacher == null) {
            System.out.println(Colors.RED + "No teacher is registered" + Colors.RESET);
            return;
        }
        if (!teacher.examUploaded) {
            System.out.println(Colors.RED + "Question Paper is not uploaded yet." + Colors.RESET);
            return;
        }
        if (teacher.teacherId == 1 && teacher1ExamCompleted) {
    System.out.println(Colors.RED + "You have already completed this exam." + Colors.RESET);
    return;
}

if (teacher.teacherId == 2 && teacher2ExamCompleted) {
    System.out.println(Colors.RED + "You have already completed this exam." + Colors.RESET);
    return;
}
        ExamTimer timer = new ExamTimer();
        timer.startTimer();
        System.out.println(Colors.YELLOW + ">>>>>>> EXAM STARTED <<<<<<<" + Colors.RESET);
        System.out.println(teacher.questionPaper);
        studentAnswers.clear();
        long startTime = System.currentTimeMillis();
        long timeLimit = 5 * 60 * 1000;
        for (int i = 1; i <= teacher.totalQuestions; i++) {
            long currentTime = System.currentTimeMillis();
            long remainingTime = timeLimit - (currentTime - startTime);

            if (remainingTime <= 0) {
                System.out.println(Colors.RED + "\nTime is Over!!!" + Colors.RESET);
                System.out.println("Exam Auto Submitted.");
                break;
            }

            long minutes = remainingTime / (60 * 1000);
            long seconds = (remainingTime % (60 * 1000)) / 1000;

            System.out.println("\nQuestion " + i);
            System.out.println("Time Left : " + minutes + " min " + seconds + " sec");
            System.out.print("Enter Answer : ");

            char ans;

            while (true) {
                ans = Character.toUpperCase(Input.sc.next().charAt(0));

                if (ans == 'A' || ans == 'B' || ans == 'C' || ans == 'D') {
                    studentAnswers.add(ans);
                    break;
                } else {
                    System.out.println(Colors.RED + "Invalid Input! Enter only A, B, C or D." + Colors.RESET);
                }
            }
        }

        System.out.println(Colors.GREEN + "Exam Submitted Successfully..." + Colors.RESET);
        timer.stopTimer();
        Exam exam = new Exam();
score = exam.calculateScore(this, teacher);

if (teacher.teacherId == 1) {
    teacher1ExamCompleted = true;
}

if (teacher.teacherId == 2) {
    teacher2ExamCompleted = true;
}

ExamRecord record = new ExamRecord(teacher.subject, score, studentAnswers);
examHistory.add(record);

System.out.println("Your Score : " + score + "/" + teacher.totalQuestions);
    }

    void viewResult(Teacher teacher) {

    for (ExamRecord record : examHistory) {

        if (record.subject.equals(teacher.subject)) {

            Result result = new Result();
            result.displayResult(this, teacher, record);

            return;
        }
    }

    System.out.println(Colors.RED + "Please take the exam first." + Colors.RESET);
}

    public void logout() {
        System.out.println(Colors.RED + "Student Logout" + Colors.RESET);
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }
}

class Teacher {

    String name;
    String password;
    long mobileNo;
    int teacherId;
    String subject;

    int totalQuestions;
    String questionPaper;

    ArrayList<Character> answerKey = new ArrayList<>();

    boolean examUploaded = false;

    Teacher(String name, String password, long mobileNo, int teacherId, String subject) {
        this.name = name;
        this.password = password;
        this.mobileNo = mobileNo;
        this.teacherId = teacherId;
        this.subject = subject;
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

    void displayProfile() {
        System.out.println(Colors.BLUE + "Teacher Name : " + Colors.RESET + name);
        System.out.println(Colors.BLUE + "Teacher Id : " + Colors.RESET + teacherId);
        System.out.println(Colors.BLUE + "Subject : " + Colors.RESET + subject);
    }

    void login() {
        System.out.println(Colors.GREEN + "Teacher Login Successful" + Colors.RESET);
    }

    void createQuestion() {
        answerKey.clear();
        questionPaper = "";
        examUploaded = false;
        System.out.print(Colors.CYAN + "Enter Total Number of Questions : " + Colors.RESET);
        totalQuestions = Input.sc.nextInt();
        Input.sc.nextLine();

        System.out.println(Colors.YELLOW + "\n>>>>>>> UPLOAD QUESTION PAPER<<<<<<<" + Colors.RESET);

        String paper = "";

        System.out.println(Colors.BOLD + "Paste the complete question paper." + Colors.RESET);
        System.out.println("Type EXIT in a new line when finished........!!!!\n");

        while (true) {
            String line = Input.sc.nextLine();

            if (line.equalsIgnoreCase("EXIT")) {
                break;
            }

            paper = paper + line + "\n";
        }

        questionPaper = paper;

        System.out.println(Colors.BLUE + "\nEnter Correct Answers" + Colors.RESET);

        for (int i = 1; i <= totalQuestions; i++) {

    while (true) {

        System.out.print(Colors.CYAN + "Answer for Question " + Colors.RESET + i + Colors.CYAN + " : " + Colors.RESET);

        char ans = Character.toUpperCase(Input.sc.next().charAt(0));

        if (ans == 'A' || ans == 'B' || ans == 'C' || ans == 'D') {
            answerKey.add(ans);
            break;
        } else {
            System.out.println(Colors.RED + "Invalid Input! Enter only A, B, C or D." + Colors.RESET);
        }
    }
}

        System.out.println(Colors.GREEN + "\nQuestion Paper Uploaded Successfully..." + Colors.RESET);
        examUploaded = true;
    }

    void viewResults(Student student) {

    if (student == null) {
        System.out.println(Colors.MAGENTA + "No Student Registered." + Colors.RESET);
        return;
    }

    for (ExamRecord record : student.examHistory) {

        if (record.subject.equals(this.subject)) {

            Result result = new Result();
            result.displayResult(student, this, record);

            return;
        }
    }

    System.out.println(Colors.MAGENTA + "Student has not taken this exam." + Colors.RESET);
}

    public void logout() {
        System.out.println(Colors.RED + "Teacher Logout" + Colors.RESET);
    }

    void teacherMenu(RegisterService rs) {
        while (true) {
            System.out.println(Colors.YELLOW + "\n>>>>>> TEACHER MENU <<<<<<" + Colors.RESET);
            System.out.println(Colors.BLUE + "( 1 )----> Upload Question Paper" + Colors.RESET);
            System.out.println(Colors.BLUE + "( 2 )----> View Results" + Colors.RESET);
            System.out.println(Colors.RED + "( 3 )---->Logout" + Colors.RESET);

            int choice = Input.sc.nextInt();

            switch (choice) {
                case 1:
                    createQuestion();
                    break;

                case 2:

                    System.out.println("\nSelect Student");
                    System.out.println("1. Student 1");
                    System.out.println("2. Student 2");

                    int ch = Input.sc.nextInt();

                    switch (ch) {
                        case 1:

                            viewResults(rs.studentObj1);
                            break;

                        case 2:

                            viewResults(rs.studentObj2);
                            break;

                        default:

                            System.out.println("Invalid Choice");
                    }

                    break;

                case 3:
                    logout();
                    return;

                default:
                    System.out.println(Colors.RED + "Invalid Choice" + Colors.RED);
            }
        }
    }
}

class RegisterService {

    Student studentObj1;
    Student studentObj2;

    Teacher teacherObj1;
    Teacher teacherObj2;

    void registerMenu() {
        System.out.println(Colors.BLUE + "( 1 )----> Student register" + Colors.RESET);
        System.out.println(Colors.BLUE + "( 2 )----> Teacher register" + Colors.RESET);
        System.out.println(Colors.BLUE + "( 3 )----> Main Menu" + Colors.RESET);
        int choice1 = Input.sc.nextInt();
        if (choice1 == 1) {
            registerStudent();

        } else if (choice1 == 2) {
            registerTeacher();

        } else if (choice1 == 3) {
            return;
        } else {
            System.out.println(Colors.RED + "Invalid Input enter " + Colors.RESET);
            System.out.println(Colors.GREEN + "Re-enter " + Colors.RESET);
            registerMenu();
        }
    }

    void registerStudent() {

        System.out.println(Colors.YELLOW + "[[[[Student Registration]]]]" + Colors.RESET);

        String s;
        while (true) {
            System.out.print("Enter Name : ");
            s = Input.sc.next();

            if (s.matches("[A-Za-z]{3,20}")) {
                break;
            } else {
                System.out.println("Invalid Name! Only alphabets (3-20 letters).");
            }
        }

        String p;
        while (true) {
            System.out.print("Enter Password : ");
            p = Input.sc.next();

            if (p.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
                break;
            } else {
                System.out.println("Password must contain:");
                System.out.println("1 Uppercase");
                System.out.println("1 Lowercase");
                System.out.println("1 Number");
                System.out.println("1 Special Character");
                System.out.println("Minimum 8 Characters");
            }
        }

        long m;
        while (true) {
            System.out.print("Enter Mobile Number : ");
            String mobile = Input.sc.next();

            if (mobile.matches("[6-9][0-9]{9}")) {
                m = Long.parseLong(mobile);
                break;
            } else {
                System.out.println("Invalid Mobile Number.");
            }
        }

        int id;
        while (true) {
            System.out.print("Enter Student ID : ");
            id = Input.sc.nextInt();

            if (id > 0) {
                break;
            } else {
                System.out.println("Invalid Student ID.");
            }
        }

        if (studentObj1 == null) {
            studentObj1 = new Student(s, p, m, id);

            System.out.println(Colors.GREEN + "Student 1 Registered Successfully." + Colors.RESET);
            studentObj1.displayProfile();
        } else if (studentObj2 == null) {
            studentObj2 = new Student(s, p, m, id);

            System.out.println(Colors.GREEN + "Student 2 Registered Successfully." + Colors.RESET);
            studentObj2.displayProfile();
        } else {
            System.out.println(Colors.RED + "Maximum Student Registration Limit Reached." + Colors.RESET);
        }
    }

    void registerTeacher() {

        System.out.println(Colors.YELLOW + "[[[[Teacher Registration]]]]" + Colors.RESET);

        String tn;
        while (true) {
            System.out.print("Enter Name : ");
            tn = Input.sc.next();

            if (tn.matches("[A-Za-z]{3,20}")) {
                break;
            }

            System.out.println("Invalid Name.");
        }

        String tp;
        while (true) {
            System.out.print("Enter Password : ");
            tp = Input.sc.next();

            if (tp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
                break;
            }

            System.out.println("Password must contain:");
            System.out.println("1 Uppercase");
            System.out.println("1 Lowercase");
            System.out.println("1 Number");
            System.out.println("1 Special Character");
            System.out.println("Minimum 8 Characters");
        }

        long tm;
        while (true) {
            System.out.print("Enter Mobile Number : ");
            String mobile = Input.sc.next();

            if (mobile.matches("[6-9][0-9]{9}")) {
                tm = Long.parseLong(mobile);
                break;
            }

            System.out.println("Invalid Mobile Number.");
        }

        int tid;
        while (true) {
            System.out.print("Enter Teacher ID : ");
            tid = Input.sc.nextInt();

            if (tid > 0) {
                break;
            }

            System.out.println("Invalid Teacher ID.");
        }

        String sub;
        while (true) {
            System.out.print("Enter Subject : ");
            sub = Input.sc.next();

            if (sub.matches("[A-Za-z ]+")) {
                break;
            }

            System.out.println("Invalid Subject.");
        }

        if (teacherObj1 == null) {
            teacherObj1 = new Teacher(tn, tp, tm, tid, sub);

            System.out.println(Colors.GREEN + "Teacher 1 Registered Successfully." + Colors.RESET);
            teacherObj1.displayProfile();
        } else if (teacherObj2 == null) {
            teacherObj2 = new Teacher(tn, tp, tm, tid, sub);

            System.out.println(Colors.GREEN + "Teacher 2 Registered Successfully." + Colors.RESET);
            teacherObj2.displayProfile();
        } else {
            System.out.println(Colors.RED + "Maximum Teacher Registration Limit Reached." + Colors.RESET);
        }
    }
}

class LoginService {

    RegisterService rs;

    LoginService(RegisterService rs) {
        this.rs = rs;
    }

    void studentLogin() {

        if (rs.studentObj1 == null && rs.studentObj2 == null) {
            System.out.println("No Student Registered.");
            return;
        }

        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter Name : ");
            String name = Input.sc.next();

            System.out.print("Enter Password : ");
            String password = Input.sc.next();

            if (rs.studentObj1 != null
                    && name.equals(rs.studentObj1.getName())
                    && password.equals(rs.studentObj1.getPassword())) {
                rs.studentObj1.login();
                if (rs.teacherObj1 == null && rs.teacherObj2 == null) {
                    System.out.println("No Teacher Registered.");
                    return;
                }
                if ((rs.teacherObj1 == null || !rs.teacherObj1.examUploaded)
                        && (rs.teacherObj2 == null || !rs.teacherObj2.examUploaded)) {
                    System.out.println("No Question Paper Uploaded Yet.");
                    return;
                }

               while (true) {

    System.out.println("\nSelect Subject");

    if (rs.teacherObj1 != null && rs.teacherObj1.examUploaded) {
        System.out.println("1. " + rs.teacherObj1.subject);
    }

    if (rs.teacherObj2 != null && rs.teacherObj2.examUploaded) {
        System.out.println("2. " + rs.teacherObj2.subject);
    }

    System.out.println("3. Back");

    int ch = Input.sc.nextInt();

    if (ch == 1) {
        rs.studentObj1.studentMenu(rs.teacherObj1);
    } else if (ch == 2) {
        rs.studentObj1.studentMenu(rs.teacherObj2);
    } else if (ch == 3) {
        return;
    } else {
        System.out.println("Invalid Choice");
    }
}}

            if (rs.studentObj2 != null
                    && name.equals(rs.studentObj2.getName())
                    && password.equals(rs.studentObj2.getPassword())) {
                rs.studentObj2.login();

                if (rs.teacherObj1 == null && rs.teacherObj2 == null) {
                    System.out.println("No Teacher Registered.");
                    return;
                }

                if ((rs.teacherObj1 == null || !rs.teacherObj1.examUploaded)
                        && (rs.teacherObj2 == null || !rs.teacherObj2.examUploaded)) {
                    System.out.println("No Question Paper Uploaded Yet.");
                    return;
                }

                while (true) {

    System.out.println("\nSelect Subject");

    if (rs.teacherObj1 != null && rs.teacherObj1.examUploaded) {
        System.out.println("1. " + rs.teacherObj1.subject);
    }

    if (rs.teacherObj2 != null && rs.teacherObj2.examUploaded) {
        System.out.println("2. " + rs.teacherObj2.subject);
    }

    System.out.println("3. Back");

    int ch = Input.sc.nextInt();

    if (ch == 1) {
        rs.studentObj2.studentMenu(rs.teacherObj1);
    } else if (ch == 2) {
        rs.studentObj2.studentMenu(rs.teacherObj2);
    } else if (ch == 3) {
        return;
    } else {
        System.out.println("Invalid Choice");
    }
}
            }
            attempts--;
            System.out.println("Invalid Username or Password");
            System.out.println("Attempts Left : " + attempts);
        }

        System.out.println("Account Locked.");
    }

    void teacherLogin() {

        if (rs.teacherObj1 == null && rs.teacherObj2 == null) {
            System.out.println("No Teacher Registered.");
            return;
        }
        int attempts = 3;

        while (attempts > 0) {

            System.out.print("Enter Name : ");
            String name = Input.sc.next();

            System.out.print("Enter Password : ");
            String password = Input.sc.next();

            if (rs.teacherObj1 != null
                    && name.equals(rs.teacherObj1.getName())
                    && password.equals(rs.teacherObj1.getPassword())) {
                rs.teacherObj1.login();
                rs.teacherObj1.teacherMenu(rs);
                return;
            }

            if (rs.teacherObj2 != null
                    && name.equals(rs.teacherObj2.getName())
                    && password.equals(rs.teacherObj2.getPassword())) {
                rs.teacherObj2.login();

                rs.teacherObj2.teacherMenu(rs);
                return;
            }

            attempts--;
            System.out.println("Invalid Username or Password");
            System.out.println("Attempts Left : " + attempts);
        }

        System.out.println("Account Locked.");
    }
}

class Exam {

    int calculateScore(Student student, Teacher teacher) {
        int score = 0;

        int answered = student.studentAnswers.size();

        for (int i = 0; i < answered; i++) {
            if (student.studentAnswers.get(i).equals(teacher.answerKey.get(i))) {
                score++;
            }
        }

        return score;
    }
}

class ExamTimer {

    long startTime;
    long endTime;

    void startTimer() {
        startTime = System.currentTimeMillis();
    }

    void stopTimer() {
        endTime = System.currentTimeMillis();

        long seconds = (endTime - startTime) / 1000;

        if (seconds > 300) {
            seconds = 300;
        }

        System.out.println(Colors.GREEN + "Exam Finished..." + Colors.RESET);
        System.out.println("Time Taken : " + seconds + " Seconds");
    }
}

class Result {

   void displayResult(Student student, Teacher teacher, ExamRecord record) {

    System.out.println("\nRESULT...........................");

    System.out.println("Student Name : " + student.getName());
    System.out.println("Subject : " + teacher.subject);
    System.out.println("Total Questions : " + teacher.totalQuestions);
    System.out.println("Score : " + record.score);

    int wrong = teacher.totalQuestions - record.score;

    System.out.println("Correct Answers : " + record.score);
    System.out.println("Wrong Answers : " + wrong);

    System.out.println("Grade : " + calculateGrade(record.score, teacher.totalQuestions));

    if (record.score >= teacher.totalQuestions / 2) {
        System.out.println("Result : " + Colors.GREEN + "PASS" + Colors.RESET);
    } else {
        System.out.println("Result : " + Colors.RED + "FAIL" + Colors.RESET);
    }
}

    char calculateGrade(int score, int totalQuestions) {
        double percentage = (score * 100.0) / totalQuestions;

        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}

 class OES {

    public static void main(String[] args) {
        RegisterService rs = new RegisterService();
        LoginService ls = new LoginService(rs);
        while (true) {
            System.out.println(Colors.YELLOW + ">>>>>>>>>>>> ONLINE EXAMINATION SYSTEM <<<<<<<<<<<" + Colors.RESET);

            System.out.println(Colors.CYAN + "1. Register" + Colors.RESET);

            System.out.println(Colors.CYAN + "2. Login" + Colors.RESET);

            System.out.println(Colors.RED + "3. Exit" + Colors.RESET);

            int choice = Input.sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(Colors.CYAN + "Register Menu" + Colors.RESET);
                    rs.registerMenu();

                    break;

                case 2:
                    System.out.println(Colors.BLUE + "Login Menu" + Colors.RESET);
                    System.out.println("( 1 )---->" + Colors.GREEN + "Student Login" + Colors.RESET);
                    System.out.println("( 2 )---->" + Colors.GREEN + "Teacher Login" + Colors.RESET);
                    System.out.println("( 3 )---->" + Colors.CYAN + "Main Menu" + Colors.RESET);

                    int ch = Input.sc.nextInt();

                    switch (ch) {
                        case 1:
                            ls.studentLogin();
                            break;

                        case 2:
                            ls.teacherLogin();
                            break;

                        case 3:
                            break;

                        default:
                            System.out.println(Colors.RED + "Invalid Choice" + Colors.RESET);
                    }
                    break;

                case 3:
                    System.out.println("Thank You........");
                    System.exit(0);
                    break;

                default:
                    System.out.println(Colors.RED + "Invalid Choice" + Colors.RESET);
            }

        }
    }
}