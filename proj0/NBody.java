/** A class to run the simulation */
class NBody {
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


   public static void main(String[] args){

   	  double T = parseDouble(args[0]);
   	  double dt = pargseDouble(args[1]);
   	  String filename = args[2];
   	  double universe_radius = readRadius(filename);
   	  Planet[] planets = readPlanets(filename);

   }
}