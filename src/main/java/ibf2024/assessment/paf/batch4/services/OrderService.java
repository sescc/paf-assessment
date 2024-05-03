package ibf2024.assessment.paf.batch4.services;

import java.io.StringReader;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ibf2024.assessment.paf.batch4.controllers.OrderResult;
import ibf2024.assessment.paf.batch4.models.Order;
import ibf2024.assessment.paf.batch4.repositories.OrderRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    // public void postByUrlEncodedForm(Order order) {
    //     MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
    //     form.add("beerId", order.getBeerId());
    //     form.add("quantity", order.getQuantity());

    //     // Construct request
    //     RequestEntity<MultiValueMap> post = RequestEntity
    //             .post("https://httpbin.org/post")
    //             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    //             .accept(MediaType.APPLICATION_JSON)
    //             .body(form);

    //     RestTemplate template = new RestTemplate();
    //     ResponseEntity<String> resp = template.exchange(post, String.class);

    //     System.out.printf(">>> Body by URL-Encoded: %s", resp.getBody());
        
    // }

    public Order createNewOrder(String json) {
        // Generate orderId
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        //Order order = toOrder(orderId, json);
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject payload = reader.readObject();

        Order order = new Order();
        order.setBreweryId(payload.getInt("brewery_id"));
        //order.setQuantity(payload.getInt("quantity", ""));

        List<Order> orders = payload.getJsonArray("order").stream()
            .map(v -> v.asJsonObject())
            .map(v -> {
                Order o = new Order();
                o.setBreweryId(v.getInt("brewery_id"));
                o.setQuantity(v.getInt("quantity"));
                return order;
            })
            .toList();


        return order;
    }

    public OrderResult insertOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertOrder'");
    }

    public int create(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public Order postByUrlEncodedForm(String payload) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postByUrlEncodedForm'");
    }
    
}
