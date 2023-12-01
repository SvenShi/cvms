package com.sven.cvms.project.tool.swagger;

import com.sven.cvms.common.utils.StringUtils;
import com.sven.cvms.framework.web.controller.BaseController;
import com.sven.cvms.framework.web.domain.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * swagger 用户测试方法
 *
 * @author ruoyi
 */
@Tag(name = "用户信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController extends BaseController {
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<>();

    static {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @Operation(summary = "获取用户列表")
    @GetMapping("/list")
    public R<List<UserEntity>> userList() {
        List<UserEntity> userList = new ArrayList<>(users.values());
        return R.ok(userList);
    }

    @Operation(summary = "获取用户详细")
    @GetMapping("/{userId}")
    public R<UserEntity> getUser(
            @PathVariable
            @Parameter(description = "用户Id", required = true)
            Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            return R.ok(users.get(userId));
        } else {
            return R.fail("用户不存在");
        }
    }

    @Operation(summary = "新增用户")
    @Parameters({
            @Parameter(name = "userId", description = "用户id"),
            @Parameter(name = "username", description = "用户名称"),
            @Parameter(name = "password", description = "用户密码"),
            @Parameter(name = "mobile", description = "用户手机")})
    @PostMapping("/save")
    public R<String> save(UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return R.fail("用户ID不能为空");
        }
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @Operation(summary = "更新用户")
    @PutMapping("/update")
    public R<String> update(
            @RequestBody
            UserEntity user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return R.fail("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId())) {
            return R.fail("用户不存在");
        }
        users.remove(user.getUserId());
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @Operation(summary = "删除用户信息")
    @Parameter(name = "userId", description = "用户ID", required = true)
    @DeleteMapping("/{userId}")
    public R<String> delete(
            @PathVariable
            Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            users.remove(userId);
            return R.ok();
        } else {
            return R.fail("用户不存在");
        }
    }
}

@Getter
@Schema(name = "UserEntity", description = "用户实体")
class UserEntity {
    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户手机")
    private String mobile;

    public UserEntity() {

    }

    public UserEntity(Integer userId, String username, String password, String mobile) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
