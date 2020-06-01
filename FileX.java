import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
class FileX{
    private String filename;
    private File fil;
    private BufferedReader br;
    private FileWriter bw;
    private LinkedList<String> file_lines=new LinkedList<String>();
    private int length=0;
    private int currLine=0;
    //****************************************File Initialisation.**********************************
    FileX(String filename){
        this.filename=filename;
        fil=new File(filename);
        try{
            br=new BufferedReader(new FileReader(fil));
            String st;
        try{
            while ((st = br.readLine()) != null) {
                file_lines.add(st);
                length++;
            }
            br.close();
        }
        catch(Exception e){}
        }
        catch(Exception e){
            try{
            fil.createNewFile();
            }
            catch(Exception q){
                System.out.println("Error...!");
            }
        }
         
    }
    //********************************************General************************************ */
    int LineCount(){
        return length;
    }

    String getFileName(){
        return filename;
    }

    //****************************************File Reading.**********************************

    String getLine(int index){
        if(0<=index && index<length)
            return file_lines.get(index);    
        else    
            return null; 
    }


    String getCurrLine(){
        return file_lines.get(currLine);
    }


    boolean hasNextLine(){
        if(currLine<length)
            return true;
        else
            return false;
    }


    String getLine(){
            if(currLine<length){
                return file_lines.get(currLine++);
            }
            else
                return null;
    }


    void rewind(){
        currLine=0;
    }


    void seek(int index){
        if(0<=index&&index<length){
            currLine=index;
        }
    }


    String getFirst(){
        return file_lines.getFirst();
    }


    String getlast(){
        return file_lines.getLast();
    }


    String[] getLines(int startIndex,int endIndex){
        if((0<=startIndex && startIndex<length-1)&&(endIndex<length-1)&&(startIndex<endIndex)){
            String[] x=new String[endIndex-startIndex+1];
            int j=0;
            for(int i=startIndex;i<=endIndex;i++){
                x[j]=file_lines.get(i);
                j++;
            }
            return x;
        }
        else
            return null;
    }


    Iterable<String> Lines(){
            return file_lines;
    }


    

    //****************************************File Writing.**********************************
    void update(){
        String s="";
        try{
        bw=new FileWriter(fil);
        for(String i:this.Lines())
            s+=i+"\n";
        bw.write(s);
        bw.close();
        file_lines.clear();
        length=0;
        try{
            br=new BufferedReader(new FileReader(fil));
            String st;
        try{
            while ((st = br.readLine()) != null) {
                file_lines.add(st);
                length++;
            }
            br.close();
        }
        catch(Exception e){}
        
        
        }catch(Exception r){}
    }
        catch(Exception e){}
    }


    boolean appendLine(String line){
        file_lines.add(line);
        update();
        return true;
    }

    boolean clear(){
        file_lines.clear();
        update();
        return true;
    }

    boolean writeAtLine(int index,String line){
        if(0<=index&&index<length){
            file_lines.add(index,line);
            update();
            return true;
        }
        else
            return false;
    }

    boolean writeAtLine(String line){
        file_lines.add(currLine++,line);
        update();
        return true;
    }

    boolean writeAfterLine(int OccurrantPosition_0_start_1_middle_2_end,int Occurance,String regrex,String line){
        int occ=0;
        String s=regrex;
        for(String i:file_lines){
            if((OccurrantPosition_0_start_1_middle_2_end==0)?i.startsWith(s):(OccurrantPosition_0_start_1_middle_2_end==2)?i.endsWith(s):(OccurrantPosition_0_start_1_middle_2_end==1)?i.contains(s.subSequence(0,s.length()-1))&&!i.startsWith(s)&&!i.endsWith(s):i.startsWith(s)){
                occ++;
                if(occ!=Occurance){
                    continue;
                }
                if(file_lines.indexOf(i)==file_lines.size()-1){
                    file_lines.add(line);
                    break;
                }
                else{
                    file_lines.add(file_lines.indexOf(i)+1,line);
                    break;
            }
        }  
        }
        update();
        return true;
    }
}
    
