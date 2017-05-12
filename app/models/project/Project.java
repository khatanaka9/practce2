package models.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.experimental.Accessors;
import models.project.types.ProjectType;
import models.user.User;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import play.db.jpa.Model;

@Entity(name="Project")
@Getter
@Accessors(fluent = true)
public class Project extends Model{

	@ManyToOne(optional = true)
    @JoinColumn(name = "users")
    @Index(name = "idx_Project_userId")
    @ForeignKey(name = "fk_Project_Users")
	private User users;

	//カラムの作成
	//案件名
    @Column(name = "projectName")
    private String projectName;

    //案件区分
    @Column(name = "projectType")
    private Integer projectType;

    public Project(User users,String projectName,ProjectType projectType){
    	this.users = users;
    	this.projectName = projectName;
    	this.projectType = projectType.intValue;
    }



    public String projectName(){
    	return this.projectName;
    }
    public ProjectType projectType(){
    	return ProjectType.valueOf(this.projectType);
    }

}