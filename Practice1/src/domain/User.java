package domain;

public class User {
	private String name;
    private String email;
    private String bookName;

    public User(String name, String email, String bookName) {
        this.name = name;
        this.email = email;
        this.bookName = bookName;
    }


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getBookName() {
		return bookName;
	}



	public void setBookName(String bookName) {
		this.bookName = bookName;
	}



	@Override
    public String toString() {
        return "Nombre='" + name + '\'' +
                ", Correo='" + email + '\'' +
                ", Libro prestado=" + bookName;
    }
}