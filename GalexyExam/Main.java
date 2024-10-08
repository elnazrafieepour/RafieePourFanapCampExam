package GalexyExam;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Galexy> galexies= new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String dataFile = "data.txt";

    public static void main(String[] args) {
        DataManager.loadData(galexies, dataFile);
        int choice;

        do {
            System.out.println("به سیستم مدیریت کهکشان خوش آمدید!!");
            System.out.println("1. نمایش کهکشان‌ها.");
            System.out.println("2. اضافه کردن سیاره جدید");
            System.out.println("3. تغییر تعداد قمر سیاره");
            System.out.println("4. نمایش اطلاعات سیاره");
            System.out.println("5. خروج و ذخیره اطلاعات");
            System.out.print("انتخاب شما: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayGalaxies();
                    break;
                case 2:
                    addNewPlanet();
                    break;
                case 3:
                    changeMoonCount();
                    break;
                case 4:
                    displayPlanetInfo();
                    break;
                case 5:
                    DataManager.saveData(galexies, dataFile);
                    System.out.println("خداحافظ.. اطلاعات با موفقیت ذخیره شد!");
                    break;
                default:
                    System.out.println("انتخاب نامعتبر. لطفاً دوباره تلاش کنید.");
            }
        } while (choice != 5);
    }

    private static void displayGalaxies() {
        for (Galexy galaxy : galexies) {
            System.out.println("کهکشان: " + galaxy.getName());
            System.out.println("سیارات:");
            for (Planet planet : galaxy.getPlanets()) {
                System.out.println(planet);
            }
            System.out.println();
        }
    }

    private static void addNewPlanet() {
        System.out.print("نام سیاره: ");
        String name = scanner.nextLine();

        // بررسی تکراری نبودن نام سیاره
        if (findPlanetByName(name) != null) {
            System.out.println("سیاره‌ای با این نام قبلاً وجود دارد. لطفاً نام دیگری انتخاب کنید.\n");
            return;
        }

        System.out.print("نام کهکشان: ");
        String galaxyName = scanner.nextLine();

        System.out.print("نوع سیاره (سنگی/گازی): ");
        String typeInput = scanner.nextLine();

        PlanetType type;
        if (typeInput.equalsIgnoreCase("سنگی") || typeInput.equalsIgnoreCase("سنگ") || typeInput.equalsIgnoreCase("Rock")) {
            type = PlanetType.ROCKY;
        } else if (typeInput.equalsIgnoreCase("گازی") || typeInput.equalsIgnoreCase("Gas")) {
            type = PlanetType.GASEOUS;
        } else {
            System.out.println("نوع سیاره نامعتبر است. باید 'سنگی' یا 'گازی' باشد.");
            return;
        }

        System.out.print("تعداد قمرها: ");
        int moons = Integer.parseInt(scanner.nextLine());

        System.out.print("فاصله از خورشید (میلیون کیلومتر): ");
        double distance = Double.parseDouble(scanner.nextLine());

        System.out.print("آیا دارای حیات است؟ (بله/خیر): ");
        String hasLifeInput = scanner.nextLine();
        boolean hasLife = hasLifeInput.equalsIgnoreCase("بله");

        System.out.print("منابع طبیعی (در صورت وجود، با کاما جدا کنید): ");
        String resourcesInput = scanner.nextLine();
        ArrayList<String> resources = new ArrayList<>();
        if (!resourcesInput.isEmpty()) {
            String[] resourcesArray = resourcesInput.split(",");
            for (String res : resourcesArray) {
                resources.add(res.trim());
            }
        }

        Planet newPlanet;
        if (hasLife) {
            newPlanet = new LifeSupportingPlanet(name, type, moons, distance, true);
        } else if (!resources.isEmpty()) {
            newPlanet = new ResourceRichPlanet(name, true);
        } else {
            newPlanet = new Planet(name, false);
        }

        Galexy galaxy = findGalaxyByName(galaxyName);
        if (galaxy == null) {
            galaxy = new Galexy(galaxyName);
            galexies.add(galaxy);
        }

        galaxy.AddPlanet(newPlanet);
        System.out.println("سیاره جدید با نام \"" + name + "\" اضافه شد.\n");
    }

    private static void changeMoonCount() {
        System.out.print("نام سیاره: ");
        String planetName = scanner.nextLine();

        Planet planet = findPlanetByName(planetName);
        if (planet != null) {
            System.out.print("تعداد جدید قمرها: ");
            int newMoons = Integer.parseInt(scanner.nextLine());
            planet.setMoonCount(newMoons);
            System.out.println("تعداد قمرهای سیاره \"" + planetName + "\" با موفقیت تغییر کرد.\n");
        } else {
            System.out.println("سیاره‌ای با این نام یافت نشد.\n");
        }
    }

    private static void displayPlanetInfo() {
        System.out.print("نام سیاره: ");
        String planetName = scanner.nextLine();

        Planet planet = findPlanetByName(planetName);
        if (planet != null) {
            System.out.println(planet);
            if (planet instanceof LifeSupportingPlanet) {
                LifeSupportingPlanet lifePlanet = (LifeSupportingPlanet) planet;
                if (lifePlanet.hasLife()) {
                    System.out.println("این سیاره دارای حیات است.");
                } else {
                    System.out.println("این سیاره دارای حیات نیست.");
                }
            }
            if (planet instanceof ResourceRichPlanet) {
                ResourceRichPlanet resourcePlanet = (ResourceRichPlanet) planet;
                System.out.println("منابع طبیعی: " + String.join(", ", resourcePlanet.getNaturalResources()));
            }
            System.out.println();
        } else {
            System.out.println("سیاره‌ای با این نام یافت نشد.\n");
        }
    }

    private static Galexy findGalaxyByName(String name) {
        for (Galexy galaxy : galexies) {
            if (galaxy.getName().equalsIgnoreCase(name)) {
                return galaxy;
            }
        }
        return null;
    }

    private static Planet findPlanetByName(String name) {
        for (Galexy galaxy : galexies) {
            Planet planet = galaxy.getPlanetByGalexyName(name);
            if (planet != null) {
                return planet;
            }
        }
        return null;
    }
}
