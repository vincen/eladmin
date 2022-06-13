package me.zhengjie.modules.security.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.zhengjie.modules.system.service.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserDto implements UserDetails {

    @Autowired
    private UserLoginDto user;

    @Autowired
    private List<Long> dataScopes;

    @Autowired
    private List<AuthorityDto> authorities;

    public Set<String> getRoles() {
        return authorities.stream().map(AuthorityDto::getAuthority).collect(Collectors.toSet());
    }

    @Override
//    @JSONField(serialize = false)
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
//    @JSONField(serialize = false)
    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

//    @JSONField(serialize = false)
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

//    @JSONField(serialize = false)
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

//    @JSONField(serialize = false)
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
//    @JSONField(serialize = false)
    @JsonIgnore
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
