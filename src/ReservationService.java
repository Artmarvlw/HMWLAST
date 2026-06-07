import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Student> students = new ArrayList<>();
    private List<Equipment> inventory = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private DiscountPolicy discountPolicy = new LoyaltyDiscountPolicy();
    private int reservationCounter = 1;

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addEquipment(Equipment eq) {
        inventory.add(eq);
    }

    public void displayStudents() {
        System.out.println("\n--- Students ---");
        for (Student s : students) System.out.println(s);
    }

    public void displayEquipment() {
        System.out.println("\n--- Equipment list ---");
        for (Equipment e : inventory) System.out.println(e.getDisplayText());
    }

    public void createReservation(String studentId, String equipmentId, int days) {
        Student student = students.stream().filter(s -> s.getId().equalsIgnoreCase(studentId)).findFirst().orElse(null);
        Equipment eq = inventory.stream().filter(e -> e.getId().equalsIgnoreCase(equipmentId)).findFirst().orElse(null);

        if (student == null) {
            System.out.println("Erorr: Student with ID" + studentId + " not found!");
            return;
        }
        if (eq == null) {
            System.out.println("Error: Equipment with ID " + equipmentId + " not found!");
            return;
        }
        if (!eq.isAvailable()) {
            System.out.println("Error: Equipment [" + eq.getName() + "] is already in use!");
            return;
        }
        if (days <= 0) {
            System.out.println("Error: The number of days must be greater than 0!");
            return;
        }
        String resId = "R" + String.format("%03d", reservationCounter++);
        Reservation res = new Reservation(resId, student, eq, days, discountPolicy);
        eq.setAvailable(false);
        reservations.add(res);
        System.out.println("Success! Booking created: " + resId);
        System.out.println("Total price (including discounts): " + res.getFinalCost());
    }

    public void returnEquipment(String reservationId) {
        Reservation res = reservations.stream()
                .filter(r -> r.getId().equalsIgnoreCase(reservationId) && r.getStatus() == ReservationStatus.ACTIVE)
                .findFirst().orElse(null);
        if (res == null) {
            System.out.println("Error: Active reservation with ID " + reservationId + " not found.");
            return;
        }
        res.setStatus(ReservationStatus.RETURNED);
        res.getEquipment().setAvailable(true);
        res.getStudent().addLoyaltyPoints(15);
        System.out.println("Success! Equipment [" + res.getEquipment().getName() + "] has been successfully returned.");
        System.out.println("Student " + res.getStudent().getFullName() + " has been awarded 15 loyalty points.");
    }

    public void displayActiveReservations() {
        System.out.println("n--- Active Reservations ---");
        long count = reservations.stream().filter(r -> r.getStatus() == ReservationStatus.ACTIVE).count();
        if (count == 0) System.out.println("No active reservations.");

        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                System.out.println(r.getDisplayText());
            }
        }
    }

    public void printReport() {
        System.out.println("n================ REVENUE REPORT ================");
        double totalRevenue = 0;
        int completedCount = 0;

        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.RETURNED) {
                System.out.println(r.getDisplayText());
                totalRevenue += r.getFinalCost();
                completedCount++;
            }
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total completed rented orders: " + completedCount);
        System.out.printf("Total laboratory revenue: %.2fn", totalRevenue);
        System.out.println("=================================================");
    }
}