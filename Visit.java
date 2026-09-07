/**
 * Represents a single past hospital visit for a patient.
 * This object is what gets stored inside each node of a
 * patient's VisitLinkedList (Singly Linked List).
 */
public class Visit {

    private int visitId;         // unique id for this visit
    private String visitDate;    // date of the visit, e.g. "2026-01-15"
    private String doctorName;   // doctor who attended the visit
    private String diagnosis;    // diagnosis given during the visit
    private String treatment;    // treatment given during the visit

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getVisitId() {
        return visitId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return "Visit #" + visitId +
                " | Date: " + visitDate +
                " | Doctor: " + doctorName +
                " | Diagnosis: " + diagnosis +
                " | Treatment: " + treatment;
    }
}
