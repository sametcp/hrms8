package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobPositionsService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.JobPositionsDao;
import hrms.hrms.entities.concretes.JobPositions;

@Service
public class JobPositionsManager implements JobPositionsService{
	
	private JobPositionsDao jobPositionsDao;  // dataAccess katmanı
	
	@Autowired
	public JobPositionsManager(JobPositionsDao jobPositionsDao) 
	{
		super();
		this.jobPositionsDao = jobPositionsDao;
	}

	@Override
	public DataResult<List<JobPositions>> getAll() 
	{
		return new SuccessDataResult<List<JobPositions>>
		(this.jobPositionsDao.findAll(),"Data listelendi");
	}

	@Override
	public Result add(JobPositions jobPositions) {
		if(getJobByTitle(jobPositions.getJobTitle()).getData() != null)
		{
			return new ErrorResult( jobPositions.getJobTitle() + " already exists");
		}
		this.jobPositionsDao.save(jobPositions);
	    return new SuccessResult("Job position has been added.");
	}

	@Override
	public DataResult<JobPositions> getJobByTitle(String title)  // aşağıdaki string pozisyon bilgim artık burda
	{                                                            // title'da
		return new SuccessDataResult<JobPositions>(this.jobPositionsDao.getByJobTitle(title));
	} // title adında bir pozisyon var mı bakmak için o pozisyonun adının değerini alıyor string olarak.
	
	/*
	@Override
	public Result add(JobPositions jobPositions) 
	{
		this.jobPositionsDao.save(jobPositions);
		return new SuccessResult("İş pozisyonu eklendi");
	}
	*/
	
	
}
