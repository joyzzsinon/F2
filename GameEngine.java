package F2;


	
	 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import java.awt.geom.Rectangle2D;
 import java.util.ArrayList;
 import java.util.Iterator;
 
 import javax.swing.Timer;
 
 
 public class GameEngine implements KeyListener{
 	GamePanel gp;
 	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
  	private SpaceShip v;	
  	
  	private Timer timer;
 	private double diff = 0.1;
 	private double difficulty = 0.1;
  	public GameEngine(GamePanel gp, SpaceShip v) {
  		this.gp = gp;
  		this.v = v;		
 		process();
 	
  		gp.sprites.add(v);
  		
  		timer = new Timer(50, new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				process();
 			}
 		});
 		timer.setRepeats(true);
 		
 	}
 	
 	public void start(){
 		timer.start();
 	}
 	private void generateEnemy(){
  		Enemy e = new Enemy(100,50);
  		gp.sprites.add(e);
   		enemies.add(e);
   	}
   	private void process(){
  		generateEnemy();
  		if(Math.random() < difficulty){
  			generateEnemy();	
  		}
  		Iterator<Enemy> e_iter = enemies.iterator();
  		while(e_iter.hasNext()){
  			Enemy e = e_iter.next();
  			e.proceed();
  			if(!e.isAlive()){
  				e_iter.remove();
  				gp.sprites.remove(e);
  			}
  		}
   		gp.updateGameUI();
 		Rectangle2D.Double er;
  		for(Enemy e : enemies){
  			er = e.getRectangle();
        if(er.intersects(vr)){
          die();
          return;
        }
   	}
  
 	
 	}

  public void die(){
    timer.stop();
  }

  	void controlVehicle(KeyEvent e) {
  		switch (e.getKeyCode()) {
  		case KeyEvent.VK_LEFT:
  			v.move(-1);
  			break;
  		case KeyEvent.VK_RIGHT:
  			v.move(1);
  			break;
  		case KeyEvent.VK_D:
  			diff += 0.1;
  			break;
 		 }
   	}
   
  public long getScore(){
    return score;
  }
  	
   	@Override
   	public void keyPressed(KeyEvent e) {
   		controlVehicle(e);
  		
  	}
  
  	@Override
  	public void keyReleased(KeyEvent e) {
  		
  	}
  
  	@Override
  	public void keyTyped(KeyEvent e) {
  				
  	}
 	
 }	