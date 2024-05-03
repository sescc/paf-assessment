package ibf2024.assessment.paf.batch4.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_STYLES_AND_BEERCOUNT);

        while (rs.next()) {
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
		// Task 3
		List<Beer> beers = new LinkedList<>();

		final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BREWERIES_BY_BEER, styleId);

		while (rs.next()) {
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
	public Optional<Brewery> getBeersFromBrewery(int breweryId) {
		// TODO: Task 4
		return jdbcTemplate.query(SQL_GET_BEERS_FROM_BREWERY,
				(ResultSet rs) -> {
					if(!rs.next())
						return Optional.empty();
					final Brewery brewery = populate(rs);
					return Optional.of(brewery);
				}, breweryId
        );
	}

	public static Brewery populate(ResultSet rs) throws SQLException {
        final Brewery b = new Brewery();

		b.setBreweryId(rs.getInt("brew_id"));
		b.setName(rs.getString("brew_name"));
		b.setAddress1(rs.getString("brew_addr1"));
		b.setAddress2(rs.getString("brew_addr2"));
		b.setCity(rs.getString("brew_city"));
		b.setPhone(rs.getString("brew_phone"));
		b.setWebsite(rs.getString("brew_website"));
		b.setDescription(rs.getString("brew_description"));
		
		// List<Beer> thisBeers = new LinkedList<>();
		while (rs.next()) {
			Beer thisBeer = new Beer();
			thisBeer.setBeerName(rs.getString("b_name"));
			thisBeer.setBeerDescription(rs.getString("b_description"));
			b.addBeer(thisBeer);	//TODO
		}
		return b;

	}

}
