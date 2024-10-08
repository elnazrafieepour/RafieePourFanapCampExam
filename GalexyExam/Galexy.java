package GalexyExam;

import java.util.ArrayList;
import java.util.List;

public class Galexy {
    private String Name;
    private List<Planet> Planets= new ArrayList<>();

    //Constructors:

    public Galexy(String name) {
        this.Name = name;
        this.Planets = new ArrayList<>();
    }

    //getter and setter:

    public String getName() {
        return Name;
    }

    public List<Planet> getPlanets() {
        return Planets;
    }

    public List<Planet> AddPlanet(Planet planet){
        Planets.add(planet);
        return Planets;
    }
    // متد برای آپدیت سیاره
    public void updatePlanet(Planet oldPlanet, Planet newPlanet) {
        int index = Planets.indexOf(oldPlanet);
        if (index != -1) { // اگر سیاره قدیمی در لیست موجود باشد
            Planets.set(index, newPlanet);
        } else {
            System.out.println("Planet " + oldPlanet.getName() + " not found in the galaxy.");
        }
    }

    // متد برای حذف سیاره از کهکشان
    public void removePlanet(Planet planet) {
        Planets.remove(planet);
    }

    // متد برای نمایش اطلاعات کهکشان و سیارات آن
    public void displayInfo() {
        System.out.println("Galaxy: " + Name);
        System.out.println("Planets:");
        for (Planet planet : Planets) {
            System.out.println(" - " + planet);
        }
    }

    @Override
    public String toString() {
        return "Galexy Name: " + this.Name + ", ListofPlanet is: " + this.Planets;
    }
}
