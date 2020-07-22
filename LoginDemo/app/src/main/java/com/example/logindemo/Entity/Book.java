package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Book implements Parcelable {
    Integer id;
    String bookName;
    String overview;
    String cover;
    String material;
    String description;
    double price;
    Integer numberOfPage;
    String size;
    double version;
    double weight;
    Integer quantity;
    String isbn;
    boolean status;
    public Publisher publisher;
    public Language language;
    public BookShelf bookshelf;
    public List<Author> authors;
    public List<Translator> translators;
    public List<Category> categories;
    public List<BorrowRequest> borrowRequests;
    public List<Cart> carts;

    public Book(Integer id, String bookName, String overview, String cover, String material, String description, double price, Integer numberOfPage, String size, double version, double weight, Integer quantity, String isbn, boolean status, Publisher publisher, Language language, BookShelf bookShelf, List<Author> authors, List<Translator> translators, List<Category> categories, List<BorrowRequest> borrowRequests, List<Cart> carts) {
        this.id = id;
        this.bookName = bookName;
        this.overview = overview;
        this.cover = cover;
        this.material = material;
        this.description = description;
        this.price = price;
        this.numberOfPage = numberOfPage;
        this.size = size;
        this.version = version;
        this.weight = weight;
        this.quantity = quantity;
        this.isbn = isbn;
        this.status = status;
        this.publisher = publisher;
        this.language = language;
        this.bookshelf = bookShelf;
        this.authors = authors;
        this.translators = translators;
        this.categories = categories;
        this.borrowRequests = borrowRequests;
        this.carts = carts;
    }

    public Book() {
    }


    protected Book(Parcel in) {
        language = (Language) in.readParcelable(Language.class.getClassLoader());
        publisher = (Publisher) in.readParcelable(Publisher.class.getClassLoader());
        bookshelf = (BookShelf) in.readParcelable(BookShelf.class.getClassLoader());
        authors = new ArrayList<Author>();
        in.readList(authors, getClass().getClassLoader());

        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        bookName = in.readString();
        overview = in.readString();
        cover = in.readString();
        material = in.readString();
        description = in.readString();
        price = in.readDouble();
        if (in.readByte() == 0) {
            numberOfPage = null;
        } else {
            numberOfPage = in.readInt();
        }
        size = in.readString();
        version = in.readDouble();
        weight = in.readDouble();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        isbn = in.readString();
        status = in.readByte() != 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(language, flags);
        dest.writeParcelable(publisher, flags);
        dest.writeParcelable(bookshelf, flags);
        dest.writeList(authors);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(bookName);
        dest.writeString(overview);
        dest.writeString(cover);
        dest.writeString(material);
        dest.writeString(description);
        dest.writeDouble(price);
        if (numberOfPage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(numberOfPage);
        }
        dest.writeString(size);
        dest.writeDouble(version);
        dest.writeDouble(weight);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeString(isbn);
        dest.writeByte((byte) (status ? 1 : 0));

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublishers(Publisher publishers) {
        this.publisher = publishers;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public BookShelf getBookShelf() {
        return bookshelf;
    }

    public void setBookShelf(BookShelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translator> translators) {
        this.translators = translators;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<BorrowRequest> getBorrowRequests() {
        return borrowRequests;
    }

    public void setBorrowRequests(List<BorrowRequest> borrowRequests) {
        this.borrowRequests = borrowRequests;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
