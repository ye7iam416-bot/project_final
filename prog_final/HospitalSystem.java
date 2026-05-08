import java.util.Scanner;

public class HospitalSystem {

    Scanner sc = new Scanner(System.in);
    Doctor[] doctors = new Doctor[100];
    Patient[] patients = new Patient[100];
    Appointment[] appointments = new Appointment[100];
    int doctorCount;
    int patientCount;
    int appointmentCount;
    Admin admin = new Admin("123", "admin", "admin", "123");
    public HospitalSystem() {
        doctorCount = FileManager.loadDoctors(doctors);
        patientCount = FileManager.loadPatients(patients);
        appointmentCount = FileManager.loadAppointments(appointments);
        restoreAppointments();
    }

    public void restoreAppointments() {
        for (int i = 0; i < appointmentCount; i++) {
            Appointment ap = appointments[i];
            Doctor doctor = admin.searchDoctorById(
                    doctors,
                    doctorCount,
                    ap.getDoctorId());
            Patient patient = admin.searchPatientById(
                    patients,
                    patientCount,
                    ap.getPatientId());
            if (doctor != null) {
                doctor.addAppointment(ap);
            }
            if (patient != null) {
                patient.addAppointment(ap);
                if (doctor != null) {
                    patient.setAssignedDoctor(doctor);
                    doctor.addPatient(patient);
                }
            }
        }
    }

    public void start() {

        while (true) {

            System.out.println("\n===== Hospital System =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Doctor");
            System.out.println("3. Login as Patient");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    doctorLogin();
                    break;
                case 3:
                    patientLogin();
                    break;
                case 4:
                    saveAllData();
                    System.out.println("System Closed.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void adminLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (admin.getUsername().equals(username)&& admin.getPassword().equals(password)) {
            adminMenu();
        } else {
            System.out.println("Invalid Login.");
        }
    }
    public void adminMenu() {
        while (true) {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Assign Patient To Doctor");
            System.out.println("4. Create Appointment");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Patients");
            System.out.println("7. View All Appointments");
            System.out.println("8. Search Patient By ID");
            System.out.println("9. Search Doctor By ID");
            System.out.println("10. Generate Reports");
            System.out.println("11. Save Data");
            System.out.println("12. Logout");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addDoctorMenu();
                    break;
                case 2:
                    registerPatientMenu();
                    break;
                case 3:
                    assignPatientMenu();
                    break;
                case 4:
                    createAppointmentMenu();
                    break;
                case 5:
                    admin.viewAllDoctors(doctors, doctorCount);
                    break;
                case 6:
                    admin.viewAllPatients(patients, patientCount);
                    break;
                case 7:
                    admin.viewAllAppointments(appointments, appointmentCount);
                    break;
                case 8:
                    searchPatientMenu();
                    break;
                case 9:
                    searchDoctorMenu();
                    break;
                case 10:
                    admin.generateReports(doctors,doctorCount,patients,patientCount,
                            appointments,appointmentCount);
                    break;
                case 11:
                    saveAllData();
                    System.out.println("Data saved successfully.");
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void addDoctorMenu() {
        sc.nextLine();
        System.out.print("Doctor ID: ");
        String id = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Specialization: ");
        String specialization = sc.nextLine();

        System.out.print("Department: ");
        String department = sc.nextLine();

        Doctor doctor = new Doctor(id,name,username,password,phone,specialization,
        department);

        boolean added = admin.addDoctor(doctors, doctorCount, doctor);

        if (added) {
            doctorCount++;
            System.out.println("Doctor added successfully.");
        }
    }
    public void registerPatientMenu() {
        sc.nextLine();

        System.out.print("Patient ID: ");
        String id = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Gender: ");
        String gender = sc.nextLine();

        Patient patient = new Patient(id,name,username,password,phone,age,gender);

        boolean added = admin.registerPatient(patients,patientCount,patient);
        if (added) {
            patientCount++;
            System.out.println("Patient registered successfully.");
        }
    }
    public void assignPatientMenu() {
        sc.nextLine();

        System.out.print("Patient ID: ");

        String patientId = sc.nextLine();

        System.out.print("Doctor ID: ");

        String doctorId = sc.nextLine();

        Patient patient = admin.searchPatientById(patients,patientCount,patientId);

        Doctor doctor = admin.searchDoctorById(doctors,doctorCount,doctorId);

        admin.assignPatientToDoctor(patient, doctor);
    }

    public void createAppointmentMenu() {

        sc.nextLine();

        System.out.print("Appointment ID: ");
        String appointmentId = sc.nextLine();

        System.out.print("Patient ID: ");
        String patientId = sc.nextLine();

        System.out.print("Doctor ID: ");
        String doctorId = sc.nextLine();

        System.out.print("Date: ");
        String date = sc.nextLine();

        System.out.print("Time: ");
        String time = sc.nextLine();

        Patient patient = admin.searchPatientById(patients,patientCount,patientId);

        Doctor doctor = admin.searchDoctorById(doctors,doctorCount,doctorId);

        Appointment appointment = new Appointment(appointmentId,patientId,
        doctorId,date,time,"Confirmed");

        boolean created = admin.createAppointment(appointments,appointmentCount,
                appointment,doctor,patient);
        if (created) {
            appointmentCount++;
            System.out.println("Appointment created successfully.");
        }
    }
    public void searchPatientMenu() {
        sc.nextLine();

        System.out.print("Enter Patient ID: ");

        String patientId = sc.nextLine();

        Patient patient = admin.searchPatientById(patients,patientCount,patientId);
        if (patient != null) {
            patient.displayInfo();
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void searchDoctorMenu() {
        sc.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = sc.nextLine();
        Doctor doctor = admin.searchDoctorById(doctors,doctorCount,doctorId);

        if (doctor != null) {
            doctor.displayInfo();
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public void doctorLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        Doctor doctor = null;
        for (int i = 0; i < doctorCount; i++) {

            if (doctors[i].getUsername().equals(username)&&
                    doctors[i].getPassword().equals(password)) {
                doctor = doctors[i];
                break;
            }
        }
        if (doctor == null) {
            System.out.println("Invalid Login.");
            return;
        }
        doctorMenu(doctor);
    }

    public void doctorMenu(Doctor doctor) {
        while (true) {
            System.out.println("\n===== Doctor Menu =====");
            System.out.println("1. View My Profile");
            System.out.println("2. View Assigned Patients");
            System.out.println("3. View My Appointments");
            System.out.println("4. Update Appointment Status");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doctor.displayInfo();
                    break;
                case 2:
                    doctor.viewPatients();
                   break;
                case 3:
                    doctor.viewAppointments();
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    System.out.print("New Status: ");
                    String status = sc.nextLine();
                    doctor.updateAppointmentStatus(appointmentId,status);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public void patientLogin() {
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        Patient patient = null;
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getUsername().equals(username)&&
                    patients[i].getPassword().equals(password)) {
                patient = patients[i];
                break;
            }
        }
        if (patient == null) {
            System.out.println("Invalid Login.");
            return;
        }
        patientMenu(patient);
    }
    public void patientMenu(Patient patient) {
        while (true) {
            System.out.println("\n===== Patient Menu =====");
            System.out.println("1. View My Profile");
            System.out.println("2. View Assigned Doctor");
            System.out.println("3. View My Appointments");
            System.out.println("4. Book Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    patient.displayInfo();
                    break;
                case 2:
                    patient.viewDoctor();
                    break;
                case 3:
                    patient.viewAppointments();
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String appointmentId = sc.nextLine();
                    System.out.print("Patient ID: ");
                    String patientId = sc.nextLine();
                    System.out.print("Doctor ID: ");
                    String doctorId = sc.nextLine();
                    System.out.print("Date: ");
                    String date = sc.nextLine();
                    System.out.print("Time: ");
                    String time = sc.nextLine();
                    appointmentCount = patient.bookAppointment(appointments,
                      appointmentCount,appointmentId,
                     patientId,doctorId,date,time,"Confirmed");

                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String cancelId = sc.nextLine();
                    patient.cancelAppointment(cancelId);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public void saveAllData() {
        FileManager.saveDoctors(doctors, doctorCount);
        FileManager.savePatients(patients, patientCount);
        FileManager.saveAppointments(
                appointments,
                appointmentCount);
    }
}