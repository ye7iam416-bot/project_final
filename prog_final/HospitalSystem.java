public class HospitalSystem {
    Doctor[] doctors = new Doctor[100];
    Patient[] patients = new Patient[100];
    Appointment[] appointments = new Appointment[100];
    int doctorCount = 0;
    int patientCount = 0;
    int appointmentCount = 0;
    
    public HospitalSystem(Doctor[] doctors, Patient[] patients, Appointment[] appointments, int doctorCount,
            int patientCount, int appointmentCount) {
        this.doctors = doctors;
        this.patients = patients;
        this.appointments = appointments;
        this.doctorCount = doctorCount;
        this.patientCount = patientCount;
        this.appointmentCount = appointmentCount;
    }

    
    
}
