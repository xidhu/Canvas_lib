class test{
    public static void main(String[] args) {
        shape s = new shape("rect.shape");
        s.deleteShape();
        s.addPoint(0, 0);
        s.addPoint(5, 0);
        s.addPoint(5, 5);
        s.addPoint(0, 5);
        s.saveShape();
        shape ss = new shape("circle.shape");
        ss.deleteShape();
        ss.createCircle(0, 0, 5);
        ss.saveShape();
        objectX obj = new objectX("a.objectX");
        obj.addCenter(0, 0);
        obj.addShape(s);
        obj.addNewFrame();
        obj.selectFrame(1);
        obj.addCenter(0, 0);
        obj.addShape(ss);
        obj.saveFrame();
        obj.saveObject("c.objectX");
        canvas c = new canvas(100, 30,'x');
        c.addObject(obj,"hello");
        c.drawObject("hello",1, 5, 5, '*');
        c.display();


    }
}