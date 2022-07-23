package tech.antoniosgarbi.gestorpeixaria.dto.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.antoniosgarbi.gestorpeixaria.model.Sale;
import tech.antoniosgarbi.gestorpeixaria.model.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SaleDTO {

    private Long id;
    private LocalDateTime moment;
    private PaymentType paymentType;
    private CustomerDTO customer;
    private CollaboratorDTO collaborator;
    private List<SaleItemDTO> saleItems;
    private Double totalValue;

    public SaleDTO(Sale model) {
        this.id = model.getId();
        this.moment = model.getMoment();
        this.paymentType = model.getPaymentType();
        this.customer = new CustomerDTO(model.getCustomer());
        this.collaborator = new CollaboratorDTO(model.getCollaborator());
        this.saleItems = model.getSaleItems()
                .stream()
                .map(SaleItemDTO::new)
                .toList();
        this.totalValue = model.getTotalValue();
    }

}