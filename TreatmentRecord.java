/**
 * Represents a completed treatment record.
 * This is the object pushed onto the TreatmentStack once
 * a patient's treatment has been finished.
 */
public class TreatmentRecord {

    private int patientId;        // id of the patient who was treated
    private String patientName;   // name of the patient who was treated
    private String treatmentGiven;// description of the treatment given
    private String completedAt;   // timestamp/date the treatment was completed

    public TreatmentRecord(int patientId, String patientName, String treatmentGiven, String completedAt) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentGiven = treatmentGiven;
        this.completedAt = completedAt;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentGiven() {
        return treatmentGiven;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    @Override
    public String toString() {
        return "Treatment for Patient ID " + patientId +
                " (" + patientName + ")" +
                " | Treatment: " + treatmentGiven +
                " | Completed: " + completedAt;
    }
}
