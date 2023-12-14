package vn.LeThanhTuan.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private int code;
    private String message;
    private String accessToken;
    private String refreshToken;
}
