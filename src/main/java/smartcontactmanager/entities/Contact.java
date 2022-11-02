package smartcontactmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cid")
	private int id;
	@Column(name = "cname")
	private String name;
	@Column(name = "cnickname")
	private String nickname;
	@Column(name = "cwork")
	private String work;
	@Column(name = "cemail")
	private String email;
	@Column(name = "cimgurl")
	private String imgURL;
	@Column(name = "cdescription")
	private String description;
	@Column(name = "cphone")
	private String phone;
	
	//here we will map a contact to only one user hence user_id will be foreign key here
	@ManyToOne
	private User user;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact(int id, String name, String nickname, String work, String email, String imgURL, String description,
			String phone, User user) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.work = work;
		this.email = email;
		this.imgURL = imgURL;
		this.description = description;
		this.phone = phone;
		this.user = user;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", nickname=" + nickname + ", work=" + work + ", email=" + email
				+ ", imgURL=" + imgURL + ", description=" + description + ", phone=" + phone + ", user=" + user + "]";
	}
	
	
}
