package ibf2024.assessment.paf.batch4.repositories;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2024.assessment.paf.batch4.models.Beer;
import ibf2024.assessment.paf.batch4.models.Brewery;
import ibf2024.assessment.paf.batch4.models.Style;

@Repository
public class BeerRepository implements BeerQueries {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// Task 2
		List<Style> beerStyles = new LinkedList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_STYLES_AND_BEERCOUNT);

        while(rs.next()){
            Style style = new Style();
            style.setStyleId(rs.getInt("s_id"));
            style.setName(rs.getString("s_name"));
            style.setBeerCount(rs.getInt("beer_count"));
            beerStyles.add(style);
        }

        return Collections.unmodifiableList(beerStyles);

	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3
		List<Beer> beers = new LinkedList<>();

		final SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_BREWERIES_BY_BEER, styleId);

		while(rs.next()){
            Beer b = new Beer();
            b.setBeerId(rs.getInt("b_id"));
            b.setBeerName(rs.getString("b_name"));
            b.setBeerDescription(rs.getString("b_description"));
            b.setBreweryId(rs.getInt("brewery_id"));
			b.setBreweryName(rs.getString("brewery_name"));
            beers.add(b);
        }

		return Collections.unmodifiableList(beers);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */) {
		// TODO: Task 4

		return Optional.empty();
	}
}
