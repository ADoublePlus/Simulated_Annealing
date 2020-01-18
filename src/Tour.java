/*
    Tour.java
        Stores a candidate tour through all cities.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    // Holds tour of cities
    private ArrayList tour = new ArrayList<City>();

    // Cache
    private int distance = 0;

    // Constructs a blank tour
    public Tour() {
        for (int i = 0; i < Tour_Manager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    // Constructs a tour from another tour
    public Tour(ArrayList tour) {
        this.tour = (ArrayList) tour.clone();
    }

    // Returns tour information
    public ArrayList getTour() {
        return tour;
    }

    // Creates a random individual
    public void generateIndividual() {
        // Loop through all destination cities and add them to the tour
        for (int cityIndex = 0; cityIndex < Tour_Manager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, Tour_Manager.getCity(cityIndex));
        }

        // Randomly re-order the tour
        Collections.shuffle(tour);
    }

    // Gets a city from the tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // Sets a city in a certain position within a tour
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);

        // If the tours have been altered, reset the fitness and distance
        distance = 0;
    }

    // Gets the total distance of the tour
    public int getDistance() {
        if (distance == 0) {
            int tourDistance = 0;

            // Loop through tour's cities
            for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
                // Get city travelled from
                City fromCity = getCity(cityIndex);

                // Get city traveling to
                City destinationCity;

                // Check if at tour's last city, if so, set the tour's final destination city to the starting city
                if (cityIndex + 1 < tourSize()) {
                    destinationCity = getCity(cityIndex + 1);
                }
                else
                {
                    destinationCity = getCity(0);
                }

                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }

            distance = tourDistance;
        }

        return distance;
    }

    // Get number of cities on tour
    public int tourSize() {
        return tour.size();
    }

    @Override
    public String toString() {
        String geneString = "|";

        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i) + "|";
        }

        return geneString;
    }
}