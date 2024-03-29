package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.AvailableLotResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.service.contract.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Long> productEntry(@RequestBody ProductEntryRequest request) {
        return ResponseEntity.ok(stockService.productEntry(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<AvailableLotResponse>> findByPruduct(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(this.stockService.findAllAvailableLotsByProductId(id, pageable));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<StockEntries>> query(@RequestBody ExpirationLotSpecBody body, Pageable pageable) {
        return ResponseEntity.ok(stockService.query(body, pageable));
    }

}