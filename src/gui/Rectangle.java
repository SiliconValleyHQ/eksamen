package gui;

public class Rectangle
		extends java.awt.Rectangle
{
	private boolean active;

	public Boolean getMovedFrom() {
		return movedFrom;
	}

	public void setMovedFrom(Boolean movedFrom) {
		this.movedFrom = movedFrom;
	}

	public Boolean getMovedTo() {
		return movedTo;
	}

	public void setMovedTo(Boolean movedTo) {
		this.movedTo = movedTo;
	}

	Boolean movedFrom = null;
	Boolean movedTo = null;

	public Rectangle(int x, int y)
	{
		super(x,y);
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean getActive()
	{
		return active;
	}

	public boolean isActive()
	{
		return active;
	}

	public void toggleActive()
	{
		setActive(!getActive());
	}
}