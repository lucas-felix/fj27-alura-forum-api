package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.input.TopicSearchInputDto;
import br.com.alura.forum.controller.dto.output.CategoryOutputDto;
import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.controller.dto.output.TopicDashboardOutputDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TopicBriefOutputDto> listTopics(TopicSearchInputDto topicSearch,
            @PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageRequest) {

        Specification<Topic> topicSearchSpecification = topicSearch.build();
        Page<Topic> topics = this.topicRepository.findAll(topicSearchSpecification, pageRequest);

        return TopicBriefOutputDto.listFromTopics(topics);
    }
 
    @GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicDashboardOutputDto> getDashboardInfo() {
  
	    	CategoryOutputDto mobile = new CategoryOutputDto("Mobile", "iOS", "Android", "Multiplataforma");
	    	TopicDashboardOutputDto dashboardMobile = new TopicDashboardOutputDto(mobile, 500, 50, 5);
	  	  	
	    	CategoryOutputDto programacao = new CategoryOutputDto("Programação", "Java", "C#", "Ruby", "Python");
	    	TopicDashboardOutputDto dashboardProgramacao = new TopicDashboardOutputDto(programacao, 1000, 100, 10);
	    	
	    	CategoryOutputDto front = new CategoryOutputDto("Front-end", "HTML e CSS", "Frameworks MVC", "JavaScript");
	    	TopicDashboardOutputDto dashboardFront = new TopicDashboardOutputDto(front, 900, 90, 9);
	    	
	    	CategoryOutputDto infra = new CategoryOutputDto("Infraestrutura", "SQL", "Linux", "NoSQL", "Redes", "Windows");
	    	TopicDashboardOutputDto dashboardInfra = new TopicDashboardOutputDto(infra, 850, 85, 8);
	    	
	    	CategoryOutputDto design = new CategoryOutputDto("Design", "UX", "Web Design", "3D", "Apresentações");
	    	TopicDashboardOutputDto dashboardDesign = new TopicDashboardOutputDto(design, 700, 70, 7);
    			
    		CategoryOutputDto business = new CategoryOutputDto("Business", "Marketing Digital", "Excel", "SEO e Adwords", "Agilidade");
		TopicDashboardOutputDto dashboardBusiness = new TopicDashboardOutputDto(business, 800, 80, 7);
		
    		return Arrays.asList(
			dashboardMobile, 
			dashboardProgramacao, 
			dashboardFront, 
			dashboardInfra, 
			dashboardDesign,
			dashboardBusiness
    		);
    }
}
