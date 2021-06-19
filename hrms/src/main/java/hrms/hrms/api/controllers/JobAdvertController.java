package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.JobAdvertService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvert;

@RestController
@RequestMapping("/api/jobadverts")
public class JobAdvertController {
	
	private JobAdvertService jobAdvertService; // business

	@Autowired
	public JobAdvertController(JobAdvertService jobAdvertService) 
	{
		super();
		this.jobAdvertService = jobAdvertService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvert jobAdvert)
	{
		return this.jobAdvertService.add(jobAdvert);
	}
	
	
	
	@PostMapping("/changeOpenToClose")
	public Result changeOpenToClose(@RequestParam int id)
	{
		return this.jobAdvertService.changeOpenToClose(id);
	}
	
	
	
	@PostMapping("/changeCloseToOpen")
	public Result changeCloseToOpen(@RequestParam int id)
	{
		return this.jobAdvertService.changeCloseToOpen(id);
	}
	
	
	
	@GetMapping("/findbyid")
	public DataResult<JobAdvert> findById(@RequestParam int id)	
	{
		return this.jobAdvertService.findById(id);
	}
	
	
	
	@GetMapping("/getAll")
	public DataResult<List<JobAdvert>> getAll()
	{		
		return this.jobAdvertService.getAll();
	}
	
	
	
	@GetMapping("/getAllOpenJobAdvertList")
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertList()
	{
		return this.jobAdvertService.getAllOpenJobAdvertList();
	}
	
	
	
	@GetMapping("/findAllByOrderByPublishedAtDesc")
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedAtDesc()
	{
		return this.jobAdvertService.findAllByOrderByPublishedDateDesc();
	}
	
	
	
	@GetMapping("/findAllByOrderByPublishedAtAsc")
	public DataResult<List<JobAdvert>> findAllByOrderByPublishedDateAsc()
	{
		return this.jobAdvertService.findAllByOrderByPublishedDateAsc();		
	}
	
	
	
	@GetMapping("/getAllOpenJobAdvertByEmployer")
	public DataResult<List<JobAdvert>> getAllOpenJobAdvertByEmployer(@RequestParam int id)
	{
		return this.jobAdvertService.getAllOpenJobAdvertByEmployer(id);
	}
	
}
