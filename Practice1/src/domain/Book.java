package domain;

public class Book {
	private String title;
	private String author;
	private int publicationYear;

	public Book(String title, String author, int publicationYear) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	@Override
	public String toString() {
		return "Título='" + title + '\'' + ", Autor='" + author + '\'' + ", Año de publicación=" + publicationYear;
	}
}
