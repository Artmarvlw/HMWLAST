public class Reservation implements Displayable {
    private String id;
    private Student student;
    private Equipment equipment;
    private int days;
    private ReservationStatus status;
    private double finalCost;

    public Reservation(String id, Student student, Equipment equipment, int days, DiscountPolicy discountPolicy) {
        this.id = id;
        this.student = student;
        this.equipment = equipment;
        this.days = days;
        this.status = ReservationStatus.ACTIVE;
        double fullPrice = equipment.calculateDailyPrice() * days;
        this.finalCost = discountPolicy.applyDiscount(student, fullPrice);
    }

    public String getId() { return id; }
    public Equipment getEquipment() { return equipment; }
    public Student getStudent() { return student; }
    public ReservationStatus getStatus() { return status; }
    public double getFinalCost() { return finalCost; }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String getDisplayText() {
        return String.format("Бронь %s | Студент: %s | Оборудование: %s | Дней: %d | Статус: %s | Стоимость: %.2f",
                id, student.getFullName(), equipment.getName(), days, status, finalCost);
    }
}