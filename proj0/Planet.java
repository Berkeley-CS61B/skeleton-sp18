public class Planet{

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double CONSTANT_G = 6.67e-11;


    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    
    }

    public Planet(Planet p){
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p){

        double xxDistance = p.xxPos - this.xxPos;
        double yyDistance = p.yyPos - this.yyPos;
        double rrSquare = (xxDistance * xxDistance) + (yyDistance * yyDistance);
        double rrDistance = Math.sqrt(rrSquare);

        return rrDistance;
    }

    public double calcForceExertedBy(Planet p){

        double rrDistance = calcDistance(p);
        return CONSTANT_G * this.mass * p.mass / (rrDistance * rrDistance);
    }

    public double calcForceExertedByX(Planet p){
        double F = calcForceExertedBy(p);
        double rrDistance = calcDistance(p);
        double xxDistance = p.xxPos - this.xxPos;
        return F * xxDistance / rrDistance;
    }

    public double calcForceExertedByY(Planet p){
        double F = calcForceExertedBy(p);
        double rrDistance = calcDistance(p);
        double yyDistance = p.yyPos - this.yyPos;
        return F * yyDistance / rrDistance;
    }

    public double calcNetForceExertedByX(Planet[] ps){
        double netFx = 0;
        for (Planet p : ps){
            if(p.equals(this)){
                continue;
            }
            netFx += calcForceExertedByX(p);
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Planet[] ps){
        double netFy = 0;
        for (Planet p : ps){
            if(p.equals(this)){
                continue;
            }
            netFy += calcForceExertedByY(p);
        }
        return netFy;
    }

    public void update(double dt, double fX, double fY){

        double accX = fX / this.mass;
        double accY = fY / this.mass;

        this.xxVel += accX * dt;
        this.yyVel += accY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

    }

    public void draw(){
        String imageToDraw = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);
    }
}