import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Hanoi_game extends PApplet {



ArrayList<Integer> A, B, C, to, from;
int n, count, mouse_pressedX, mouse_releasedX, mouse_pressedY, mouse_releasedY;
int size;
char fromChar, toChar;
String tekst;
float k;

public void setup() {
  
  n = 6;
  count = 0;
  k = pow(2,n) - 1;
  tekst = "";
  A = new ArrayList<Integer>();
  B = new ArrayList<Integer>();
  C = new ArrayList<Integer>();
  size = height/(2*n);
  for (int i = 0; i < n; i++) {
    A.add(n-i);
  }
  frameRate(5);
}

public void draw() {
  background(173,216,230);
  
  println("(" + mouse_pressedX + " , " + mouse_pressedY + ")");
  println("(" + mouse_releasedX + " , " + mouse_releasedY + ")");
  println();
    
  move();
  
  println("Move from " + fromChar + " to " + toChar );
  println();
  
  swap1(from, to);
  
  println(A);
  println(B);
  println(C);
  println();

  fill(160,82,45);
  rect((width - 10)/4-5, height/6, 10, height*4/6);
  rect((width - 10)/2-5, height/6, 10, height*4/6);
  rect(3*(width - 10)/4-5, height/6, 10, height*4/6);
  fill(218,165,32);
  drawStack((width - 10)/4, 5*height/6, A);
  drawStack((width - 10)/2, 5*height/6, B);
  drawStack(3*(width - 10)/4, 5*height/6, C);
  
  textSize(32);
  text("Andrea Ćirić 2016/0202 OE", width - 450, height - 10);
  textSize(20);
  text("Minimalan broj koraka je: " + str(floor(k)), width - 350, 30);
  text(tekst, 40, 50);
  
  if (C.size() == n) {
   textSize(100);
   fill(255,0,0);
   background(0);
   text("Čestitamo!", width/2 - 250, height/2);
  }
  
  noLoop();
}

public void mousePressed(){
  mouse_pressedX = mouseX;
  mouse_pressedY = mouseY;
}

public void move(){
  if (mouse_pressedX < 450) {
    from = A;
    fromChar = 'A';
  } else if (mouse_pressedX < 750) {
    from = B;
    fromChar = 'B';
  } else {
    from = C;
    fromChar = 'C';
  }
  
  if (mouse_releasedX < 450) {
    to = A;
    toChar = 'A';
  } else if (mouse_releasedX < 750) {
    to = B;
    toChar = 'B';
  } else {
    to = C;
    toChar = 'C';
  }
}

public void mouseReleased(){
  mouse_releasedX = mouseX;
  mouse_releasedY = mouseY;
  loop();
}

public void drawStack(int xpos, int ypos, ArrayList<Integer> stack) {
  for (int i = 0; i < stack.size(); i++) {
    rect(xpos - stack.get(i) * size/2, ypos - (i+1) * size, stack.get(i) * size, size);
  }
}

public void swap1(ArrayList<Integer> A, ArrayList<Integer> B) {
  int nA = A.size(), nB = B.size();
  if (nA > 0 && nB > 0) { 
    if (A.get(nA-1) < B.get(nB-1)) {
      B.add(A.get(nA-1));
      A.remove(nA-1);
      count = count + 1;
      tekst = "broj napravljenih koraka je : " + str(count);
    } else if (A.get(nA-1) > B.get(nB-1)) {
    tekst = "Potez nije dozvoljen!";
      }    
    } else if (nA > 0) {
      B.add(A.get(nA-1));
      A.remove(nA-1);
      count = count + 1;
      tekst = "broj napravljenih koraka je : " + str(count);
    } else if (nB > 0) {
      A.add(B.get(nB-1));
      B.remove(nB-1);
      count = count + 1;
      tekst = "broj napravljenih koraka je : " + str(count);
    } 
}
  public void settings() {  size(1200, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Hanoi_game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
