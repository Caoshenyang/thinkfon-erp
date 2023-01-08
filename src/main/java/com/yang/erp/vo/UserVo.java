package com.yang.erp.vo;

import com.yang.erp.common.annotation.PasswordMatch;
import com.yang.erp.common.annotation.ValidEmail;
import com.yang.erp.common.annotation.ValidPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * <p>
 *  user vo
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-01 17:42:02
 */
@PasswordMatch
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50, message = "用户名长度必须在1-50之间")
    @ApiModelProperty("用户名")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50, message = "昵称长度必须在1-50之间")
    @ApiModelProperty("昵称")
    private String nickName;

    @NotNull
    @NotBlank
    @ValidPassword
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证密码")
    private String matchingPassword;

    @ValidEmail
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phoneNumber;

}
