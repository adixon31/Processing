import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Paint_the_Night_with_Fireflies extends PApplet {

Firefly fly;
Firefly glide;
Firefly p;
ArrayList flies;
ArrayList stream;
ArrayList paint;
ArrayList flyjar;
ArrayList stars;
int pos = 340, pos0 = 200, pos1 = 51, pos2 = 153, pos3 = 204, num = 1, num2 = 106, num3 = 0, num4 = 0;
int c = color(204,153,0);

class Firefly{
  float a, b, f = random(266), randy, randy2, randy3, randy4, inner = 10.0f, outer = 16.0f, center = 2.0f;
  int star;
  int o;
  int t = 20;
  float stayX, stayY;
  Firefly(float c, float d, int e, float h){
    a = c;
    b = d;
    stayX = c;
    stayY = d; 
    o = e;
    f = h;
  }
  Firefly(float c, float d, int s){
    a = c;
    b = d; 
    stayX = c;
    stayY = d;
    o = color(255); 
    star = s;
  }
 
    public void display(){
      stroke(o, f);
      fill(o, f);
      ellipse(a, b, inner, inner);
      stroke(o, f/2);
      fill(o,f/2);
      ellipse(a, b, outer, outer);
      stroke(255, f);
      fill(255, f);
      ellipse(a,b, center,center);
    }
    public void display2(){
      stroke(18, 54, 94, f);
      fill(o, f);
      ellipse(a, b, star, star); 
    }
    
    public void display3(){
      stroke(o, f);
      fill(o, f);
      ellipse(stayX, stayY, inner, inner);
      stroke(o, f/2);
      fill(o,f/2);
      ellipse(stayX, stayY, outer, outer);
      stroke(255, f);
      fill(255, f);
      ellipse(stayX, stayY, center,center);
    }
    
    public void move(){
      randy = random(10);
      randy2 = random(10);
      translate(randy, randy2);
    }
    
    public void move2(){
      randy2 = random(1);
      translate(0, randy2);
    }
    
    public void fade(int h){
      if(h == 1){
        if(f > 0){
        f = f - 10;
        }
        if(f <= 0)
          flies.remove(0);
        }
      else if(h == 2){
        if(f > 0){
        f = f - 10;
        }
        if(f <= 0)
          paint.remove(0);
        }
    }
    
    public void fade2(){
      if(num4 <=0 && num4 > -10){
        f = f - 10;
        num4--;
      }
      else if(num4 == -10)
        num4 = 10;
      else if(num4 <= 10 && num4 > 0){
        f = f + 10;
        num4--;
      }
    }
    
    public int timer(){
      if (t != 2){
        t--;
      }
      return t;
    }    
    
    public void setX(float val){
      a = a - val;
    }
    
    public void setY(float value){
      b = b - value;
    }
}

public void setup(){
  size(680,480);
  flies = new ArrayList();
  stream = new ArrayList();
  paint = new ArrayList();
  flyjar = new ArrayList();
  stars = new ArrayList();
}

public void redrawBackground2(){
 frameRate(10);
  stroke(204, 153, 0);
   background(pos1,pos2,pos3);
   if(pos1 <= 18){
     background(18,54, 94);
   }
    pos1 = pos1 - 3;
    pos2 = pos2 - 9;
    pos3 = pos3 - 10;
  fill(255,128,0);
  ellipse(pos, pos0, 340, 340);
  fill(51,102,0);
  if(pos1 <= 21)
    fill(40,77,0);
  rect(0, 380, 679, 99); 
}
public void redrawBackground(){
  frameRate(10);
  background(18, 54, 94);
  stroke(18, 54, 94);
  fill(255, num2);
    if(num >= 3)
      fill(255);
  num++;
  num2 = num2 + 20;
  ellipse(95, 60, 70, 70);
  fill(18,54,94);
  ellipse(105,50, 70,70);
  fill(26,51,0);
  rect(0, 380, 679, 99); 
  createStars();
  jar();
  fill(255);
  PFont myFont = createFont("Bodoni MT", 14);
  textFont(myFont);
  text("Click: Create a Fly! \nClick and Drag: Paint the Night!. \nClick the Jar: Clear the Night!", 240, 410);
  text("Click a fly: Change Fly Color!", 17, 410);
}
public void net(float a, float b){
  stroke(255);
  fill(255, 100);
  arc(a + 7, b , 20, 25, 0, 90);
  stroke(128, 51, 204);
  fill(128, 51, 204);
  ellipse(a,b, 10, 30);
  fill(255, 100);
  ellipse(a, b, 5, 15);
  fill(128, 51, 204);
  rect(a, b + 15, 1, 25);
}

public void mousePressed(){
  if(mouseY <= 380){
  fly = new Firefly(mouseX, mouseY, c, 255);
  flies.add(fly);
  }
  if(mouseY >= 430 && mouseY<=450){
    if(mouseX < 45)
      colorChange(color(204,153,0));
    if(mouseX < 67 && mouseX > 45)
      colorChange(color(61, 245,0));
    if(mouseX < 95 && mouseX > 67)
      colorChange(color(255,0,0));
    if(mouseX < 120 && mouseX > 95)
      colorChange(color(255, 0, 102));
    if(mouseX < 140 && mouseX > 120)
      colorChange(color(51, 204,204));
    }
  if(mouseY >= 350 && mouseY <= 440 && mouseX > 600 && mouseX <= 670)
    clear();
}

public void mouseDragged(){
  if(mouseY <= 380){
  p = new Firefly(mouseX, mouseY, c, 255);
  paint.add(p);
  }
  
}

public void jar(){
  flyjar = new ArrayList();
  stroke(0);
  fill(255, 67);
  rect(600, 360, 65, 85, 7);
  fill(0);
  rect(600, 350, 65, 10, 7);
  Firefly j = new Firefly(635, 380, c, 47);
  Firefly k = new Firefly(645, 395, c, 72);
  Firefly l = new Firefly(627, 402, c, 100);
  flyjar.add(j);
  flyjar.add(k);
  flyjar.add(l);
}

public void createStars(){
  stars = new ArrayList();
  Firefly a = new Firefly(530, 260, 8);
  Firefly b = new Firefly(40, 130, 8);
  Firefly c = new Firefly(190, 270, 8);
  Firefly d = new Firefly(300, 150, 8);
  Firefly e = new Firefly(410, 240, 8);
  Firefly f = new Firefly(430, 65, 8);
  Firefly g = new Firefly(665, 40, 8);
  Firefly h = new Firefly(158, 200, 8);
   Firefly i = new Firefly(45, 310, 5);
   Firefly j = new Firefly(85, 270, 5);
   Firefly k = new Firefly(141, 108, 5);
   Firefly l = new Firefly(265, 110, 5);
   Firefly m = new Firefly(210, 65, 5);
   Firefly n = new Firefly(267, 278, 5);
   Firefly o = new Firefly(380, 300, 5);
   Firefly p = new Firefly(450, 115, 5);
   Firefly q = new Firefly(600, 135, 5);
   Firefly r = new Firefly(432, 250, 5);
  stars.add(a);
  stars.add(b);
  stars.add(c);
  stars.add(d);
  stars.add(e);
  stars.add(f);
  stars.add(g);
  stars.add(h);
  stars.add(i);
  stars.add(j);
  stars.add(k);
  stars.add(l);
  stars.add(m);
  stars.add(n);
  stars.add(o);
  stars.add(p);
  stars.add(q);
  stars.add(r);
}

public void clear(){
  flies = new ArrayList();
  paint = new ArrayList();
}

public void colorChange(int d){
  c = d;
}

public void checkSize(){
  if(flies.size() >= 5){
    Firefly f = (Firefly) flies.get(0);
    f.fade(1);
  }
  
}

public void fireFly(float a, float b, int l){
   stroke(l);
   fill(l);
   ellipse(a, b, 10, 10);
   stroke(l, 126);
   fill(l,126);
   ellipse(a, b, 16, 16);
   stroke(255);
   fill(255);
   ellipse(a,b, 2,2);
}

public void draw(){
  frameRate(10);
  stroke(204, 153, 0);
  background(255,0,0);
  fill(255,128,0);
  ellipse(pos, pos0, 340, 340);
  fill(51,102,0);
  rect(0, 380, 679, 99); 
  pos0 = pos0 + 10; 
  if(pos0 >= 340 && pos0 < 600)
    redrawBackground2();
  
  if(pos1 <= 18 && pos0 >=580){
     pos1 = 18;
     pos0 = 580;
   redrawBackground();
    if(mouseY <= 380){
     net(mouseX, mouseY);
     noCursor();
   }
   if(mouseY > 380){
     cursor();
   }
   
   fireFly(25, 435, color(204,153,0));
   fireFly(50, 435, color(61, 245, 0));
   fireFly(75, 435, color(255, 0, 0));
   fireFly(100, 435, color(255, 0, 102));
   fireFly(125, 435, color(51, 204, 204));   
  
   for(int u = 0; u < stars.size(); u++){
    Firefly s = (Firefly) stars.get(u);
    s.fade2();
    s.display2();
  }
  for(int i = 0; i < flyjar.size(); i++){
      Firefly here = (Firefly) flyjar.get(i);
      here.fade2();
      here.display();
  }
  for(int i = 0; i < flies.size(); i++){
       Firefly f = (Firefly) flies.get(i);
       f.move();
       f.display();
   }
   for(int i = 0; i < paint.size(); i++){
    Firefly f = (Firefly) paint.get(i);
    f.display();
    f.fade(f.timer());
    
  } 
   checkSize();
}
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Paint_the_Night_with_Fireflies" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
