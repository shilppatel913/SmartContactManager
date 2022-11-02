package smartcontactmanager.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uid")
	private int id;
	@Column(name = "uname")
	private String name;
	@Column(name = "uemail")
	private String email;
	@Column(name = "upassword")
	private String password;
	@Column(name = "udesc")
	private String desc;
	@Column(name = "uimgurl")
	private String imageURL;
	@Column(name = "urole")
	private String role;
	@Column(name = "uenabled")
	private boolean enabled;
	
	//let us define the mapping from user to contact , a user may have many contacts
	//now as we have created list of contacts a seperate table will be created named user_contacts(user_id,contact_id)
	//but we dont want that we just want to have user_id as a foreign key in the contact table
	//hence we can use mappedBy property to do so in the below mapping
	//we use cascade all  so that when user is created and contact is mapped to it
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Contact> contact=new ArrayList<>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	public User(int id, String name, String email, String password, String desc, String imageURL, String role,
			boolean enabled, List<Contact> contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.desc = desc;
		this.imageURL = imageURL;
		this.role = role;
		this.enabled = enabled;
		this.contact = contact;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", desc=" + desc
				+ ", imageURL=" + imageURL + ", role=" + role + ", enabled=" + enabled + ", contact=" + contact + "]";
	}
	
	
}
