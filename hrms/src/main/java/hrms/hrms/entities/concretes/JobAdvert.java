package hrms.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "jobadverts")
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)

public class JobAdvert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	@JsonIgnore  // bu kullanıcı nesnemi, istemciye göndermek istemiyorum.
	@Column(name= "created_date")
	private LocalDate createdDate;
	
	@JsonIgnore
	@Column(name= "is_active", columnDefinition = "boolean default true")
	private boolean isActive = true;
	
	@JsonIgnore
	@Column(name= "is_deleted", columnDefinition = "boolean default false")
	private boolean isDeleted = false;
	
	@Column(name = "statement")  // açıklama
	private String statement;
	
	@Column(name = "salary_min")  // min maaş
	private int salaryMin;
	
	@Column(name = "salary_max")  // max maaş
	private int salaryMax;
	
	@Column(name = "open_position_count")  // açık pozisyon adedi
	private int openPositionCount;
	
	@Column(name = "deadline")  // son başvuru tarihi
	private LocalDate deadline;
	
	@Column(name = "published_date")  // yayınlanma tarihi
	private LocalDate publishedDate;
	
	@Column(name = "is_open")
	private boolean isOpen;

	@ManyToOne  
	@JoinColumn(name = "job_position_id") // iş yerimde pozisyon id'si olacak
	private JobPositions jobPositions;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")  // iş yerimde çalışanımın id'si olacak.
	private Employer employer;
	
	@ManyToOne () // bir şehirde 2 iş yeri olduğunu varsayarsak şehir tekrar edicek. o yüzden many.
	@JoinColumn(name = "city_id")  // iş ilanımda şehrimin id'si olacak.
	private City city;
	
}
