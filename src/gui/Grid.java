package gui;

import game.Move;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


//Now we have a GRid that is canvas
public class Grid
		extends Canvas
	implements ActionListener
{
	private int       rows;
	private int       cols;
	private Dimension dim ;
	private Map<Point, Rectangle> rectangles;

	//We will remember two rectangles that where clicked last. First rectangle will be from, and the next will be to.
	Rectangle rectangleFrom;

	public void actionPerformed(ActionEvent e)
	{
		//when action receiveddo whathever ..
		//we must educate grid to understand messages it receives
		if (e.getActionCommand().equals("GOT WELCOMING")) //string comparison! do with .equals
		{

		}
		else if (e.getActionCommand().equals("REPAINT"))
		{
			repaint();
		}
		else if (e.getActionCommand().startsWith("REPAINT ON MOVE"))
		{
			//do some logic. Paint moved tiles different color
			//must parse again. Doen't know how to pass context. So just reparse move string. This is bad code smell.
			Scanner scanner = new Scanner(e.getActionCommand());
			scanner.next();
			scanner.next();
			scanner.next();
			//skip words REPAINT ON MOVE
			String from = scanner.next();
			String to = scanner.next();
			Move move = new Move(from, to); //now we know what moved.
			Rectangle movingFrom = new ArrayList<Rectangle>(getRectangles().values()).get(getRows()*move.getyFrom()+move.getxFrom());
			Rectangle movingTo =  new ArrayList<Rectangle>(getRectangles().values()).get(getRows()*move.getyTo()+move.getxTo());
					//now we know which rectanlges moved . from there we could add some customisation to
			//class Rectangle to draw
			movingFrom.setMovedFrom(true);
			movingTo.setMovedTo(true);

			repaint();

			//once drawn we can unset
			movingFrom.setMovedFrom(null);
			movingTo.setMovedTo(null);
		}
	}

	public void clicked(Rectangle r)
	{
		if (getClicks() == 0) //that means we have first rectangle
		{
			setRectangleFrom(r);
		}
		else if (getClicks() == 1 )//that means we have second rectangle
		{
			setRectangleTo(r);
		}
		setClicks(getClicks()+1);
		//once we will use rectangles we will have to reset click counter to 0.
	}
	public Rectangle getRectangleFrom() {
		return rectangleFrom;
	}

	public void setRectangleFrom(Rectangle rectangleFrom) {
		this.rectangleFrom = rectangleFrom;
	}

	public Rectangle getRectangleTo() {
		return rectangleTo;
	}

	public void setRectangleTo(Rectangle rectangleTo) {
		this.rectangleTo = rectangleTo;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	Rectangle rectangleTo;
	int clicks = 0;

	public Grid(int width, int height, int rows, int cols)
	{
		dim = new Dimension(width, height);
		this.rows = rows;
		this.cols = cols;

		rectangles = new LinkedHashMap<Point, Rectangle>();//we are saved linkedhashmap preserves ordering
		for (int row = 0; row < getRows(); row++)
		{
			for (int col = 0; col < getCols(); col++)
			{
				Rectangle r = makeRectangle(row, col);
				rectangles.put(new Point(row, col), r);
			}
		}

	}

	//some getters and setters

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	public int getRows()
	{
		return rows;
	}

	public void setCols(int cols)
	{
		this.cols = cols;
	}

	public int getCols()
	{
		return cols;
	}

	public void setDimension(Dimension dim)
	{
		this.dim = dim;
	}

	public Dimension getDimension()
	{
		return dim;
	}

	//grid will be made of rectangles
	public void setRectangles(Map<Point, Rectangle> rectangles)
	{
		this.rectangles = rectangles;
	}

	public Map<Point, Rectangle> getRectangles()
	{
		return rectangles;
	}

	public List<Rectangle> rectangles()
	{
		return new ArrayList<Rectangle>(getRectangles().values());
	}

	//this code is important when we want to get rectangle on which the mouse was clicked.
	//we pass coordinates of the clicked point.
	//then we go through each rectangle and check if x,y point is in any rectangle. once the rectangle found return
	public Rectangle getRectangle(int x, int y)
	{
		Rectangle rectangle = null;
		for (Rectangle r : rectangles())
		{
			if (r.contains(x,y))//this is where x,y is checked whether it is in rectangle
			{
				rectangle = r;
			}
		}
		return rectangle;
	}


	//constructs rectangle by it's position given by row and column indexes.
	//have to do some math here to calculate rectangles location
	public Rectangle makeRectangle(int row, int col)
	{
		Rectangle r = new Rectangle(
				(int) getWidth()/getCols(),
				(int) getHeight()/getRows());
		r.setLocation((int)(col*r.getWidth()), (int)(row*r.getHeight()));
		return r;
	}

	public int getHeight()
	{
		return (int) getDimension().getHeight();//we don't need double preecision here
	}

	public int getWidth()
	{
		return (int) getDimension().getWidth();
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		for (Rectangle r : rectangles())
		{
			if (r.isActive())
			{
				g2.setPaint(GridVisualizer.getActiveRectangleColor());
			}
			else
			{
				g2.setPaint(GridVisualizer.getNormalRectangleColor());
			}
			g2.fill(r);
		}
		paintGrid(g2);
	}
//we can draw on canvas. We draw rectangles on it . If we put that canvas on jpanel we can visualize the grid.
	// Now have to hook grid with board. So that we can see board information on the grid.
	public void paintGrid(Graphics2D g2)
	{
		g2.setPaint(GridVisualizer.getGridColor());
		for (Rectangle r : rectangles())
		{
			g2.draw(r);
		}
	}

	@Override
	public java.awt.Dimension getPreferredSize()
	{
		return new java.awt.Dimension(getWidth(), getHeight());
	}
}