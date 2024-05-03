package ibf2024.assessment.paf.batch4.repositories;

public interface BeerQueries {

    public static final String SQL_GET_STYLES_AND_BEERCOUNT = """
            SELECT s.id AS s_id, s.style_name AS s_name, count(b.name) AS beer_count
                FROM styles AS s
                LEFT JOIN beers AS b
                ON s.id = b.style_id
                GROUP BY s_id
                ORDER BY beer_count DESC, s_name ASC;
            """;
    
    public static final String SQL_GET_BREWERIES_BY_BEER = """
            SELECT s.id AS s_id, s.style_name AS s_name,
                    b.id AS b_id, b.name AS b_name, b.descript AS b_description,
                    brew.id AS brewery_id, brew.name AS brewery_name
                FROM styles AS s
                JOIN beers AS b
                JOIN breweries AS brew
                ON s.id = b.style_id
                AND b.brewery_id = brew.id
                WHERE s.id = ?
                ORDER BY b_name ASC;
            """;

    public static final String SQL_GET_BEERS_FROM_BREWERY = """
            SELECT b.id AS b_id, b.name AS b_name, b.descript AS b_description,
                    brew.id AS brew_id, brew.name AS brew_name, brew.descript AS brew_description, brew.add_user AS brew_adduser,
                    brew.address1 AS brew_addr1, brew.address2 AS brew_addr2, brew.city AS brew_city, brew.phone AS brew_phone, brew.website AS brew_website
                FROM breweries AS brew
                JOIN beers AS b
                ON brew.id = b.brewery_id
                WHERE brew.id = ?
                ORDER BY b_name ASC;
            """;
    
}
