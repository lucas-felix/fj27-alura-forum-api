package br.com.alura.forum.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.vo.CategoryStatisticsData;

@Service
public class DashboardDataProcessingService {

	private CategoryRepository categoryRepository;	
	private CategoryStatisticsDataLoadingService categoryStatisticsService;

	@Autowired
	public DashboardDataProcessingService(CategoryRepository categoryRepository, 
			CategoryStatisticsDataLoadingService categoryStatisticsService) {
				
		this.categoryRepository = categoryRepository;
		this.categoryStatisticsService = categoryStatisticsService;
	}
	
	public Map<Category, CategoryStatisticsData> execute() {
		Map<Category, CategoryStatisticsData> categoriesAndTheirNumbers = new LinkedHashMap<>();
		
		List<Category> categories = this.categoryRepository.findByCategoryIsNull();
		
		categories.stream().forEach(category -> {
			CategoryStatisticsData statisticsData = this.categoryStatisticsService.load(category);
			categoriesAndTheirNumbers.put(category, statisticsData);	
		});
				
		return categoriesAndTheirNumbers;
	}
}
