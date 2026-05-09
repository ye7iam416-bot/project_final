public class Admin extends User {

    public Admin(String id,String name,String username,
    String password) {
        super(id, name, username, password, null);
    }
    public boolean addDoctor(Doctor[] doctors,int doctorCount,
    Doctor doctor) {
        if (doctor == null) {
            System.out.println("Invalid doctor.");
            return false;
        }
        if (doctorCount >= doctors.length) {
            System.out.println("Doctors list is full.");
            return false;
        }
        for (int i = 0; i < doctorCount; i++) {
            if (doctors[i].getId()
                    .equalsIgnoreCase(doctor.getId())) {
                System.out.println("Doctor ID already exists.");
                return false;
            }
        }
        doctors[doctorCount] = doctor;
        return true;
    }
    public boolean registerPatient(Patient[] patients,int patientCount,Patient patient) {
        if (patient == null) {
            System.out.println("Invalid patient.");
            return false;
        }
        if (patientCount >= patients.length) {
            System.out.println("Patients list is full.");
            return false;
        }
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getId()
                    .equalsIgnoreCase(patient.getId())) {
                System.out.println("Patient ID already exists.");
                return false;
            }
        }
        patients[patientCount] = patient;
        return true;
    }
    public void assignPatientToDoctor(Patient patient,Doctor doctor) {
        if (patient == null || doctor == null) {
            System.out.println("Invalid data.");
            return;
        }
        patient.setAssignedDoctor(doctor);
        doctor.addPatient(patient);
        System.out.println("Assign successful.");
    }

    public boolean createAppointment(Appointment[] appointments,int appointmentCount,
    Appointment appointment,Doctor doctor,Patient patient) {
        if (doctor == null ||patient == null ||appointment == null) {
            System.out.println("Invalid data.");
            return false;
        }
        if (appointmentCount >= appointments.length) {
            System.out.println("Appointments list is full.");
            return false;
        }
        if (patient.getAssignedDoctor() != doctor) {
            System.out.println("Patient is not assigned to this doctor.");
            return false;
        }
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId()
                    .equalsIgnoreCase(
                            appointment.getAppointmentId())) {
                System.out.println("Appointment ID already exists.");
                return false;
            }
            if (appointments[i].getDoctorId().equals(doctor.getId())&&
                    appointments[i].getDate().equals(appointment.getDate())&&
                    appointments[i].getTime().equals(appointment.getTime())&&
                    !appointments[i].getStatus()
                            .equalsIgnoreCase("Cancelled")) {
                System.out.println(
                        "Doctor already has appointment at this time.");
                return false;
            }
        }
        appointments[appointmentCount] = appointment;
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        return true;
    }

    public Patient searchPatientById(Patient[] patients,int count,String id) {
        for (int i = 0; i < count; i++) {
            if (patients[i].getId().equalsIgnoreCase(id)) {
                return patients[i];
            }
        }
        return null;
    }
    public Doctor searchDoctorById(Doctor[] doctors,int count,String id) {
        for (int i = 0; i < count; i++) {
            if (doctors[i].getId().equalsIgnoreCase(id)) {
                return doctors[i];
            }
        }
        return null;
    }
    public void viewAllDoctors(Doctor[] doctors, int count) {
        if (count == 0) {
            System.out.println(
                    "There are no doctors in the system.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("----- Doctor "+ (i + 1) + " -----");
            doctors[i].displayInfo();
        }
        System.out.println("-------------------");
    }

    public void viewAllPatients(Patient[] patients,int count) {
        if (count == 0) {
            System.out.println(
                    "There are no patients in the system.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("----- Patient "+ (i + 1) + " -----");
            patients[i].displayInfo();
        }
        System.out.println("-------------------");
    }
    public void viewAllAppointments(Appointment[] appointments,int count) {
        if (count == 0) {
            System.out.println(
                    "There are no appointments in the system.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("----- Appointment "+ (i + 1) + " -----");
            if (appointments[i] != null) {
                appointments[i].displayAppointment();
            }
        }
        System.out.println("-------------------");
    }

    public void generateReports(Doctor[] doctors,int doctorCount,Patient[] patients,
    int patientCount,Appointment[] appointments,int appointmentCount) {
        System.out.println("====> SYSTEM REPORT <====");
        System.out.println("---> General Statistics <---");
        System.out.println("Total Doctors: "+ doctorCount);
        System.out.println("Total Patients: "+ patientCount);
        System.out.println("Total Appointments: "+ appointmentCount);
        int confirmed = 0;
        int completed = 0;
        int cancelled = 0;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i] == null) {
                continue;
            }
            String status =appointments[i].getStatus();
            if (status.equalsIgnoreCase("Confirmed")) {
                confirmed++;
            } else if (status.equalsIgnoreCase("Completed")) {
                completed++;
            } else if (status.equalsIgnoreCase("Cancelled")) {
                cancelled++;
            }
        }

        System.out.println("---> Appointment Status <---");
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Completed: " + completed);
        System.out.println("Cancelled: " + cancelled);
        int[] doctorAppointments =
                new int[doctorCount];
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i] == null) {
                continue;
            }
            for (int j = 0; j < doctorCount; j++) {
                if (appointments[i].getDoctorId()
                        .equals(doctors[j].getId())) {
                    doctorAppointments[j]++;
                }
            }
        }

        System.out.println("---> Top 3 Doctors <---");
        for (int k = 0; k < 3; k++) {
            int maxIndex = -1;
            int maxValue = -1;
            for (int i = 0; i < doctorCount; i++) {
                if (doctorAppointments[i] > maxValue) {
                    maxValue = doctorAppointments[i];
                    maxIndex = i;
                }
            }
            if (maxIndex == -1 || maxValue <= 0) {
                break;
            }
            System.out.println((k + 1)+ ". Dr. "+ doctors[maxIndex].getName()+ " -> "
                            + maxValue+ " appointments");

            doctorAppointments[maxIndex] = -1;
        }
        System.out.println("===== END OF REPORT =====");
        System.out.println("-------------------");
    }
}