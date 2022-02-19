package spring.qr.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 2/18/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaytmRequestBody {
    private String userName;
    private String mobileNo;
    private String accountType;
    private String accountNo;
}
