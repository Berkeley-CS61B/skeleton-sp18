public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public static double G = 6.67e-11;
    String imgFileName; 

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    public Planet(Planet P) {
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P) {
        double dx = P.xxPos - this.xxPos;
        double dy = P.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet P) {
        double r = this.calcDistance(P);
        double F = Planet.G * this.mass * P.mass / (r * r);
        return F;     
    }

    public double calcForceExertedByX(Planet P) {
        double dx = P.xxPos - this.xxPos;
        double r = this.calcDistance(P);
        double F = this.calcForceExertedBy(P);
        return F * dx / r;
    }

    public double calcForceExertedByY(Planet P) {
        double dy = P.yyPos - this.yyPos;
        double r = this.calcDistance(P);
        double F = this.calcForceExertedBy(P);
        return F * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double Fx = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i]))
                Fx += this.calcForceExertedByX(allPlanets[i]);
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double Fy = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i]))
                Fy += this.calcForceExertedByY(allPlanets[i]);
        }
        return Fy;
    }

    public void update(double dt, double Fx, double Fy) {
        double xA = Fx / this.mass;
        double yA = Fy / this.mass;
        double xV = this.xxVel + xA * dt;
        double yV = this.yyVel + yA * dt;
        double xP = this.xxPos + xV * dt;
        double yP = this.yyPos + yV * dt;
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
    }

    public void draw() { 
        StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
    }
}