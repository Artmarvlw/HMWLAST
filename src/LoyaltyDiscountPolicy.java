public class LoyaltyDiscountPolicy implements DiscountPolicy {
    @Override
    public double applyDiscount(Student student, double price) {
        if (student.getLoyaltyPoints() > 100) {
            return price * 0.85; // 15% скидка
        } else if (student.getLoyaltyPoints() > 50) {
            return price * 0.90; // 10% скидка
        }
        return price;
    }
}