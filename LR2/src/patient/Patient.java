package patient;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String phoneNumber;
    private int medicalRecordNumber;
    private String diagnosis;
    
    public Patient(int id, String firstName, String lastName, String middleName,
            String address, String phoneNumber, int medicalRecordNumber, String diagnosis) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.medicalRecordNumber = medicalRecordNumber;
        this.diagnosis = diagnosis;
    }

    // Сетери
    public void setId(int id) {
        this.id = id;
    }
    
    public void setMedicalRecordNumber(int medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    
    // Гетери
    public int getId() {
        return id;
    }
    
    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return "Patient [" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' + 
                ", lastName='" + lastName + '\'' +
                ", middleName'=" + middleName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", medicalRecordNumber='" + medicalRecordNumber + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                "]";
    }

    public boolean checkDiagnosis(String diagnosis){
        return this.diagnosis.equalsIgnoreCase(diagnosis);
    }

    public boolean checkNumberBeginning(int digit){
        return this.phoneNumber.startsWith(String.valueOf(digit));
    }

    public boolean checkMedicalNumberInRange(int min, int max){
        return this.medicalRecordNumber >= min && this.medicalRecordNumber <= max;
    }
}