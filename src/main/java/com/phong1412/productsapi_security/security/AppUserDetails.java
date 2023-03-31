package com.phong1412.productsapi_security.security;

import com.phong1412.productsapi_security.entities.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AppUserDetails implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorities; // Danh sach quyền hạn của một người dùng

    // phương thức tạo mới một UserDetails đc gán giá trị bằng user đăng nhập vào app.
    public AppUserDetails(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(
                user.getRole()
                        .replaceAll("\\s","")
                        .split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        /*
        - user.getRole() lấy chuỗi đại diện cho danh sách các quyền hạn của người dùng từ đối tượng user.
        - split(",") được sử dụng để phân tách các quyền hạn trong chuỗi thành một mảng các chuỗi.
        - Arrays.stream() tạo ra một Stream từ mảng các chuỗi này.
        - map(SimpleGrantedAuthority::new) được sử dụng để chuyển đổi mỗi chuỗi trong Stream thành một đối tượng SimpleGrantedAuthority bằng cách sử dụng constructor của đối tượng này để khởi tạo với chuỗi quyền hạn tương ứng.
        - collect(Collectors.toList()) được sử dụng để thu thập các đối tượng SimpleGrantedAuthority thành một danh sách List<SimpleGrantedAuthority>.
         */
    }

    // Trả về danh sách các đối tượng GrantedAuthority biểu diễn các quyền hạn được cấp cho người dùng.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //Trả về mật khẩu được lưu trữ cho người dùng.
    @Override
    public String getPassword() {
        return this.password;
    }

    //Trả về tên đăng nhập được sử dụng để xác thực người dùng.
    @Override
    public String getUsername() {
        return this.userName;
    }

    //Trả về true nếu tài khoản của người dùng không bị hết hạn.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Trả về true nếu tài khoản của người dùng không bị khóa.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Trả về true nếu thông tin xác thực của người dùng không bị hết hạn.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Trả về true nếu tài khoản của người dùng đã được kích hoạt.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
