package ibf2024.assessment.paf.batch4.repositories;

public interface BeerQueries {

    public static final String GET_STYLES_AND_BEERCOUNT = """
            SELECT s.id AS s_id, s.style_name AS s_name, count(b.name) AS beer_count
                FROM styles AS s
                LEFT JOIN beers AS b
                ON s.id = b.style_id
                GROUP BY s_id
                ORDER BY beer_count DESC, s_name ASC;
            """;
    
    public static final String GET_BREWERIES_BY_BEER = """
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
    
}
