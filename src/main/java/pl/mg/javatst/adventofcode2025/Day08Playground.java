package pl.mg.javatst.adventofcode2025;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Day08Playground {


    static void main() {
    }


    @Data
    private static class Point3D {
        double x;
        double y;
        double z;

        public double distanceSq(Point3D other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            double dz = this.z - other.z;

            // Kwadrat odległości bez użycia Math.sqrt i Math.pow
            return (dx * dx) + (dy * dy) + (dz * dz);
        }
    }
}
