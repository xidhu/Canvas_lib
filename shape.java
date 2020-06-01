
import java.util.LinkedList;
class point{
    int x,y;
    point(int x,int y){
        this.x=x;
        this.y=y;
    }
    double getDistance(int x,int y){
        return Math.sqrt((Math.pow(x-this.x,2)+Math.pow(y-this.y,2)));
    }
}
class shape{
     LinkedList<point> points=new LinkedList<point>();
    int circle[];
    private FileX fil;
    private String filename;
    boolean isCircle =false;
    shape(String filename){
        this.filename=filename;
        fil=new FileX(filename);
        if(fil.LineCount()>2){
            isCircle=false;
        for(String st:fil.Lines()){
            String x[]=st.split(",");
            addPoint(Integer.parseInt(x[0].replaceAll("\\s+","")),Integer.parseInt(x[1].replaceAll("\\s+","")));
        }
    }
        else if(fil.LineCount()==2){
            this.circle=new int[3];
            String x[]=fil.getFirst().split(",");
            this.circle[0]=Integer.parseInt(x[0].replaceAll("\\s+",""));
            this.circle[1]=Integer.parseInt(x[1].replaceAll("\\s+",""));
            this.circle[2]=Integer.parseInt(fil.getLine(1));
            this.isCircle=true;
        }
    }
    boolean createCircle(int x,int y,int r){
        circle=new int[3];
        circle[0]=x;
        circle[1]=y;
        circle[2]=r;
        isCircle=true;
        return true;
    }
    boolean deleteShape(){
        points.clear();
        circle=new int[3];
        isCircle=false;
        fil.clear();
        return true;
    }
    boolean saveShape(){
        fil=new FileX(filename);
        if(points.size()>=3){
            for(point p:points){
                fil.appendLine(p.x+","+p.y);
            }
        }
        else if(circle[2]!=0){
            fil.appendLine(circle[0]+","+circle[1]);
            fil.appendLine(Integer.toString(circle[2]));
        }
        return true;
    }
    void addPoint(int x,int y){
        point n=new point(x, y);
        points.add(n);
    }
    double getPerimeter(){
        double peri=0;
        if(circle[2]!=0){
        point prevPoint=points.getLast();
        for(point p : points){
            double dist=p.getDistance(prevPoint.x, prevPoint.y);
            peri+=dist;
            prevPoint=p;
        }
    }
    else{
        peri=circle[2]*2*3.145;
    }
        return peri;
    }
    void longSide(){
        if(circle[2]!=0){
    double large=0;
    point l[]=new point[2];
    point prevPoint=points.getLast();
    for(point p : points){
        double dist=p.getDistance(prevPoint.x, prevPoint.y);
        if(dist>large){
            large=dist;
            l[0]=prevPoint;
            l[1]=p;
        }
        prevPoint=p;
    }
        System.out.format("\n points:(%d,%d) and (%d,%d) is %f",l[0].x,l[0].y,l[1].x,l[1].y,large);
    }
        }
    }
