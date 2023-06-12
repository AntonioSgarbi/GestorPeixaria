package tech.antoniosgarbi.gestorpeixaria.dto.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.dto.model.CollaboratorDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.ProductDTO;
import tech.antoniosgarbi.gestorpeixaria.dto.model.SupplierDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ExpirationLotSpecBody {

        private Long id;
        private LocalDate arrivalDateStart;
        private LocalDate arrivalDateEnd;
        private LocalDate expirationDateStart;
        private LocalDate expirationDateEnd;
        private Double availableQuantityMin;
        private Double availableQuantityMax;
        private Double arrivalQuantityMin;
        private Double arrivalQuantityMax;
        private Double optionalPriceMin;
        private Double optionalPriceMax;
        private Boolean arrivalRegistered;
        private LocalDateTime registeredMomentStart;
        private LocalDateTime registeredMomentEnd;
        private List<ProductDTO> products;
        private List<SupplierDTO> suppliers;
        private List<CollaboratorDTO> receivedBy;

}
