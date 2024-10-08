package GalexyExam;

import java.util.HashSet;
import java.util.Set;

// کلاس برای مدل کردن سیاره
class Planet {
    private String name;         // نام سیاره
    private PlanetType type;     // نوع سیاره  
    //private int moons;           // تعداد قمرها
    private double distanceFromSun; // فاصله از خورشید
    private static Set<String> planetNames = new HashSet<>(); // مجموعه نام‌های سیاره جهت بررسی یونیک بودن
    private Set<String> moons; // مجموعه قمرها

    private int moonsCount;
    private boolean hasLife; // مشخصه برای بررسی وجود حیات
    private Set<String> resources; // مجموعه منابع طبیعی سیاره



    public Planet(String name) {
        if (planetNames.contains(name)) {
            throw new IllegalArgumentException("The planet name " + name + " is already taken.");
        }
        planetNames.add(name); // افزودن نام سیاره به مجموعه
        this.name = name;
        this.type = type;
        this.moons = new HashSet<>(); // فهرست قمرها ایجاد می‌شود
        this.distanceFromSun = distanceFromSun;
        this.hasLife = hasLife; // مقداردهی مشخصه وجود حیات
        this.resources = new HashSet<>(); // فهرست منابع طبیعی ایجاد می‌شود


    }
    public Planet(String name, boolean hasLife) {
        if (planetNames.contains(name)) {
            throw new IllegalArgumentException("The planet name " + name + " is already taken.");
        }
        this.name = name;
        planetNames.add(name); // افزودن نام سیاره به مجموعه
        this.moons = new HashSet<>(); // فهرست قمرها ایجاد می‌شود
        this.hasLife = hasLife; // مقداردهی مشخصه وجود حیات
        this.resources = new HashSet<>(); // فهرست منابع طبیعی ایجاد می‌شود




    }

    public String getName() {
        return name;
    }

    public PlanetType getType() {
        return type;
    }


    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    // متد برای افزودن قمر به سیاره
    public void addMoon(String moonName) {
        moons.add(moonName); // افزودن یک قمر جدید به فهرست
    }

    // متد برای بازگرداندن تعداد قمرها
    public int getMoonCount() {
        return moons.size(); // برگرداندن تعداد قمرها
    }
    // متد برای تعیین تعداد قمرها
    public void setMoonCount(int CountMoon) {
        this.moonsCount = CountMoon;
    }

    // متد برای نمایش قمرها
    public Set<String> getMoons() {
        return moons;
    }

    // متد برای بررسی وضعیت حیات
    public String checkLifeStatus() {
        if (hasLife) {
            return name + " has life.";
        } else {
            return name + " does not have life.";
        }
    }

    @Override
    public String toString() {
        return name + " (" + type + ", Moons: " + moons + ", Distance from Sun: " + distanceFromSun + " AU)";
    }

    // متد کمکی برای تبدیل لیست قمرها به رشته
    private String getMoonsString() {
        return String.join(", ", moons.isEmpty() ? Set.of("None") : moons);
    }


}