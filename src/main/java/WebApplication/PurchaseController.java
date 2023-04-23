package WebApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @GetMapping("/data")
    public ResponseEntity<List<Purchase>> getAll(){
        return ResponseEntity.ok(purchaseService.getAll());
    }

   /* @PostMapping(consumes = "application/Purchase")*/
    @PostMapping("/data")
    public ResponseEntity<Void> createTodo(@ModelAttribute("name") String name, @ModelAttribute("prise") double prise) {
        purchaseService.addNew(new Purchase(name,prise));
        try {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("data/{id}")
    public ResponseEntity<Void> updatePurchase(@PathVariable long id) {
        Purchase purchase = purchaseService.getById(id);
        if (purchase == null) return ResponseEntity.notFound().build();
        purchaseService.change(id);
        try {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("data/delete/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable long id) {
        Purchase purchase = purchaseService.getById(id);
        if (purchase == null) return ResponseEntity.notFound().build();
        purchaseService.delete(purchase);
        try {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
