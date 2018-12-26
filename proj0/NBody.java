public class NBody {
    public static String background = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numPlanets = in.readInt();
        Planet[] allPlanets = new Planet[numPlanets]; 
        double radius = in.readDouble();
        for (int i = 0; i < numPlanets; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String file = in.readString();
            allPlanets[i] = new Planet(xP, yP, xV, yV, m, file);
        }
        return allPlanets;
    }

    public static void drawBackground(double r) {
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(-r, r, background);
        StdDraw.show();
        StdDraw.pause(2000);
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double time = 0;
        String fileName = args[2];
        StdDraw.enableDoubleBuffering();

        double r = readRadius(fileName);
        Planet[] allPlanets = readPlanets(fileName);
        double[] xForces = new double[allPlanets.length];
        double[] yForces = new double[allPlanets.length];
        for (time = 0.0; time < T; time += dt){
            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            drawBackground(r);
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(1000);


            
        }



       
    }
}