public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  private static final double gConstant = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  

  public double calcDistance(Planet p){
    double distance = Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    return distance;
  }


  public double calcForceExertedBy(Planet p){
    double force = gConstant * mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    return force;
  }


  
  public double calcForceExertedByX(Planet p){
    double xforce = this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
    return xforce;
  }


  
  public double calcForceExertedByY(Planet p){
    double yforce = this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
    return yforce;
  }

  public double calcNetForceExertedByX(Planet[] allPlanets){

    double Xforce = 0;

    for (Planet p : allPlanets){
      if (this.equals(p)){
        continue;
      }

      else{
        Xforce += this.calcForceExertedByX(p);

      }
    }
    return Xforce;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets){

    double Yforce = 0;

    for (Planet p : allPlanets){
      if (this.equals(p)){
        continue;
      }

      else{
        Yforce += this.calcForceExertedByY(p);

      }
    }
    return Yforce;
  }

  public void update(double dt,double fx, double fy ){
    double accelx = fx/mass;
    double accely = fy/mass;
    xxVel += accelx*dt;
    yyVel += accely*dt;
    xxPos += xxVel*dt;
    yyPos += yyVel*dt;
  }

  public void draw(){
    StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
  }
}