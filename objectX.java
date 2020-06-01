import java.util.LinkedList;
public class objectX {
    private FileX fil;
    private String filename;
    LinkedList<point> points = new LinkedList<point>();
    objectX(String filename){
        this.filename=filename;
        fil=new FileX(this.filename);
        if(fil.LineCount()>0){
        for(String st:fil.Lines()){
            String x[]=st.split(",");
            this.addPoint(Integer.parseInt(x[0].replaceAll("\\s+","")),Integer.parseInt(x[1].replaceAll("\\s+","")));
            }
        }
    }
    boolean ConnectPoints(int X0,int Y0,int X1,int Y1){
        try{
            int dx = X1 - X0; 
            int dy = Y1 - Y0; 
            int steps = Math.abs(dx) > Math.abs(dy) ? Math.abs(dx) : Math.abs(dy); 
            float Xinc = dx / (float) steps; 
            float Yinc = dy / (float) steps; 
            float X = X0; 
            float Y = Y0; 
            for (int i = 0; i <= steps; i++) 
            { 
                addPoint((int)X,(int)Y); 
                X += Xinc;           
                Y += Yinc;            
                                     
            } 
            return true;
    }
            catch(Exception e){
                return false;
            }
    }
    void saveObject(){
        fil=new FileX(filename);
        fil.clear();
            for(point p:points){
                fil.appendLine(p.x+","+p.y);
            }
    }
    void addPoint(int x,int y){
        points.add(new point(x, y));
    }
    void addCenter(int x,int y){
        addPoint(x, y);
    }
    boolean addShape(shape s){
        point f;
        if(s.isCircle){
            f=new point(s.circle[0], s.circle[1]);
            drawcircle(f.x, f.y,s.circle[2]);
        }
        else{
            f=s.points.getLast();
            for(point p : s.points){
                    ConnectPoints(p.x, p.y, f.x, f.y);
                    f=p;
                }
            }
        
        return true;
    }
    void deleteObject(){
        points.clear();
    }
    void drawcircle(int x0, int y0, int radius)
{
    int x = radius;
    int y = 0;
    int err = 0;
 
    while (x >= y)
    {
	addPoint(x0 + x, y0 + y);
	addPoint(x0 + y, y0 + x);
	addPoint(x0 - y, y0 + x);
	addPoint(x0 - x, y0 + y);
	addPoint(x0 - x, y0 - y);
	addPoint(x0 - y, y0 - x);
	addPoint(x0 + y, y0 - x);
	addPoint(x0 + x, y0 - y);
 
	if (err <= 0)
	{
	    y += 1;
	    err += 2*y + 1;
	}
 
	if (err > 0)
	{
	    x -= 1;
	    err -= 2*x + 1;
	}
    }
}
}