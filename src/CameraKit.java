public class CameraKit extends Equipment {
    private int lensCount;
    private boolean hasTripod;

    public CameraKit(String id, String name, double baseDailyPrice, int lensCount, boolean hasTripod) {
        super(id, name, baseDailyPrice);
        this.lensCount = lensCount;
        this.hasTripod = hasTripod;
    }

    @Override
    public double calculateDailyPrice() {
        double extra = (lensCount * 15.0) + (hasTripod ? 5.0 : 0.0);
        return getBaseDailyPrice() + extra;
    }

    @Override
    public String getDisplayText() {
        return super.getDisplayText() + String.format(" (Камера: Объективов: %d, Штатив: %s)", lensCount, hasTripod ? "Да" : "Нет");
    }
}