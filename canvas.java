
class canvas{
    private int h = 240,w = 320;
    private char border_char='*';
    private char canv[][];
    canvas(int x,int y,char border_char){
            this.h=y+2; this.w=x+2;
            this.border_char=border_char;
        this.canv=new char[this.h][this.w];
            for(int i = 0;i < this.h;i++){
                for(int j = 0;j < this.w;j++){
                    if((i == 0||i == this.h-1||j == 0||j == this.w-1))
                        this.canv[i][j] = this.border_char;
                    else
                        this.canv[i][j] = ' ';  
            }
        }
    }
    void display(){
        for (int i=h-1;i>=0;i--) {
            System.out.println(String.valueOf(canv[i]));
        }
    }
    void clear(){
        for(int i = 0;i < this.h;i++){
            for(int j = 0;j < this.w;j++){
                if((i == 0||i == this.h-1||j == 0||j == this.w-1))
                    this.canv[i][j] = this.border_char;
                else
                    this.canv[i][j] = ' ';      
            }
        }
    }
    boolean addPoint(int x,int y,char p){
        try{
                if(x!=w-2&&y!=h-2)
                    canv[y+1][x+1]=p;
                return true;
        }
        catch (Exception e){
            return false;
        }
    }

    boolean delPoint(int x,int y){
        try{
            if(x!=w-2&&y!=h-2&&x!=-1&&y!=-1){
                canv[y+1][x+1]=' ';
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    boolean write(String s,int x,int y){
        try{
            if(s.length()<((w-1)-x+1)&&y!=-1&&y!=h-2){
                for(int i=x+1;i<x+s.length();i++)
                    canv[y+1][i]=s.charAt(i-x-1);
            }
            return true;
        }
            catch (Exception e){
                return false;
            }
    }
    boolean insideCanvas(int x,int y){
        if(-1<x&&x<w-1&&-1<y&&y<h-1)
            return true;
        else
            return false;
    }
    boolean drawObject(objectX o,int x,int y,char p){
        boolean ok=true;
        for(point i:o.points){
            ok=insideCanvas(i.x+x, i.y+y);
        }
        if(ok){
            for(int i=1;i<o.points.size();i++){
                addPoint(o.points.get(i).x+x-o.points.getFirst().x, o.points.get(i).y+y-o.points.getFirst().y, p);
            }

        }
        return true;
    }
}