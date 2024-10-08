package GalexyExam;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    public static void loadData(ArrayList<Galexy> galaxies, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return;
            }
            Scanner fileScanner = new Scanner(file, "UTF-8");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 6) {
                    System.out.println("فرمت نادرست در خط: " + line);
                    continue;
                }

                String namePart = parts[0].trim();
                String name = namePart.contains(":") ? namePart.split(":")[1].trim() : namePart;

                String galaxyName = parts[1].trim();

                String typeString = parts[2].trim();
                PlanetType type;
                if (typeString.equalsIgnoreCase("سنگی") || typeString.equalsIgnoreCase("سنگ") || typeString.equalsIgnoreCase("Rock")) {
                    type = PlanetType.ROCKY;
                } else if (typeString.equalsIgnoreCase("گازی") || typeString.equalsIgnoreCase("Gas")) {
                    type = PlanetType.GASEOUS;
                } else {
                    System.out.println("نوع سیاره نامعتبر در فایل داده‌ها: " + typeString);
                    continue;
                }

                int moons = Integer.parseInt(parts[3].trim());
                double distance = Double.parseDouble(parts[4].trim());

                String lifeStatus = parts[5].trim();
                boolean hasLife = lifeStatus.equalsIgnoreCase("دارد") || lifeStatus.equalsIgnoreCase("has life");

                ArrayList<String> resources = new ArrayList<>();
                if (parts.length > 6) {
                    String resourcesInput = parts[6].trim();
                    if (!resourcesInput.equalsIgnoreCase("nothing") && !resourcesInput.equalsIgnoreCase("ندارد")) {
                        String[] resourcesArray = resourcesInput.split("،|,");
                        for (String res : resourcesArray) {
                            resources.add(res.trim());
                        }
                    }
                }

                Planet planet;
                if (hasLife) {
                    planet = new LifeSupportingPlanet(name, type, moons, distance, true);
                } else if (!resources.isEmpty()) {
                    planet = new ResourceRichPlanet(name, true);
                } else {
                    planet = new Planet(name, false);
                }

                Galexy galaxy = findGalaxyByName(galaxies, galaxyName);
                if (galaxy == null) {
                    galaxy = new Galexy(galaxyName);
                    galaxies.add(galaxy);
                }
                galaxy.AddPlanet(planet);
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("خطا در خواندن فایل داده‌ها: " + e.getMessage());
        }
    }

    public static void saveData(ArrayList<Galexy> galaxies, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));

            for (Galexy galaxy : galaxies) {
                for (Planet planet : galaxy.getPlanets()) {
                    StringBuilder line = new StringBuilder();
                    line.append("سیاره: ").append(planet.getName()).append(", ");
                    line.append(galaxy.getName()).append(", ");
                    line.append(planet.getType().toString()).append(", ");
                    line.append(planet.getMoonCount()).append(", ");
                    line.append(planet.getDistanceFromSun()).append(", ");

                    if (planet instanceof LifeSupportingPlanet) {
                        LifeSupportingPlanet lifePlanet = (LifeSupportingPlanet) planet;
                        line.append(lifePlanet.hasLife() ? "دارد" : "ندارد");
                    } else {
                        line.append("ندارد");
                    }

                    if (planet instanceof ResourceRichPlanet) {
                        ResourceRichPlanet resourcePlanet = (ResourceRichPlanet) planet;
                        if (!resourcePlanet.getNaturalResources().isEmpty()) {
                            line.append(", ").append(String.join(", ", resourcePlanet.getNaturalResources()));
                        } else {
                            line.append(", ندارد");
                        }
                    } else {
                        line.append(", ندارد");
                    }

                    writer.write(line.toString());
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("خطا در ذخیره‌سازی فایل داده‌ها: " + e.getMessage());
        }
    }

    private static Galexy findGalaxyByName(ArrayList<Galexy> galaxies, String name) {
        for (Galexy galaxy : galaxies) {
            if (galaxy.getName().equalsIgnoreCase(name)) {
                return galaxy;
            }
        }
        return null;
    }
}
