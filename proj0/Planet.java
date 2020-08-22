class Planet {
	static final double G = 6.67e-11;  // The Gravitational constant.

	public double xxPos,  // Its current x position
		   		  yyPos,  // Its current y position
		          xxVel,  // Its current velocity in x direction.
		          yyVel,  // Its current velocity in y direction.
		          mass;   // Its mass.

	public String imgFileName;  // The name of the image file for this planet.

	/** Constructor */
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img)
	{
		xxPos = xP; 
		yyPos = yP;
		xxVel = xV; 
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p)
	{
		xxPos = p.xxPos; 
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculates the distance between the two Planets. */
	public double calcDistance(Planet p) 
	{
		double r_squared = Math.pow(xxPos - p.xxPos, 2) + Math.pow(yyPos - p.yyPos, 2);
		return Math.sqrt(r_squared);
	} 

	/** Calculates the Force exerted on 'this' Planet by the given Planet p. */
	public double calcForceExertedBy(Planet p)
	{
		double distance_squared = this.calcDistance(p) * this.calcDistance(p);
		return (G * mass * p.mass) / distance_squared;
	}

	/** Calculates the X component of calcForceExertedBy() method. */
	public double calcForceExertedByX(Planet p)
	{
		double dx = p.xxPos - xxPos;
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		return (F * dx) / r;
	}

	/** Calculates the Y component of calcForceExertedBy() method. */
	public double calcForceExertedByY(Planet p)
	{
		double F = calcForceExertedBy(p);
		double Fx = calcForceExertedByX(p);
		return Math.sqrt(F * F - Fx * Fx);
	}

	/** Calculates the NET X component of the Force exerted by each planet on 'this' planet. */
	public double calcNetForceExertedByX(Planet[] all_planets)
	{
		double F_netX = 0;
		for(Planet p : all_planets)
		{
			if(! p.equals(this))
				F_netX += calcForceExertedByX(p);
		}

		return F_netX;
	}


	/** Calculates the NET Y component of the Force exerted by each planet on 'this' planet. */
	public double calcNetForceExertedByY(Planet[] all_planets)
	{
		double F_netY = 0;
		for(Planet p : all_planets)
		{
			if(! p.equals(this))
				F_netY += calcForceExertedByY(p);
		}
		return F_netY;
	}

	/** 
	Updates the position and velocity of 'this' planet. 
	    dt: delta time (in seconds)
	    fX: the X component of the exerted force.
	    fY: the Y component of the exerted force.
	*/
	public void update(double dt, double fX, double fY)
	{
		double accX = fX / mass; // acceleration in the x direction.
		double accY = fY / mass; // acceleration in the y direction.

		xxVel += accX * dt;  // updating the
		yyVel += accY * dt;  // velocities.

		xxPos += xxVel * dt; // updating the
		yyPos += yyVel * dt; // position.
	}

	/** Draws the Planet's image at its position. */
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}