import java.util.Scanner;

public class Admin extends User {

    public Admin(String id, String name, String username, String password) {
        super(id, name, username, password, null);
    }

    // ================= ADD DOCTOR =================
    public void addDoctor(Doctor[] doctors, int doctorCount, Doctor doctor) {
        doctors[doctorCount] = doctor;
    }

    // ================= REGISTER PATIENT =================
    public void registerPatient(Patient[] patients, int patientCount, Patient patient) {
        patients[patientCount] = patient;
    }

    // ================= ASSIGN PATIENT =================
    public void assignPatientToDoctor(Patient patient, Doctor doctor) {
        if (patient == null || doctor == null) {
            System.out.println("Invalid data.");
            return;
        }

        patient.setAssignedDoctor(doctor);
        doctor.addPatient(patient);
        System.out.println("Assign successful.");
    }

    // ================= CREATE APPOINTMENT =================
    public boolean createAppointment(Appointment[] appointments,
                                     int appointmentCount,
                                     Appointment appointment,
                                     Doctor doctor,
                                     Patient patient) {

        if (doctor == null || patient == null || appointment == null) {
            System.out.println("Invalid data.");
            return false;
        }

        appointments[appointmentCount] = appointment;
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);

        return true;
    }

    // ================= SEARCH =================
    public Patient searchPatientById(Patient[] patients, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getId().equals(id)) {
                return patients[i];
            }
        }
        return null;
    }

    public Doctor searchDoctorById(Doctor[] doctors, int count, String id) {
        for (int i = 0; i < count; i++) {
            if (doctors[i].getId().equals(id)) {
                return doctors[i];
            }
        }
        return null;
    }

    // ================= VIEW ALL =================
    public void viewAllDoctors(Doctor[] doctors, int count) {
        if (count == 0) {
        System.out.println("there is no doctors in the system.");
        return;
    }
        for (int i = 0; i < count; i++) {
            doctors[i].displayInfo();
        }
    }

    public void viewAllPatients(Patient[] patients, int count) {
        if (count == 0) {
        System.out.println("there is no patients in the system.");
        return;
    }
        for (int i = 0; i < count; i++) {
            patients[i].displayInfo();
        }
    }

    public void viewAllAppointments(Appointment[] appointments, int count) {
        if (count == 0) {
        System.out.println("there is no appointments in the system.");
        return;
    }
        for (int i = 0; i < count; i++) {
            if (appointments[i] != null) {
                appointments[i].displayAppointment();
            }
        }
         
        
    }

    // ================= REPORTS =================
public void generateReports(Doctor[] doctors, int dc,
                            Patient[] patients, int pc,
                            Appointment[] appointments, int ac) {

    System.out.println("\n===== SYSTEM REPORT =====");

    // ================= BASIC COUNTS =================
    System.out.println("\n--- General Statistics ---");
    System.out.println("Total Doctors: " + dc);
    System.out.println("Total Patients: " + pc);
    System.out.println("Total Appointments: " + ac);

    // ================= APPOINTMENT STATUS COUNT =================
    int scheduled = 0;
    int completed = 0;
    int cancelled = 0;

    for (int i = 0; i < ac; i++) {

        if (appointments[i] == null) continue;

        String status = appointments[i].getStatus();

        if (status.equalsIgnoreCase("Scheduled")) {
            scheduled++;
        } else if (status.equalsIgnoreCase("Completed")) {
            completed++;
        } else if (status.equalsIgnoreCase("Cancelled")) {
            cancelled++;
        }
    }

    System.out.println("\n--- Appointment Status ---");
    System.out.println("Scheduled: " + scheduled);
    System.out.println("Completed: " + completed);
    System.out.println("Cancelled: " + cancelled);

    // ================= TOP 3 DOCTORS =================
    int[] docAppointments = new int[dc];
    for (int i = 0; i < ac; i++) {
        if (appointments[i] == null) continue;
        for (int j = 0; j < dc; j++) {
            if (doctors[j] == null) continue;
            if (appointments[i].getDoctorId()
                    .equals(doctors[j].getId())) {
                docAppointments[j]++;
            }
        }
    }
    System.out.println("\n--- Top 3 Doctors ---");
    for (int k = 0; k < 3; k++) {
        int maxIndex = -1;
        int maxValue = -1;
        for (int i = 0; i < dc; i++) {
            if (docAppointments[i] > maxValue) {
                maxValue = docAppointments[i];
                maxIndex = i;
            }
        }
        if (maxIndex == -1 || maxValue == 0) break;
        System.out.println((k + 1) + ". Dr. "
                + doctors[maxIndex].getName()
                + " -> " + maxValue + " appointments");
        docAppointments[maxIndex] = -1; // exclude
    }
}
}