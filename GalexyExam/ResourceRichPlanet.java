package GalexyExam;

import java.util.ArrayList;
import java.util.List;

// کلاس ResourceRichPlanet
class ResourceRichPlanet extends Planet {
    private List<String> naturalResources;
    private boolean hasResources;  // ویژگی برای پشتیبانی از منابع طبیعی


    // سازنده برای ResourceRichPlanet  
    public ResourceRichPlanet(String name, boolean hasResources) {
        super(name); // صدا زدن سازنده کلاس پایه  
        this.naturalResources = new ArrayList<>(); // فهرست منابع جدید ایجاد می‌شود
        this.hasResources = hasResources;  // تنظیم وضعیت پشتیبانی

    }

    // متد برای افزودن منبع طبیعی  
    public void addResource(String resource) {
        if (hasResources) {
            naturalResources.add(resource);
        } else {
            System.out.println(getName() + " does not support natural resources.");
        }
    }

    // متد برای نمایش منابع طبیعی  
    public List<String> getNaturalResources() {
        return naturalResources;
    }

    // متد برای بررسی پشتیبانی از منابع طبیعی
    public boolean hasResources() {
        return hasResources;
    }

    @Override
    public String toString() {
        return super.toString() +
                " (Supports Natural Resources: " + hasResources +
                ", Natural Resources: " + getNaturalResourcesString() + ")";
    }

    // متد کمکی برای تبدیل لیست منابع به رشته  
    private String getNaturalResourcesString() {
        return String.join(", ", naturalResources.isEmpty() ? List.of("None") : naturalResources);
    }


}  