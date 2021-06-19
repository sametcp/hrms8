package hrms.hrms.business.abstracts;

import java.util.List;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvert;

public interface JobAdvertService {
	
	Result add(JobAdvert jobAdvert);
	Result update(JobAdvert jobAdvert);
	Result delete(int id);
	Result changeOpenToClose(int id);
	Result changeCloseToOpen(int id);
		
	DataResult<List<JobAdvert>> getAll();
	DataResult<List<JobAdvert>> getAllOpenJobAdvertList();
	
	DataResult<JobAdvert> findById(int id);
	DataResult<List<JobAdvert>> findAllByOrderByPublishedDateDesc();
	DataResult<List<JobAdvert>> findAllByOrderByPublishedDateAsc();
	DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(int id);
	
}
