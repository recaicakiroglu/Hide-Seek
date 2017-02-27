
public class Human {
	//attributes
	private long id;
	private String name;
	private String lastname;
	private String city;
	private String address;
	private long SSN;
//constructor
	public Human(long id, String name, String lastname, String city, String address, long sSN) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.city = city;
		this.address = address;
		SSN = sSN;
	}
//getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getSSN() {
		return SSN;
	}

	public void setSSN(long sSN) {
		SSN = sSN;
	}

}
