import java.util.Scanner;

public class HospitalSystem {
    Scanner sc = new Scanner(System.in);

    Doctor[] doctors = new Doctor[100];
    Patient[] patients = new Patient[100];
    Appointment[] appointments = new Appointment[100];
    int doctorCount;
    int patientCount;
    int appointmentCount;

    Admin admin = new Admin("123","admin","admin","123");

    public HospitalSystem() {

        doctorCount = FileManager.loadDoctors(doctors);
        patientCount = FileManager.loadPatients(patients);
        appointmentCount = FileManager.loadAppointments(appointments);
    }

    public void start() {

        while (true) {
            System.out.println("\n===== Hospital System =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Doctor");
            System.out.println("3. Login as Patient");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();
                    if (admin.getUsername().equals(username)&&
                            admin.getPassword().equals(password)) {
                        adminMenu();
                    }
                    else {
                        System.out.println("Invalid Login.");
                    }
                    break;
                case 2:
                    doctorLogin();
                    break;
                case 3:
                    patientLogin();
                    break;
                case 4:
                    FileManager.saveDoctors(doctors, doctorCount);
                    FileManager.savePatients(patients, patientCount);
                    FileManager.saveAppointments(appointments, appointmentCount);
                    System.out.println("System Closed.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
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
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Doctor ID: ");
                    String did = sc.nextLine();
                    System.out.print("Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Username: ");
                    String duser = sc.nextLine();
                    System.out.print("Password: ");
                    String dpass = sc.nextLine();
                    System.out.print("Phone: ");
                    String dphone = sc.nextLine();
                    System.out.print("Specialization: ");
                    String spec = sc.nextLine();
                    System.out.print("Department: ");
                    String dep = sc.nextLine();
                    Doctor doctor = new Doctor(did,dname,duser,dpass,dphone,spec,dep);
                    admin.addDoctor(doctors,doctorCount,doctor);
                    doctorCount++;
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Patient ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Username: ");
                    String puser = sc.nextLine();
                    System.out.print("Password: ");
                    String ppass = sc.nextLine();
                    System.out.print("Phone: ");
                    String pphone = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();          
                    sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    Patient patient = new Patient(pid,pname,puser,ppass,pphone,
                            age,gender);
                    admin.registerPatient(
                            patients,
                            patientCount,
                            patient);
                    patientCount++;
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Patient ID: ");
                    String pId = sc.nextLine();
                    System.out.print("Doctor ID: ");
                    String dId = sc.nextLine();
                    Patient p = admin.searchPatientById(
                            patients,
                            patientCount,
                            pId);
                    Doctor d = admin.searchDoctorById(
                            doctors,
                            doctorCount,
                            dId);
                    admin.assignPatientToDoctor(p, d);
                    break;
                
                case 4:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String aid = sc.nextLine();
                    System.out.print("Patient ID: ");
                    String apPid = sc.nextLine();
                    System.out.print("Doctor ID: ");
                    String apDid = sc.nextLine();
                    System.out.print("Date: ");
                    String date = sc.nextLine();
                    System.out.print("Time: ");
                    String time = sc.nextLine();
                    Appointment appointment = new Appointment(aid,apPid,apDid,date,time,
                            "Confirmed");
                    Patient patientObj = admin.searchPatientById(patients,patientCount,apPid);
                    Doctor doctorObj = admin.searchDoctorById(
                            doctors,doctorCount,apDid);
                    boolean created = admin.createAppointment(
                            appointments,appointmentCount,
                            appointment,doctorObj,patientObj);
                    if (created) {
                        appointmentCount++;
                    }
                    break;
                case 5:
                    admin.viewAllDoctors(doctors,doctorCount);
                    break;
                case 6:
                    admin.viewAllPatients(patients,patientCount);
                    break;
                case 7:
                    admin.viewAllAppointments(appointments,appointmentCount);
                    break;
                case 8:
                    sc.nextLine();
                    System.out.print("Enter Patient ID: ");
                    String searchPid = sc.nextLine();
                    Patient foundPatient = admin.searchPatientById(
                            patients,
                            patientCount,
                            searchPid);
                    if (foundPatient != null) {
                        foundPatient.displayInfo();
                    }
                    else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 9:
                    sc.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String searchDid = sc.nextLine();
                    Doctor foundDoctor = admin.searchDoctorById(
                            doctors,
                            doctorCount,
                            searchDid);
                    if (foundDoctor != null) {
                        foundDoctor.displayInfo();
                    }
                    else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 10:
                    admin.generateReports(doctors,doctorCount,patients,
                            patientCount,appointments,appointmentCount);
                    break;
                case 11:
                    FileManager.saveDoctors(doctors,doctorCount);
                    FileManager.savePatients(patients,patientCount);
                    FileManager.saveAppointments(appointments,appointmentCount);
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

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
            if (doctors[i].getUsername()
                    .equals(username)&&
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
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    doctor.displayInfo();
                    break;
                case 2:
                    doctor.viewPatients(patients, choice);
                    break;
                case 3:
                    doctor.viewAppointments();
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String appId = sc.nextLine();
                    System.out.print("New Status: ");
                    String status = sc.nextLine();
                    Appointment found = null;
                    for (int i = 0; i < appointmentCount; i++) {
                        if (appointments[i]
                                .getAppointmentId()
                                .equals(appId)) {
                            found = appointments[i];
                            break;
                        }
                    }
                    doctor.updateAppointmentStatus(appId, status);
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
            if (patients[i].getUsername()
                    .equals(username)&&
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
                    String appId = sc.nextLine();
                    System.out.print("Patient ID: ");
                    String patientId = sc.nextLine();
                    System.out.print("Doctor ID: ");
                    String doctorId = sc.nextLine();
                    System.out.print("Date: ");
                    String date = sc.nextLine();
                    System.out.print("Time: ");
                    String time = sc.nextLine();
                    System.out.print("Status: ");
                    String status = sc.nextLine();
                    appointmentCount = patient.bookAppointment( appointments, appointmentCount, appId, patientId, doctorId, date, time, status);
                    break;
                case 5:
                    sc.nextLine();
                    System.out.print("Appointment ID: ");
                    String cancelId = sc.nextLine();
                    Appointment found = null;
                    for (int i = 0; i < appointmentCount; i++) {
                        if (appointments[i]
                                .getAppointmentId()
                                .equals(cancelId)) {
                            found = appointments[i];
                            break;
                        }
                    }
                    patient.cancelAppointment(cancelId);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

        }

    }

}