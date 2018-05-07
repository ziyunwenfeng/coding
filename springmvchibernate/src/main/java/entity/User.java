package entity;

import javax.persistence.*;

@Entity
@Table(name="t_user")
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;    
    @Column(name="name",length=32)
    private String name;
    @Column(name="pwd")
    private String pwd;
    public User(){}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
