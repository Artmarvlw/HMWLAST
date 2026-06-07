import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReservationService service = new ReservationService();
        Scanner scanner = new Scanner(System.in);

        // Initializing sample data according to the requirements
        service.addStudent(new Student("S001", "Anna Kowalska", "12c", 120));
        service.addStudent(new Student("S002", "Marek Nowak", "12c", 40));
        service.addStudent(new Student("S003", "Julia Zielinska", "13a", 0));

        service.addEquipment(new LaptopSet("E001", "MacBook Pro 16", 50.0, 16, true));
        service.addEquipment(new LaptopSet("E002", "Lenovo ThinkPad", 40.0, 8, false));
        service.addEquipment(new CameraKit("E003", "Sony Alpha 7 IV", 70.0, 2, true));
        service.addEquipment(new CameraKit("E004", "Canon EOS R6", 65.0, 1, false));

        System.out.println("Welcome to the MediaLab Reservation System!");

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Display the list of students");
            System.out.println("2. Display the list of equipment");
            System.out.println("3. Create a new reservation");
            System.out.println("4. Return equipment");
            System.out.println("5. Display active reservations");
            System.out.println("6. Display report and total revenue");
            System.out.println("7. Exit the program");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    service.displayStudents();
                    break;
                case "2":
                    service.displayEquipment();
                    break;
                case "3":
                    System.out.print("Enter Student ID (e.g., S001): ");
                    String sId = scanner.nextLine().trim();
                    System.out.print("Enter Equipment ID (e.g., E001): ");
                    String eId = scanner.nextLine().trim();
                    System.out.print("Enter rental duration (in days): ");
                    try {
                        int days = Integer.parseInt(scanner.nextLine().trim());
                        service.createReservation(sId, eId, days);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid number of days!");
                    }
                    break;
                case "4":
                    System.out.print("Enter Reservation ID for return (e.g., R001): ");
                    String resId = scanner.nextLine().trim();
                    service.returnEquipment(resId);
                    break;
                case "5":
                    service.displayActiveReservations();
                    break;
                case "6":
                    service.printReport();
                    break;
                case "7":
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}