import java.io.*;
import java.util.Scanner;

public class FileManager {

    // ================= DOCTORS =================
    public static void saveDoctors(Doctor[] doctors, int count) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("doctors.txt"));

            for (int i = 0; i < count; i++) {
                if (doctors[i] != null) {
                    writer.println(
                            doctors[i].getId() + "," +
                            doctors[i].getName() + "," +
                            doctors[i].getUsername() + "," +
                            doctors[i].getPassword() + "," +
                            doctors[i].getPhone() + "," +
                            doctors[i].getSpecialization() + "," +
                            doctors[i].getDepartment()
                    );
                }
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving doctors file.");
        }
    }

    public static int loadDoctors(Doctor[] doctors) {
        int count = 0;

        try {
            File file = new File("doctors.txt");

            if (!file.exists()) {
                file.createNewFile();
                return 0;
            }

            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {

                String line = read.nextLine();

                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                doctors[count++] = new Doctor(
                        data[0], data[1], data[2],
                        data[3], data[4],
                        data[5], data[6]
                );
            }

            read.close();

        } catch (Exception e) {
            System.out.println("Error loading doctors file.");
        }

        return count;
    }

    // ================= PATIENTS =================
    public static void savePatients(Patient[] patients, int count) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("patients.txt"));

            for (int i = 0; i < count; i++) {
                if (patients[i] != null) {
                    writer.println(
                            patients[i].getId() + "," +
                            patients[i].getName() + "," +
                            patients[i].getUsername() + "," +
                            patients[i].getPassword() + "," +
                            patients[i].getPhone() + "," +
                            patients[i].getAge() + "," +
                            patients[i].getGender()
                    );
                }
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving patients file.");
        }
    }

    public static int loadPatients(Patient[] patients) {
        int count = 0;

        try {
            File file = new File("patients.txt");

            if (!file.exists()) {
                file.createNewFile();
                return 0;
            }

            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {

                String line = read.nextLine();

                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                patients[count++] = new Patient(
                        data[0], data[1], data[2],
                        data[3], data[4],
                        Integer.parseInt(data[5]),
                        data[6]
                );
            }

            read.close();

        } catch (Exception e) {
            System.out.println("Error loading patients file.");
        }

        return count;
    }

    // ================= APPOINTMENTS =================
    public static void saveAppointments(Appointment[] appointments, int count) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("appointments.txt"));

            for (int i = 0; i < count; i++) {
                if (appointments[i] != null) {
                    writer.println(
                            appointments[i].getAppointmentId() + "," +
                            appointments[i].getPatientId() + "," +
                            appointments[i].getDoctorId() + "," +
                            appointments[i].getDate() + "," +
                            appointments[i].getTime() + "," +
                            appointments[i].getStatus()
                    );
                }
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving appointments file.");
        }
    }

    public static int loadAppointments(Appointment[] appointments) {
        int count = 0;

        try {
            File file = new File("appointments.txt");

            if (!file.exists()) {
                file.createNewFile();
                return 0;
            }

            Scanner read = new Scanner(file);

            while (read.hasNextLine()) {

                String line = read.nextLine();

                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                appointments[count++] = new Appointment(
                        data[0], data[1], data[2],
                        data[3], data[4], data[5]
                );
            }

            read.close();

        } catch (Exception e) {
            System.out.println("Error loading appointments file.");
        }

        return count;
    }

    // ================= UPDATE HELPERS =================
    public static void updateAppointmentsFile(Appointment[] appointments, int count) {
        saveAppointments(appointments, count);
    }

    public static void updateDoctorsFile(Doctor[] doctors, int count) {
        saveDoctors(doctors, count);
    }

    public static void updatePatientsFile(Patient[] patients, int count) {
        savePatients(patients, count);
    }
}