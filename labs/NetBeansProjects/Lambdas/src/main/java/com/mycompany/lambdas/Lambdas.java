/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class Lambdas {

    static List<Address> addresses = new ArrayList();

    public static void main(String[] args) {

        Address one = new Address();
        one.setCity("Akron");
        one.setStreetAddress("343 Main Street");

        addresses.add(one);

        Address two = new Address();
        one.setCity("Cleveland");
        one.setStreetAddress("232 Wall Street");

        addresses.add(two);

        Address three = new Address();
        one.setCity("Pittsburgh");
        one.setStreetAddress("121 First Street");

        addresses.add(three);

        findAddressesInCityLambda("Cleveland");
    }

    List<Address> results = findAddressesInCity("Cleveland");

    public static List<Address> findAddressesInCity(String city) {

        List<Address> results = new ArrayList();

        for (Address a : addresses) {

            if (a.getCity().equals(city)) {

                results.add(a);
                continue;

            }

            if (a.getStreetAddress().contains("Wall")) {
                results.add(a);
            }
        }
        return results;
    }

    public static List<Address> findAddressesInCityLambda(String city) {

//        addresses.stream().filter((Address a) -> {
//            return a.getCity().equals(city);
//        }
//        );
//    }

        addresses
                .stream()
                .filter(a -> a.getCity().equals(city))
                .forEach(e -> System.out.println(e.getCity()));

        return addresses
                .stream()
                .filter(a -> a.getCity().equals(city))
                .collect(Collectors.toList());

    }
}
