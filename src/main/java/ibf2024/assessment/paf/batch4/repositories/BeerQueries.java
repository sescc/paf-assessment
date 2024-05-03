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
    
    
}
