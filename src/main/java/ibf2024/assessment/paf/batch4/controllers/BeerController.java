package ibf2024.assessment.paf.batch4.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.models.Style;
import ibf2024.assessment.paf.batch4.services.BeerService;
import ibf2024.assessment.paf.batch4.services.OrderService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class BeerController {

	@Autowired
	private BeerService beerService;

	@Autowired
	private OrderService orderService;

	// Task 2 - view 0
	@GetMapping(path = {"/", "/view0", "/view0.html"})
	public ModelAndView getStyleListing() {
		List<Style> styles = beerService.getStyles();
		
		ModelAndView mav = new ModelAndView("/view0.html");
		mav.addObject("styles", styles);

		return mav;
	}
	
	// Task 3 - view 1
	@GetMapping("/beer/style/{id}")
	public ModelAndView getBeerDetails(
			@RequestParam String styleName,
			@PathVariable("id") int id,
			@ModelAttribute("style") Style style,
			BindingResult bindings) {

		List<Beer> beers = beerService.getBreweriesByBeer(id);

		ModelAndView mav = new ModelAndView("view1.html");
		mav.addObject("beers", beers);
		mav.addObject("styleName", styleName);
		
		return mav;
	}

	// Task 4 - view 2
	@GetMapping("/beer/brewery/{id}")
	public String getBreweryDetails(
				@PathVariable("id") int id,
				Model model) {
		
		Optional<Brewery> opt = beerService.getBeersFromBrewery(id);
		Order order = new Order();
		model.addAttribute("brewery", opt.get());
		model.addAttribute("order", order);
		
		return "view2";
	}
	
	
	//TODO Task 5 - view 2, place order
	@PostMapping("/brewery/{breweryId}/order")
	@ResponseBody
	public ResponseEntity<String> postOrder(
				@PathVariable("breweryId") int breweryId,
				@RequestBody String payload
				//@ModelAttribute Order order
				) {

			Order order = null;
			OrderResult orderResult = null;
			JsonObject resp;

			try {
				order = orderService.postByUrlEncodedForm(payload);
			} catch (Exception e) {
				e.printStackTrace();
				resp = Json.createObjectBuilder()
						.add("error", e.getMessage())
						.build();
				return ResponseEntity.badRequest().body(resp.toString());
			}

			int orderId;
			try {
				orderId = orderService.create(order);
				resp = Json.createObjectBuilder()
						.add("orderId", orderId)
						.build();
			} catch (Exception e) {
				e.printStackTrace();
				resp = Json.createObjectBuilder()
						.add("error", e.getMessage())
						.build();
				return ResponseEntity.badRequest().body(resp.toString());
			}

			orderResult = orderService.insertOrder(order);
			resp = Json.createObjectBuilder()
					.add("rsvpId", orderResult.getId())
					.build();

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.contentType(MediaType.APPLICATION_JSON)
					.body(resp.toString());
	}


}
