/**
 * Represents a single patient record.
 * This is the object stored inside each node of the PatientBST.
 * Also owns a VisitLinkedList holding that patient's past visits.
 */
public class Patient {

    private int patientId;             // unique id, used as the BST key
    private String name;               // patient's full name
    private int age;                   // patient's age
    private String contactNumber;      // patient's contact number
    private String medicalCondition;   // current medical condition / complaint
    private VisitLinkedList visitHistory; // singly linked list of this patient's past visits

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitLinkedList();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public VisitLinkedList getVisitHistory() {
        return visitHistory;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + name +
                " | Age: " + age +
                " | Contact: " + contactNumber +
                " | Condition: " + medicalCondition;
    }
}
