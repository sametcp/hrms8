package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobAdvertService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.JobAdvertDao;
import hrms.hrms.entities.concretes.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService{
	
	private JobAdvertDao jobAdvertDao; // dataAccess
	
	@Autowired
	public JobAdvertManager(JobAdvertDao jobAdvertDao)
	{
		super();
		this.jobAdvertDao = jobAdvertDao;
	}
	
	@Override
	public Result add(JobAdvert jobAdvert) 
	{
		if (!CheckIfNullField(jobAdvert))
		{
			return new ErrorResult("You have entered missing information. Please fill in all fields.");
		}
		
		
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("Job advert has been added.");
	}
	
	
	@Override
	public Result update(JobAdvert jobAdvert) 
	{
		this.jobAdvertDao.save(jobAdvert);
		return new SuccessResult("Job advert has been updated.");
	}

	
	
	@Override
	public Result delete(int id) 
	{
		this.jobAdvertDao.deleteById(id);  // id üzerinden direkt silinir.
		return new SuccessResult("Job advert has been deleted.");
	}

	
	@Override
	public Result changeOpenToClose(int id) 
	{
		if (findById(id) == null) 
		{
			return new ErrorResult("There is no such job advert");

		}
		
		if (findById(id).getData().isOpen() == false) 
		{
			return new ErrorResult("There job advert is already closed.");
		}

		JobAdvert jobAdvert = findById(id).getData();
		
		jobAdvert.setOpen(false);
		update(jobAdvert);
		return new SuccessResult("Job advert has been successfully closed.");
	}
	
	
	public Result changeCloseToOpen (int id) 
	{
		if (findById(id) == null) 
		{
			return new ErrorResult("There is no such job advert");

		}
		
		if (findById(id).getData().isOpen() == true) 
		{
			return new ErrorResult("There job advert is already opened.");
		}

		JobAdvert jobAdvert = findById(id).getData();
		
		jobAdvert.setOpen(true);
		update(jobAdvert);
		return new SuccessResult("Job advert has been successfully opened.");
	}
	
	
	@Override
	public DataResult<JobAdvert> findById(int id) 
	{
		return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.findById(id));
	}

	
	
	@Override
	public DataResult<List<JobAdvert>> getAll() {
		
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll());
	}

	
	
	@Override
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertList() 
	{
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertList());
	}

	
	
	@Override
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedDateDesc()
	{
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByOrderByPublishedDateDesc());
	}

	
	
	@Override
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedDateAsc()
	{
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAllByOrderByPublishedDateAsc());
	}
	
	
	
	@Override
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id)
	{
		return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertByEmployer(id));
	}

	
	
	private boolean CheckIfNullField(JobAdvert jobAdvert)
	{
		if (jobAdvert.getJobPositions() != null && jobAdvert.getStatement()!= null && jobAdvert.getCity() != null
				&& jobAdvert.getOpenPositionCount() != 0) 
		{
			return true;
		}
		
		
		return false;
	}
	
}
