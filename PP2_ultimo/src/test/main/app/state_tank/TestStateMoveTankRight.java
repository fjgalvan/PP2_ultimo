package app.state_tank;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import app.enums.Orientation;
import app.object.Tank;

public class TestStateMoveTankRight {

	@Test
	public void testHayColision() 
	{
		Tank tank = new Tank(Orientation.RIGHT, new Point(100, 100), new Point(50,50), 2);
		StateMoveTankRight state = new StateMoveTankRight();
		state.setTank(tank);
		tank.setStateMove(state);
		Tank otro = new Tank(Orientation.RIGHT, new Point(350, 225), new Point(50,50), 2);
		assertFalse(tank.getStateMove().hayColision(otro));
		tank.setCoordinate(new Point(300, 225));
		assertTrue(tank.getStateMove().hayColision(otro));
		state.control();
	} 
	
	@Test
	public void testControl() 
	{
		Tank tank = new Tank(Orientation.RIGHT, new Point(100, 100), new Point(30,30), 2);
		StateMoveTankRight state = new StateMoveTankRight();
		state.setTank(tank);
		tank.setStateMove(state);
		assertTrue(tank.getCoordinate().equals(new Point(100,100)));
		tank.getStateMove().control();
		assertFalse(tank.getCoordinate().equals(new Point(100,100)));
	}

	@Test
	public void testGetOrientacion() 
	{
		StateMoveTankRight state = new StateMoveTankRight();
		assertTrue(state.getOrientacion()==Orientation.RIGHT);
		assertFalse(state.getOrientacion()==Orientation.UP);
	}

	@Test
	public void testSetTank() 
	{
		StateMoveTankRight state = new StateMoveTankRight();
		assertTrue(state.tank==null);
		state.setTank(new Tank(Orientation.RIGHT, new Point(100, 100), new Point(30,30), 2));
		assertTrue(state.tank!=null);
	}

}
