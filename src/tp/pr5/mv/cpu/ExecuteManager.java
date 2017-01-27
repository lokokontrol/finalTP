package tp.pr5.mv.cpu;

public class ExecuteManager {

	private int currentPC;
	private int nextPC;
	private boolean halt;

	public ExecuteManager() {
		this.currentPC = 0;
		this.nextPC = 0;
		this.halt = false;
	}

	public int getCurrentPC() {
		return currentPC;
	}

	public void setCurrentPC(int currentPC) {
		this.currentPC = currentPC;
	}

	public int getNextPC() {
		return nextPC;
	}

	public void setNextPC(int nextPC) {
		this.nextPC = nextPC;
	}

	public void setHalt(boolean halt) {
		this.halt = halt;
	}

	public boolean getHalt() {
		return this.halt;
	}

	public void incrementPC() {
		if (currentPC == nextPC) {
			this.currentPC++;
			this.nextPC++;
		} else
			this.currentPC = this.nextPC;
	}

	public void incrementPCJump() {
		this.currentPC = this.nextPC;
	}
	
	public void reset(){
		this.currentPC = 0;
		this.nextPC = 0;
	}

}
