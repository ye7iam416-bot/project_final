public class Admin extends User {

    public Admin(String ID, String name, String username, String password) {
        super(ID, name, username, password);
    }
    public void addDoctor(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        String id = input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = input.nextLine();
        System.out.print("Enter Department: ");
        String department = input.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = input.nextLine();

        Doctor doctor = new Doctor(id, name, username, password, specialization, department, phone);
        system.addDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    public void registerPatient(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Patient ID: ");
        String id = input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Username: ");
        String username = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.print("Enter Gender: ");
        String gender = input.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = input.nextLine();

        Patient patient = new Patient(id, name, username, password, age, gender, phone);
        system.addPatient(patient);
        System.out.println("Patient registered successfully.");
    }

    public void assignPatientToDoctor(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Patient ID: ");
        String patientId = input.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = input.nextLine();

        Patient patient = system.findPatientById(patientId);
        Doctor doctor = system.findDoctorById(doctorId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        patient.setAssignedDoctor(doctor);
        doctor.addPatient(patient);
        System.out.println("Patient assigned to doctor successfully.");
    }

    public void createAppointment(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Appointment ID: ");
        String appId = input.nextLine();
        System.out.print("Enter Patient ID: ");
        String patientId = input.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = input.nextLine();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = input.nextLine();
        System.out.print("Enter Time (HH:MM): ");
        String time = input.nextLine();

        if (date.isEmpty()) {
            System.out.println("Date cannot be empty.");
            return;
        }
        if (time.isEmpty()) {
            System.out.println("Time cannot be empty.");
            return;
        }

        Patient patient = system.findPatientById(patientId);
        Doctor doctor = system.findDoctorById(doctorId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        if (patient.getAssignedDoctor() == null) {
            System.out.println("Patient is not assigned to any doctor.");
            return;
        }
        if (doctor.hasAppointmentAt(date, time)) {
            System.out.println("Doctor already has an appointment at this date and time.");
            return;
        }

        Appointment appointment = new Appointment(appId, patientId, doctorId, date, time, "confirmed");
        system.addAppointment(appointment);
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        System.out.println("Appointment created successfully.");
    }

    public void viewAllDoctors(HospitalSystem system) {
        List<Doctor> doctors = system.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.println(d);
        }
    }

    public void viewAllPatients(HospitalSystem system) {
        List<Patient> patients = system.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    public void viewAllAppointments(HospitalSystem system) {
        List<Appointment> appointments = system.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }

    public void searchPatientById(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Patient ID: ");
        String id = input.nextLine();
        Patient patient = system.findPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println(patient);
        }
    }

    public void searchDoctorById(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        String id = input.nextLine();
        Doctor doctor = system.findDoctorById(id);
        if (doctor == null) {
            System.out.println("Doctor not found.");
        } else {
            System.out.println(doctor);
        }
    }

 

    public void saveData(HospitalSystem system) {
        FileManager.saveDoctors(system.getAllDoctors());
        FileManager.savePatients(system.getAllPatients());
        FileManager.saveAppointments(system.getAllAppointments());
        System.out.println("Data saved successfully.");
    }

    public void showMenu(HospitalSystem system) {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Assign Patient to Doctor");
            System.out.println("4. Create Appointment");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Patients");
            System.out.println("7. View All Appointments");
            System.out.println("8. Search Patient by ID");
            System.out.println("9. Search Doctor by ID");
            System.out.println("10. Generate Reports");
            System.out.println("11. Save Data");
            System.out.println("12. Logout");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1: addDoctor(system); break;
                case 2: registerPatient(system); break;
                case 3: assignPatientToDoctor(system); break;
                case 4: createAppointment(system); break;
                case 5: viewAllDoctors(system); break;
                case 6: viewAllPatients(system); break;
                case 7: viewAllAppointments(system); break;
                case 8: searchPatientById(system); break;
                case 9: searchDoctorById(system); break;
                case 10: generateReports(system); break;
                case 11: saveData(system); break;
                case 12: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 12);
    
}
