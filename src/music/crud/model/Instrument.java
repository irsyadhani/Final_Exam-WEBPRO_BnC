package music.crud.model;

public class Instrument {
	private int id;
	private String name;
	private String price;
	private String status;
	
	public Instrument(int id, String name, String price, String status) {
	super();
	this.id = id;
	this.name = name;
	this.price = price;
	this.status = status;
	}

	public Instrument(String name, String price, String status) {
		super();
		this.name = name;
		this.price = price;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
