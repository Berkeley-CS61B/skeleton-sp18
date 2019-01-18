import java.lang.reflect.Array;

public class NBody{

    public static void main(String[] args){

        // Drawing the Initial Universe State
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] ps = readPlanets(filename);
        double universeRadius = readRadius(filename);

        String imageToDraw = "./images/starfield.jpg";

        StdDraw.setScale(-universeRadius, universeRadius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);

        for(Planet p:ps){
            p.draw();
        }
        StdDraw.show();

        // Create an animation
        StdDraw.enableDoubleBuffering();
        double initT = 0;
        while(initT < T){
            Double[] xForces = new Double[ps.length];
            Double[] yForces = new Double[ps.length];
            for(int i = 0;i < ps.length; i++){
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);              
            }
            for(int i = 0; i < ps.length; i++){
                ps[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
    
            for(Planet p:ps){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            initT += dt;
        };

        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
        }
    }

    public static double readRadius(String file){

        In in = new In(file);
        int numberOfPlanets = in.readInt();
        double readRadius = in.readDouble();

        return readRadius;
    }

    public static Planet[] readPlanets(String file){

        In in = new In(file);
        int numberOfPlanets = in.readInt();
        double readRadius = in.readDouble();
        Planet[] planets = new Planet[numberOfPlanets];
        int counter = 0;

        while(counter < numberOfPlanets){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            planets[counter] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
            counter++;
        }
        return planets;
    }


}