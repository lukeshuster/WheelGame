package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
    /* Attributes */
    private int position;
    private Color color;
    private int number;

    /* Constructor */
    public SlotImpl(int position, Color color, int number){
        this.position = position;
        this.color = color;
        this.number = number;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		result = prime * result + position;
		return result;
	}

    @Override
    public boolean equals(Slot slot) {
    	if (this == slot) {
    		return true;
    	}
    	if (slot == null) {
    		return false;
    	}
    	if (color != slot.getColor()) {
    		return false;
    	}
    	if (number != slot.getNumber()) {
    		return false;
    	}
    	if (position != slot.getPosition()) {
    		return false;
    	}
    	return true;
    }

	@Override
	public boolean equals(Object obj) {
		Slot other = (Slot) obj;
		return this.equals(other);
	}

	public Color getColor(){
    	return color;
    }

    public int getNumber(){
    	return number;
    }

    public int getPosition(){
    	return position;
    }


    @Override
    public String toString(){
    	return "Position: = '" + this.position + "', Colour: = '" + this.color + "', Number: = '" + this.number + "'";
    }

}
