package com.multitrans.wasalliya.optimizer;

import com.multitrans.wasalliya.model.Delivery;
import com.multitrans.wasalliya.model.Vehicale;
import com.multitrans.wasalliya.model.Warehouse;
import com.multitrans.wasalliya.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClarkeWright implements TourOptimizer{
    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> pendingDeliveries, Vehicale vehicle) {
        List<Saving> savingsList = new ArrayList<>();

        double whLat = warehouse.getLatitude();
        double whLon = warehouse.getLongitude();

        for (int i = 0; i < pendingDeliveries.size(); i++) {
            Delivery deliveryA = pendingDeliveries.get(i);

            for (int j = i + 1; j < pendingDeliveries.size(); j++) {
                Delivery deliveryB = pendingDeliveries.get(j);

                double dist_WA = DistanceCalculator.calculateDistance(whLat, whLon, deliveryA.getLatitude(), deliveryA.getLongitude());
                double dist_WB = DistanceCalculator.calculateDistance(whLat, whLon, deliveryB.getLatitude(), deliveryB.getLongitude());
                double dist_AB = DistanceCalculator.calculateDistance(deliveryA.getLatitude(), deliveryA.getLongitude(), deliveryB.getLatitude(), deliveryB.getLongitude());

                double savingAmount = (dist_WA + dist_WB) - dist_AB;

                if (savingAmount > 0) { // (منطقي أننا نخدمو غير بالوفورات الموجبة)
                    savingsList.add(new Saving(deliveryA, deliveryB, savingAmount));
                }
            }
        }

        Collections.sort(savingsList);
        System.out.println("--- Savings List (Sorted) ---");
        for (Saving s : savingsList) {
            System.out.printf("Saving ( %d, %d ) = %.2f km\n",
                    s.deliveryA().getId(), s.deliveryB().getId(), s.savingAmount());
        }

        return new ArrayList<>();
    }
}
