import java.util.Scanner;

/**
 * Main entry point for the Mini Hospital Emergency Management System.
 * Provides an interactive console menu that uses all four required
 * data structures together:
 *   - PatientBST      (patient records)
 *   - EmergencyQueue  (waiting patients)
 *   - TreatmentStack  (completed treatments)
 *   - VisitLinkedList (per-patient visit history)
 *
 * Run "Demo.java" instead of this file if you just want a scripted,
 * non-interactive walkthrough for a screen recording.
 */
public class Main {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("====================================================");
        System.out.println(" Mini Hospital Emergency Management System - CIT300");
        System.out.println("====================================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1: registerPatient(); break;
                case 2: searchPatient(); break;
                case 3: deletePatient(); break;
                case 4: patientBST.displayInOrder(); break;
                case 5: enqueuePatient(); break;
                case 6: dequeuePatient(); break;
                case 7: emergencyQueue.displayQueue(); break;
                case 8: completeTreatment(); break;
                case 9: undoLastTreatment(); break;
                case 10: treatmentStack.displayStack(); break;
                case 11: addVisit(); break;
                case 12: removeVisit(); break;
                case 13: searchVisit(); break;
                case 14: displayVisitHistory(); break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println(" Patient Records (BST)");
        System.out.println("  1. Register new patient");
        System.out.println("  2. Search patient by ID");
        System.out.println("  3. Delete patient by ID");
        System.out.println("  4. Display all patients (in-order)");
        System.out.println(" Emergency Queue");
        System.out.println("  5. Enqueue patient to waiting queue");
        System.out.println("  6. Dequeue next patient for treatment");
        System.out.println("  7. Display waiting queue");
        System.out.println(" Treatment History (Stack)");
        System.out.println("  8. Complete treatment (push record)");
        System.out.println("  9. Undo last completed treatment (pop record)");
        System.out.println("  10. Display treatment history");
        System.out.println(" Patient Visit History (Linked List)");
        System.out.println("  11. Add visit to a patient's history");
        System.out.println("  12. Remove a visit from a patient's history");
        System.out.println("  13. Search a visit in a patient's history");
        System.out.println("  14. Display a patient's visit history");
        System.out.println("  0. Exit");
        System.out.println("--------------------------------------------");
    }

    // ---------- BST operations ----------

    private static void registerPatient() {
        int id = readInt("Patient ID: ");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age = readInt("Age: ");
        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient found = patientBST.search(id);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("No patient found with ID " + id);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        System.out.println(deleted ? "Patient deleted." : "No patient found with ID " + id);
    }

    // ---------- Queue operations ----------

    private static void enqueuePatient() {
        int id = readInt("Enter Patient ID to add to waiting queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found. Register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void dequeuePatient() {
        Patient next = emergencyQueue.dequeue();
        if (next != null) {
            System.out.println("Now treating: " + next);
        }
    }

    // ---------- Stack operations ----------

    private static void completeTreatment() {
        int id = readInt("Enter Patient ID whose treatment is complete: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.print("Treatment given: ");
        String treatment = scanner.nextLine();
        System.out.print("Completed date/time (e.g. 2026-09-07 14:30): ");
        String completedAt = scanner.nextLine();

        TreatmentRecord record = new TreatmentRecord(id, patient.getName(), treatment, completedAt);
        treatmentStack.push(record);
    }

    private static void undoLastTreatment() {
        TreatmentRecord popped = treatmentStack.pop();
        if (popped != null) {
            System.out.println("Removed most recent record: " + popped);
        }
    }

    // ---------- Linked List operations ----------

    private static void addVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        int visitId = readInt("Visit ID: ");
        System.out.print("Visit Date: ");
        String date = scanner.nextLine();
        System.out.print("Doctor Name: ");
        String doctor = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to patient's history.");
    }

    private static void removeVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed." : "Visit not found.");
    }

    private static void searchVisit() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit found = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(found != null ? "Found: " + found : "Visit not found.");
    }

    private static void displayVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        System.out.println("Visit history for " + patient.getName() + ":");
        patient.getVisitHistory().displayVisits();
    }

    // ---------- Input helper ----------

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}
