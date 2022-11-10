package smartcontactmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CONTACT")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cid")
	private int cid;
	@Column(name = "cname")
	private String cname;
	@Column(name = "cnickname")
	private String cnickname;
	@Column(name = "cwork")
	private String cwork;
	@Column(name = "cemail")
	private String cemail;
	@Column(name = "cimgurl")
	private String cimgURL;
	@Column(name = "cdescription")
	private String cdescription;
	@Column(name = "cphone")
	private String cphone;
	
	//here we will map a contact to only one user hence user_id will be foreign key here
	@ManyToOne
	@JsonIgnore
	private User user;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCnickname() {
		return cnickname;
	}

	public void setCnickname(String cnickname) {
		this.cnickname = cnickname;
	}

	public String getCwork() {
		return cwork;
	}

	public void setCwork(String cwork) {
		this.cwork = cwork;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getCimgURL() {
		return cimgURL;
	}

	public void setCimgURL(String cimgURL) {
		this.cimgURL = cimgURL;
	}

	public String getCdescription() {
		return cdescription;
	}

	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contact(int cid, String cname, String cnickname, String cwork, String cemail, String cimgURL,
			String cdescription, String cphone, User user) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cnickname = cnickname;
		this.cwork = cwork;
		this.cemail = cemail;
		this.cimgURL = cimgURL;
		this.cdescription = cdescription;
		this.cphone = cphone;
		this.user = user;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
