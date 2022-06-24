package cn.lvhaosir.controller;


import cn.lvhaosir.utils.PageData;
import cn.lvhaosir.utils.PageParam;
import cn.lvhaosir.entity.Users;
import cn.lvhaosir.service.UsersService;
import cn.lvhaosir.utils.Result;
import cn.lvhaosir.utils.SystemException;
import cn.lvhaosir.utils.SystemSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService usersService;

	@PostMapping(value="/add")
	public Result<String> addUser(@RequestBody Users users){
		Integer add = usersService.add(users);
		return Result.ok( add==1 ? SystemSuccess.ADD_USER_SUCCESS.getMessage() : SystemException.ADD_USER_FAILED.getMessage());
	}

	@GetMapping(value="/delete/{userId}")
	public Result<String> deleteUser(@PathVariable Integer userId){
		Integer delete = usersService.delete(userId);
		return Result.ok( delete==1 ? SystemSuccess.DELETE_USER_SUCCESS.getMessage() : SystemException.DELETE_USER_FAILED.getMessage());
	}

	@PutMapping(value="/update")
	public Result<String> updateUser(@RequestBody Users users){
		Integer update = usersService.update(users);
		return Result.ok( update==1 ? SystemSuccess.UPDATE_USER_SUCCESS.getMessage() : SystemException.UPDATE_USER_FAILED.getMessage());
	}

	@GetMapping(value="/select/{userId}")
	public Result<Users> selectUserByUserId(@PathVariable Integer userId){
		Users users = usersService.selectUserByUserId(userId);
		return Result.ok(users);
	}

	@GetMapping(value="/select")
	public Result<PageData<Users>> queryListByParam(Users users){
		PageData<Users> user = usersService.queryListByParam(users);
		return Result.ok(user);
	}

	@GetMapping(value="/allUsers")
	public Result<PageData<Users>> allUsers(PageParam pageParam){
		PageData<Users> queryPageList = usersService.allUsers(pageParam);
		return Result.ok(queryPageList);
	}
}
