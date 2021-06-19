package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobseekerService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.JobSeekerDao;
import hrms.hrms.entities.concretes.Jobseeker;

@Service
public class JobseekerManager implements JobseekerService{
	
	private JobSeekerDao jobseekerDao;
	
	@Autowired
	public JobseekerManager(JobSeekerDao jobseekerDao) {
		super();
		this.jobseekerDao = jobseekerDao;
	}
	
	
	@Override
	public Result add(Jobseeker jobseeker) {
		this.jobseekerDao.save(jobseeker);
	    return new SuccessResult("Jobseeker has been added.");
	}
	
	
	@Override
	public DataResult<List<Jobseeker>> getAll() {  // burda, databaseden verileri alıp controller'a iletiyor.
		return new SuccessDataResult<List<Jobseeker>>
		(this.jobseekerDao.findAll());
	}


	@Override
	public DataResult<Jobseeker> getJobseekerByNationalId(String nationalId) {
		return new SuccessDataResult<Jobseeker>
		(this.jobseekerDao.findJobseekerByNationalId(nationalId));
	}
	
	
	
}
