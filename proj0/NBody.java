/** A class to run the simulation */
public class NBody {
   /** Default Constructor */
   public NBody(){};
   /** Reads and returns the radius of the universe to be simulated. */
   public static double readRadius(String file)
   {
   	  In fin = new In(file); // initializing the input stream.
   	  fin.readInt(); // ignore the first integer.
   	  return fin.readDouble();
   }

   /** Reads the data from file and returns an array of Planets.*/
   public static Planet[] readPlanets(String file)
   {
   	  In fin = new In(file);
   	  int rows = fin.readInt(); // how many planets.
   	  fin.readDouble(); // ignore the radius.

   	  Planet[] planets = new Planet[rows];

   	  for(int i = 0; i < rows; ++i)
   	  {

	  	double xPos = fin.readDouble();
	  	double yPos = fin.readDouble();
	  	double xVel	= fin.readDouble();
	  	double yVel = fin.readDouble();
	  	double mass = fin.readDouble();
	  	String img_file_name = fin.readString();
	  	planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img_file_name);
   	  }

   	  return planets;
   }

   /** Draws the given image to the canvas. */
   private static void drawBackground(String imgName){
    StdDraw.picture(0, 0, imgName);
   }

   /** Draws all of the planets */
   private static void drawPlanets(Planet[] planets){
      for(Planet planet : planets)
        planet.draw();
   }
   public static void main(String[] args){

   	  double T = Double.parseDouble(args[0]);
   	  double dt = Double.parseDouble(args[1]);
   	  String filename = args[2];
   	  double radius = readRadius(filename);
   	  Planet[] planets = readPlanets(filename);

      StdDraw.setScale(-radius, radius);
      String imgFileName = "images/starfield.jpg";
      drawBackground(imgFileName);
      StdDraw.show();

      drawPlanets(planets);
      StdDraw.show();
      StdDraw.enableDoubleBuffering();

      final int PAUSE_TIME = 10;  // pause time in miliseconds.
      for(int time = 0; time < T; time += dt)
      {
        double[] yForces = new double[planets.length];
        double[] xForces = new double[planets.length];

        for(int i = 0; i < planets.length; ++i)
        { 
          yForces[i] = planets[i].calcNetForceExertedByY(planets);
          xForces[i] = planets[i].calcNetForceExertedByX(planets);
        }

        for(int i = 0; i < planets.length; ++i)
           planets[i].update(dt, xForces[i], yForces[i]);

        drawBackground(imgFileName);
        drawPlanets(planets);
        StdDraw.show();
        StdDraw.pause(PAUSE_TIME);
      }

      // Output the data of the universe.
      StdOut.printf("%d\n", planets.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);  
      }
   
   } // end of main


}