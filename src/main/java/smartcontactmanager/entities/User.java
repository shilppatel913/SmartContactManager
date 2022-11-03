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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uid")
	private int id;
	@NotBlank(message = "The Name field cannot be empty")
	@Size(min=3,max=20,message="The characters should be between 3 and 20")
	@Column(name = "uname")
	private String uname;
	@Column(name = "uemail")
	private String uemail;
	@Column(name = "upassword")
	private String upassword;
	@Column(name = "udesc")
	private String udesc;
	@Column(name = "uimgurl")
	private String imageURL;
	@Column(name = "urole")
	private String urole;
	@Column(name = "uenabled")
	private boolean uenabled;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private List<Contact> contact=new ArrayList<>();
	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", uemail=" + uemail + ", upassword=" + upassword + ", udesc="
				+ udesc + ", imageURL=" + imageURL + ", urole=" + urole + ", uenabled=" + uenabled + ", contact="
				+ contact + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String uname, String uemail, String upassword, String udesc, String imageURL, String urole,
			boolean uenabled, List<Contact> contact) {
		super();
		this.id = id;
		this.uname = uname;
		this.uemail = uemail;
		this.upassword = upassword;
		this.udesc = udesc;
		this.imageURL = imageURL;
		this.urole = urole;
		this.uenabled = uenabled;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUdesc() {
		return udesc;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public boolean isUenabled() {
		return uenabled;
	}

	public void setUenabled(boolean uenabled) {
		this.uenabled = uenabled;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}


	
	//let us define the mapping from user to contact , a user may have many contacts
	//now as we have created list of contacts a seperate table will be created named user_contacts(user_id,contact_id)
	//but we dont want that we just want to have user_id as a foreign key in the contact table
	//hence we can use mappedBy property to do so in the below mapping
	//we use cascade all  so that when user is created and contact is mapped to it
	

	
	
}
