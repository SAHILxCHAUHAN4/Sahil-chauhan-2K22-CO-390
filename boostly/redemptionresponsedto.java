
package com.boostly.boostly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedemptionResponseDTO {
    private String message;
    private String voucherCode;
    private int amountRedeemed; // e.g., ₹75
    private int newRedeemableBalance;
}
