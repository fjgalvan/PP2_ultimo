package app.state_tank;

import app.enums.Orientation;
import app.modelo.Colisionador;
import app.modelo.ObjetoGrafico;
import app.object.Configuracion;
import app.object.Tank;

public class StateMoveTankUp extends StateMoveTank
{
	public StateMoveTankUp(){}
	
	public StateMoveTankUp(Tank tanque) 
	{
		super(tanque);
	}
	
	public boolean hayColision(ObjetoGrafico obj) 
	{
		Colisionador col = Colisionador.getInstance();
		return col.hayColisionArriba(tank, obj);
	}

	public void control() 
	{
		if(tank.getCoordinate().getY() >= Configuracion.MinCoordinateY)//20)
			tank.moverse(getOrientacion());		
	}

	public Orientation getOrientacion() 
	{
		return Orientation.UP;
	}
}
