import java.util.*;

ArrayList<Integer> A, B, C;
int n,k;
int size;

void setup() {
  size(1200, 600);
  n = 6;
 // k = floor(log(2^n -1)/log(2)) + 1;
  A = new ArrayList<Integer>();
  B = new ArrayList<Integer>();
  C = new ArrayList<Integer>();
  size = height/(2*n);
  for (int i = 0; i < n; i++) {
    A.add(n-i);
  }
  frameRate(5);
}

void draw() {
  background(173,216,230);

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
  
    if (frameCount < 1 << n){
      towerOfHanoiIt();
      
      text("Binary: " + binary(frameCount,n), 40, 50 );
    }
    
    
    
      noLoop();
}

void mousePressed() {
  loop();
}

void drawStack(int xpos, int ypos, ArrayList<Integer> stack) {
  for (int i = 0; i < stack.size(); i++) {
    rect(xpos - stack.get(i) * size/2, ypos - (i+1) * size, stack.get(i) * size, size);
  }
}

void towerOfHanoiIt() {
  if (frameCount%3 == 1) {
    swap(A, n%2 == 0 ? B : C);
  } else if (frameCount%3 == 2) {
    swap(A, n%2 == 0 ? C : B);
  } else {
    swap(B, C);
  }
}

void swap(ArrayList<Integer> A, ArrayList<Integer> B) {
  int nA = A.size(), nB = B.size();
  if (nA > 0 && nB > 0) { 
    if (A.get(nA-1) > B.get(nB-1)) {
      A.add(B.get(nB-1));
      B.remove(nB-1);
    } else {
      B.add(A.get(nA-1));
      A.remove(nA-1);
    }
  } else if (nA > 0) {
    B.add(A.get(nA-1));
    A.remove(nA-1);
  } else if (nB > 0) {
    A.add(B.get(nB-1));
    B.remove(nB-1);
  }
}
