import java.io.*;
import java.util.Scanner;

public class FileManager {

    private final int size = 100;

    private String[] doctors = new String[size];
    private String[] patients = new String[size];
    private String[] appointments = new String[size];
    private String[] users = new String[size];

    private int dCount = 0;
    private int pCount = 0;
    private int aCount = 0;
    private int uCount = 0;

    //load files
    public void loadAll() {
        loadFile("doctors.txt", 1);
        loadFile("patients.txt", 2);
        loadFile("appointments.txt", 3);
        loadFile("users.txt", 4);
    }
    private void loadFile(String fileName, int type) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (type == 1) doctors[dCount++] = line;
                if (type == 2) patients[pCount++] = line;
                if (type == 3) appointments[aCount++] = line;
                if (type == 4) users[uCount++] = line;
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error loading " + fileName);
        }
    }

    // save file
    private void saveFile(String fileName, String[] arr, int count) {
        try {
            FileWriter fw = new FileWriter(fileName);

            for (int i = 0; i < count; i++) {
                fw.write(arr[i] + "\n");
            }

            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving " + fileName);
        }
    }

    //add
    public void addDoctor(String d) {
        doctors[dCount++] = d;
        saveFile("doctors.txt", doctors, dCount);
    }

    public void addPatient(String p) {
        patients[pCount++] = p;
        saveFile("patients.txt", patients, pCount);
    }

    public void addAppointment(String a) {
        appointments[aCount++] = a;
        saveFile("appointments.txt", appointments, aCount);
    }

    public void addUser(String u) {
        users[uCount++] = u;
        saveFile("users.txt", users, uCount);
    }

    // delete appo
  public void deleteAppointment(String id) {
    String[] temp = new String[size];
    int newCount = 0;

    for (int i = 0; i < aCount; i++) {
        // نقوم بتقسيم السطر بناءً على الفاصلة
        String[] parts = appointments[i].split(",");
        if (!parts[0].equals(id)) {
            temp[newCount++] = appointments[i];
        }
    }
    appointments = temp;
    aCount = newCount;

    saveFile("appointments.txt", appointments, aCount);
}

    // login
    public boolean login(String username, String password) {

        for (int i = 0; i < uCount; i++) {

            String[] parts = users[i].split(",");

            if (parts[0].equals(username) && parts[1].equals(password)) {
                return true;
            }
        }

        return false;
    }
    public void updateAppointmentStatus(String id, String newStatus) {
    for (int i = 0; i < aCount; i++) {
        String[] parts = appointments[i].split(",");
        
        if (parts[0].equals(id)) {
            String currentStatus = parts[5];

            if (currentStatus.equalsIgnoreCase("cancelled") && newStatus.equalsIgnoreCase("completed")) {
                System.out.println("Error: A cancelled appointment cannot be marked as completed.");
                return;
            }
            appointments[i] = parts[0] + "," + parts[1] + "," + parts[2] + "," 
                            + parts[3] + "," + parts[4] + "," + newStatus;
            
            
            saveFile("appointments.txt", appointments, aCount);
            System.out.println("Appointment status updated to: " + newStatus);
            return;
        }
    }
    System.out.println("Appointment ID not found.");
}
}