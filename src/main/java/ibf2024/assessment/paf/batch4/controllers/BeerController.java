package ibf2024.assessment.paf.batch4.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.services.BeerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	private BeerService beerService;

	//Task 2 - view 0
	@GetMapping("/view0.html")
	public ModelAndView getStyleListing() {
		List<Style> styles = beerService.getStyles();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("styles", styles);

		return mav;
	}
	
	//TODO Task 3 - view 1
	@GetMapping(path = "/beer/style/{id}")
	public ModelAndView getBeerDetails(
			@RequestParam String styleName,
			@PathVariable("id") int id,
			@ModelAttribute("style") Style style,
			BindingResult bindings) {
					//	/beer/style/{id}?styleName={styleName}(id=*{styleId}, styleName=*{name})}

		List<Beer> beers = beerService.getBreweriesByBeer(id);

		ModelAndView mav = new ModelAndView("view1.html");
		mav.addObject("beers", beers);
		mav.addObject("styleName", styleName);
		
		return mav;
	}

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}
