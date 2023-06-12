package tech.antoniosgarbi.gestorpeixaria.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.antoniosgarbi.gestorpeixaria.Builder;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.AvailableLotResponse;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.model.ExpirationLot;
import tech.antoniosgarbi.gestorpeixaria.repository.ExpirationLotRepository;
import tech.antoniosgarbi.gestorpeixaria.specification.ExpirationLotSpecification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockServiceImplTest {

    @Mock
    private ExpirationLotRepository expirationLotRepository;
    @InjectMocks
    private StockServiceImpl underTest;

    @Test
    @DisplayName("Should invocke repository when register")
    void productEntry1() {
        ExpirationLot expected = new ExpirationLot();
        expected.setId(1L);
        expected.setProduct(Builder.productUnity1());
        expected.setSupplier(Builder.supllier1());
        when(this.expirationLotRepository.save(any(ExpirationLot.class))).thenReturn(expected);

        this.underTest.productEntry(Builder.productEntryRequest1());
        verify(this.expirationLotRepository).save(any(ExpirationLot.class));
    }

    @Test
    @DisplayName("Should return id")
    void productEntry2() {
        ExpirationLot expected = new ExpirationLot();
        expected.setId(1L);
        expected.setProduct(Builder.productUnity1());
        expected.setSupplier(Builder.supllier1());
        when(this.expirationLotRepository.save(any(ExpirationLot.class))).thenReturn(expected);

        assertEquals(expected.getId(), this.underTest.productEntry(Builder.productEntryRequest1()));
    }

    @Test
    void query() {
        ExpirationLot expected = new ExpirationLot();
        expected.setId(1L);
        expected.setProduct(Builder.productUnity1());
        expected.setSupplier(Builder.supllier1());

        Page<ExpirationLot> expectedPage = new PageImpl<>(List.of(expected));

        when(this.expirationLotRepository.findAll(any(ExpirationLotSpecification.class), any(Pageable.class))).thenReturn(expectedPage);

        Page<StockEntries> result = this.underTest.query(new ExpirationLotSpecBody(), Pageable.unpaged());

        assertEquals(expected.getId(), result.getContent().get(0).getId());
    }

    @Test
    @DisplayName("Should return Page<AvailableLotResponse>")
    void findAllAvailableLotsByProductId() {
        List<ExpirationLot> list = List.of(Builder.expirationLot1(), Builder.expirationLot2());

        Page<ExpirationLot> page = new PageImpl<>(list);

        when(this.expirationLotRepository
                .findAllByProductIdAndAvailableQuantityGreaterThan(anyLong(), anyDouble(), any(Pageable.class))
        ).thenReturn(page);

        Page<AvailableLotResponse> response = this.underTest.findAllAvailableLotsByProductId(1L, Pageable.unpaged());

        assertEquals(list.get(0).getId(), response.getContent().get(0).getId());
        assertEquals(list.get(1).getId(), response.getContent().get(1).getId());
    }
}