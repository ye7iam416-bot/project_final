import java.util.Scanner;

public class Main {
    static Scanner  input = new Scanner(System.in);
    static Admin A1 = new Admin("A001", "Admin Name", "admin", "admin123");
    static Doctor D1 = new Doctor("D001", "Doctor Name", "doctor", "doctor123", "Cardiology", "Heart Department", new String[100], new String[100], 0, 0);
    static Patient P1 = new Patient("P001", "Patient Name", "patient", "patient123", 30, "Male", "D001", "1234567890", new String[100], 0);
    public static void main(String[] args) {
        logIn();

    
        
        
    }

public static void logIn () {


           
    System.out.println("====> LOGIN <====");
    System.out.println("1. Admin");
    System.out.println("2. Doctor");
    System.out.println("3. Patient");
    System.out.println("4. Exit");
    System.out.print(">> Choose : ");

    int choose = input.nextInt();

    switch (choose) {
        case 1:
            System.out.print("Username Admin >> ");
            String adminUsername = input.next();
            System.out.print("Password Admin >> ");
            String adminPassword = input.next();

            if (adminUsername.equals(A1.getUsername()) && adminPassword.equals(A1.getPassword())) {
                 System.out.println("\nAdmin login successful. Welcome !" );
                AdminMenu ();
                
                } else {
                System.out.println(">> Invalid Admin details. Please try again.");
                } 
                 
            break;
        case 2:
            System.out.print("Doctor ID >>");
            String doctorID = input.next(); 
            if (doctorID.equals(D1.getID())) {
                DoctorMenu();
            } else {
                System.out.println("Invalid Doctor ID. Please try again.");
                
            }
            
            break;
        case 3:
            System.out.print("Patient ID >>");
            String patientID = input.next(); 
            if (patientID.equals(P1.getID())) {
                PatientMenu();
            } else {
                System.out.println("Invalid Patient ID. Please try again.");
                
            }
            
            break;
        case 4:
            System.out.println("Exiting the system. Goodbye !");
            return;
            
                
        default:
                System.out.println("Invalid choice. Please try again.");
            break;
    }

}

private static void AdminMenu (){

    while (true) {

        System.out.println("\n====> Admin Menu <====");
        System.out.println("1. Add Doctor ");
        System.out.println("2. Register Patient");
        System.out.println("3. Assign Patient to Doctor");
        System.out.println("4. Create Appointment");
        System.out.println("5. View All Doctors");
        System.out.println("6. View All Patients");
        System.out.println("7. View All Appointments");
        System.out.println("8. Search Patient By ID");
        System.out.println("9. Search Doctor By ID");
        System.out.println("10. Genrate Reports");
        System.out.println("11. Save Data");
        System.out.println("12. Log Out");

        System.out.print(">> Choose : ");

        int choise = input.nextInt();

        switch (choise) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
            case 8:
                
                break;
            case 9:
                
                break;
            case 10:
                
                break;
            case 11:
                
                break;
            case 12:
                
                break;
        
            default:
                break;
        }
        
    }
}

public static void DoctorMenu (){


}

public static void PatientMenu (){



}

public static void log_Admin (){


}


public static void log_Doctor (){


}


public static void log_Patient (){


}

}