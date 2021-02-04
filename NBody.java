public class NBody{

	public static double readRadius(String path){
    In in = new In(path);
    int number = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  	public static Planet [] readPlanets(String path){

  		In in = new In(path);
    	int number = in.readInt();
    	double radius = in.readDouble();

    	Planet [] planets = new Planet[number];
    	int i = 0;

    	while (i<number){
    		double xxPos = in.readDouble();
    		double yyPos = in.readDouble();
    		double xxVel = in.readDouble();
    		double yyVel = in.readDouble();
    		double mass = in.readDouble();
    		String img = in.readString();
    		planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
    		i += 1;
    	}
    	return planets;
  	}

  	public static void main(String[] args) {

  		double T = Double.valueOf(args[0]);
  		double dt = Double.valueOf(args[1]);
  		String filename = args[2];

  		double radius = readRadius(filename);
  		Planet[] planets = readPlanets(filename);

  		StdDraw.setScale(-radius,radius);
  		StdDraw.enableDoubleBuffering();

  		
  		
  		int time = 0;
  		

  		while (time <T){
  			double [] xforces = new double [planets.length];
  			double [] yforces = new double [planets.length];
  			
  			int i = 0;
  			for(i=0;i<planets.length;i++){
  				xforces[i] = planets[i].calcNetForceExertedByX(planets);
  				yforces[i] = planets[i].calcNetForceExertedByY(planets);
  				
  				
  			}
  			for(i=0;i<planets.length;i++){
  				planets[i].update(dt,xforces[i],yforces[i]);
  			}
  			StdDraw.picture(0,0,"images/starfield.jpg");
  			for (Planet p:planets){
  			p.draw();
  			}
  			StdDraw.show();
  			StdDraw.pause(10);
  			time+=10;
  			
  		}

  		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}

  		
  	}
}