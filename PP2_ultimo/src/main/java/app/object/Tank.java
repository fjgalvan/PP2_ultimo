package app.object;

import java.awt.Point;
import app.enums.Orientation;
import app.enums.TankShot;
import app.modelo.ObjetoGrafico;
import app.state_tank.StateMoveTank;
import app.state_tank.StateMoveTankDown;

public class Tank extends ObjetoGrafico {
	private Orientation orientation;
	private TankShot tankShot;
	private Bullet bullet;
	private StateMoveTank stateMove;
	private double velocidadDeMovimiento;
	private Integer energy;

	public Tank(Orientation orientation, Point coordinate, Point size, Integer energy) {
		super(coordinate, size);
		this.orientation = orientation;
		this.tankShot = TankShot.NO_EXISTS;
		this.velocidadDeMovimiento = Configuracion.VelocidadTanque;
		this.energy = energy;
		this.stateMove = new StateMoveTankDown(this);
	}

	// dependiendo del estado en que se encuentre se gira
	// le paso una nueva orientacion que reemplaza a la anterior, y cuando lo
	// dibuje girara el tanque
	public void girar() 
	{
		this.setOrientation(getStateMove().getOrientacion());
	}

	// dependiendo del estado en que se encuentre se mueve
	public void moverse(Orientation o) {
		if (getOrientation().equals(o)){
			setearMovimiento(o);
		}
	}

	public void setearMovimiento(Orientation o) {
		if (o.equals(Orientation.RIGHT))
			this.coordinate.setLocation(coordinate.getX() + velocidadDeMovimiento, coordinate.getY());
		else if (o.equals(Orientation.LEFT))
			this.coordinate.setLocation(coordinate.getX() - velocidadDeMovimiento, coordinate.getY());
		else if (o.equals(Orientation.UP))
			this.coordinate.setLocation(coordinate.getX(), coordinate.getY() - velocidadDeMovimiento);
		else
			this.coordinate.setLocation(coordinate.getX(), coordinate.getY() + velocidadDeMovimiento);
	}

	public void disparar() {
		Point aux = new Point();
		if (tankShot.equals(TankShot.NO_EXISTS)) {
			aux.setLocation(this.coordinate.getX() + 20, this.coordinate.getY() + 20);
			bullet = new Bullet(orientation, aux, new Point(10, 10));
			tankShot = TankShot.EXISTS;
		}
	}

	public double getAngulo() {
		if (this.orientation.equals(Orientation.UP)) {
			return 0;
		}
		if (this.orientation.equals(Orientation.DOWN)) {
			return Math.PI;
		}
		if (this.orientation.equals(Orientation.LEFT)) {
			return Math.PI * 1.5;
		}
		if (this.orientation.equals(Orientation.RIGHT)) {
			return Math.PI / 2;
		}
		return 0;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public TankShot getTankBullet() {
		return tankShot;
	}

	public void impactBullet()
	{
		this.setTankBullet(TankShot.NO_EXISTS);
		this.setBullet(null);
	}
	
	public void setTankBullet(TankShot tankShot) {
		this.tankShot = tankShot;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public StateMoveTank getStateMove() {
		return stateMove;
	}

	public void setStateMove(StateMoveTank state) {
		this.stateMove = state;
	}

	public boolean existeDisparoEnEjecucion() {
		return getTankBullet().equals(TankShot.EXISTS);
	}

	public Integer getEnergyVal() {
		return this.energy;
	}

	public void increaseHP() {
		this.energy += 1;
		validarEnergy();
	}

	public void decreaseHP() {
		this.energy -= 1;
		validarEnergy();
	}

	public boolean sinEnergy() 
	{
		return this.energy == 0;
	}

	private void validarEnergy() {
		if (this.energy < 0) {
			this.energy = 0;
		} else if (this.energy > 2) {
			this.energy = 2;
		}
	}
	
	public boolean estaEnLaMismaOrientacion() 
	{
		return this.getOrientation().equals(this.getStateMove().getOrientacion());
	}
}
