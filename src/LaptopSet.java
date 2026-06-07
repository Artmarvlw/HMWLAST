public class LaptopSet extends Equipment {
    private int ramGb;
    private boolean hasDockingStation;

    public LaptopSet(String id, String name, double baseDailyPrice, int ramGb, boolean hasDockingStation) {
        super(id, name, baseDailyPrice);
        this.ramGb = ramGb;
        this.hasDockingStation = hasDockingStation;
    }

    @Override
    public double calculateDailyPrice() {
        double extra = hasDockingStation ? 10.0 : 0.0;
        return getBaseDailyPrice() + extra + (ramGb * 0.5);
    }

    @Override
    public String getDisplayText() {
        return super.getDisplayText() + String.format(" (Ноутбук: %dGB RAM, Док-станция: %s)", ramGb, hasDockingStation ? "Да" : "Нет");
    }
}