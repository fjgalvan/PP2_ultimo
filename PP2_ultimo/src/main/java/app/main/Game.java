package app.main;

import java.awt.Point;
import java.util.*;
import app.levels.DataLevelGame;
import app.levels.LevelEasy;
import app.mapa.MapaTiled;
import app.modelo.*;
import app.object.*;
import entorno.InterfaceJuego;

public class Game extends InterfaceJuego {
	private static Player player1;
	private static Player player2;
	private Tank tank;
	private Draftsman dibujador;
	private List<ObjetoGrafico> estructuras;
	private MapaTiled mapaTiled;
	private TankController tControl;
	private Destructor destructor;
	private Tank enemyTank;
	private Tank enemyTank2;
	private Tank enemyTank3;
	private Tank enemyTank4;
	private List<Tank> tanks;
	private List<Tank> enemysTanks;
	private boolean modoJuegoCop = true;
	private Tank tankCop;
	private TankController tControlCop;
	private LevelEasy levelGame;
	private String mapaAJugar = Configuracion.MapaTank;
	
	public Game(DataLevelGame data) {
		this.dibujador = new Draftsman(this, new Point(1000, 600), "Battle-Ungs");
		estructuras = new ArrayList<>();
		this.mapaTiled = new MapaTiled(mapaAJugar);
		this.mapaTiled.inicializar();
		this.mapaTiled.crearEstructuras(estructuras);
		//prueba de apoco el lector de datos del combo .. 1 test modo cop
		levelGame = new LevelEasy(data);
		// ver como ubicar el tanque
		this.modoJuegoCop = levelGame.isModeCopActivate();
		this.tank = levelGame.getTankPlayer1();
		this.destructor = new Destructor();
		Game.player1 = new Player(0, 0, levelGame.getTankController().getListener());
		this.tControl = levelGame.getTankController();
		this.tanks = new ArrayList<Tank>();
		this.enemysTanks = new ArrayList<Tank>();
		
		// COP
		if (modoJuegoCop) {
			Game.player2 = new Player(0, 0, new ListenerPlayer2(dibujador.getEntorno()));
			// ver como ubicar el tanque
			this.tankCop = levelGame.getTankPlayer2();
			this.tControlCop = new TankController(tankCop, player2.getListener(), destructor.getColisionador());
			this.tanks.add(tankCop);
		}
	}

	public void iniciar() {
		this.tanks.add(tank);
		this.enemysTanks.add(enemyTank);
		this.enemysTanks.add(enemyTank2);
		this.enemysTanks.add(enemyTank3);
		this.enemysTanks.add(enemyTank4);
	}

	public void tick(){ 
		if (!levelGame.finishLevel()) 
		{
			for (ObjetoGrafico e : estructuras) 
			{
				this.dibujador.dibujarEstructura(e);
			}
			if (levelGame.containsPlayer1()) 
			{
				player1.getListener().seMovio(tank);
				this.dibujador.dibujarTank(tank);
				this.tControl.ControlTank(estructuras);
				this.tControl.control_bullet(estructuras, this.enemysTanks);
				if(tank.existeDisparoEnEjecucion()){
					dibujador.dibujarBullet(tank.getBullet());
				}
				destructor.destruccionTanksEnemys(tank,levelGame,LevelEasy.P1);
				this.tControl.setContTick(this.tControl.getContTick() + 1);
			}
			// COOP
			if (levelGame.containsPlayer2()) 
			{	
				player2.getListener().seMovio(tankCop);
				this.dibujador.dibujarTankCop(tankCop);
				this.tControlCop.ControlTank(estructuras);
				if(tankCop.existeDisparoEnEjecucion()){
					dibujador.dibujarBullet(tankCop.getBullet());
				}
				this.tControlCop.control_bullet(estructuras, this.enemysTanks);
				destructor.destruccionTanksEnemys(tankCop,levelGame,LevelEasy.P2);
				this.tControlCop.setContTick(this.tControlCop.getContTick() + 1);
			}

			for (DriverEnemyTank driver : levelGame.getDrives())
			{
				this.dibujador.dibujarEnemyTank(driver.getEnemyTank());
				driver.ControlEnemyTank(dibujador.getEntorno(), estructuras);
				destructor.destruccionTank(driver.getEnemyTank(), levelGame);
				driver.setContTick(driver.getContTick() + 1);
				if(driver.getEnemyTank().existeDisparoEnEjecucion()){
					dibujador.dibujarBullet(driver.getEnemyTank().getBullet());
				}
			}
        	destructor.destruirEstructuras(estructuras);
		} 
		else 
		{
			this.dibujador.dibujarFinDeJuego();
			this.dibujador.dibujarPuntaje("Player 1: ", Game.player1.getPuntaje(),
			Game.player1.getCantidadDeEnemigosAsesinados(), 200, 400);
			if (Game.player2 != null)
				this.dibujador.dibujarPuntaje("Player 2: ", Game.player2.getPuntaje(),
				Game.player2.getCantidadDeEnemigosAsesinados(), 200, 430);
		}
	}

	public static void sumarPuntaje(String player) {
		if (player.equals("1")) {
			player1.setPuntaje(500);
			player1.setCantidadDeEnemigosAsesinados(0);
		} else {
			player2.setPuntaje(500);
			player2.setCantidadDeEnemigosAsesinados(0);
		}
	}

	public static void sumarCantidadDeEnemigosAsesinados(String player) {
		if (player.equals("1")) {
			player1.setPuntaje(0);
			player1.setCantidadDeEnemigosAsesinados(1);
		} else {
			player2.setPuntaje(0);
			player2.setCantidadDeEnemigosAsesinados(1);
		}
	}

	public Draftsman getDibujador() {
		return dibujador;
	}
}
