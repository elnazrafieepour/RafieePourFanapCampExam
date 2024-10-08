package GalexyExam;

public class LifeSupportingPlanet extends Planet{
    private boolean hasLife; // مشخص می کند که آیا حیات بر روی سیاره وجود دارد یا خیر

    public LifeSupportingPlanet(String name, PlanetType type, int moons, double distanceFromSun, boolean hasLife) {
        super(name, type, distanceFromSun, moons); // صدا زدن سازنده کلاس پایه
        this.hasLife = hasLife;
    }

    // سازنده ساده که فقط نام و وضعیت hasLife را می‌گیرد
    public LifeSupportingPlanet(String name, boolean hasLife) {
        super(name, hasLife); // صدا زدن سازنده کلاس پایه با نام
        this.hasLife = hasLife; // تنظیم وضعیت hasLife
    }

    public boolean hasLife() {
        return hasLife;
    }

    @Override
    public String toString() {
        return super.toString() + " (Supports Life: " + (hasLife ? "Yes" : "No") + ")";
    }
}
