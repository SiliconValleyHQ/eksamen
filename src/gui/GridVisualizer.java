package gui;

import java.awt.Color;

public class GridVisualizer
{

	//some customizing from this point it's not clear yet if we will use it as it is here
	public static Color getActiveRectangleColor()
	{
		return Color.GREEN;
	}

	public static Color getNormalRectangleColor()
	{
		return Color.CYAN;
	}

	public static Color getGridColor()
	{
		return Color.BLACK;
	}

	public static Color getMovedFromColor() {return Color.BLUE;}

	public static Color getMovedToColor() {return Color.YELLOW;}
}