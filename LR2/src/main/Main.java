package main;

import patient.Patient;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static Patient[] patients = generatePatients();

    public static void main(String[] args) {
        printPatientsWithDiagnosis();
        printPatientsWithPhoneBeginning();
        printPatientsWithMedicalRecordInRange();
    }

    public static Patient[] generatePatients(){
        Patient[] patients = new Patient[5];

        patients[0] = new Patient(1, "Стеценко", "Роман", "Валерійович" ,"Lviv", "0986029515", 312315, "Cancer");
        patients[1] = new Patient(2, "Коваленко", "Назар", "Степанович", "Kyiv", "4986029515", 4320, "Bronchit");
        patients[2] = new Patient(3, "Махно", "Олег", "Володимирович", "Sumy", "0986029515", 42330, "Cancer");
        patients[3] = new Patient(4, "Зозуля", "Віталій", "Олександрович", "Ternopil", "0986029515", 53450, "Cancer");
        patients[4] = new Patient(5, "Куцик", "Іван", "Степанович", "Kyiv", "2986029515", 23425, "Bronchit");

        return patients;
    }

    public static void printPatientsWithDiagnosis(){
        System.out.print("\nВведіть діагноз: ");
        String diagnosis = scanner.nextLine();
        System.out.println("Пацієнти з діагнозом " + diagnosis + ":");
        for (Patient patient : patients){
            if (patient.checkDiagnosis(diagnosis)) {
                System.out.println(patient);
            }
        }
    }

    public static void printPatientsWithPhoneBeginning(){
        System.out.print("\nВведіть першу цифру номеру телефона : ");
        int digit = scanner.nextInt();
        int patientsCount = 0;
        System.out.println("Пацієнти з номером, що починається на '" + digit + "' :");
        for (Patient patient : patients){
            if (patient.checkNumberBeginning(digit)) {
                System.out.println(patient);
                patientsCount++;
            }
        }
        System.out.println("Всього пацієнтів: " + patientsCount);
    }

    public static void printPatientsWithMedicalRecordInRange(){
        System.out.print("\nВведіть інтервал номерів медичних карт : ");
        int min = scanner.nextInt();
        int max = scanner.nextInt();
        System.out.println("Cписок пацієнтів, номер медичної карти у яких знаходиться в інтервалі від " + min + " до " + max + " :");
        for (Patient patient : patients){
            if (patient.checkMedicalNumberInRange(min, max)) {
                System.out.println(patient);
            }
        }
    }
}